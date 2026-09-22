#!/usr/bin/env node
/**
 * Check relative links in Markdown against the JavaForge tree.
 * Usage: node scripts/validate-links.mjs
 */
import { readFileSync, existsSync, readdirSync, statSync } from 'fs'
import { join, dirname, resolve, normalize } from 'path'
import { fileURLToPath } from 'url'

const ROOT = resolve(dirname(fileURLToPath(import.meta.url)), '..')

function walk(dir, acc = []) {
  for (const entry of readdirSync(dir)) {
    if (entry === '.git' || entry === 'target' || entry === 'node_modules') continue
    const full = join(dir, entry)
    if (statSync(full).isDirectory()) walk(full, acc)
    else if (entry.endsWith('.md')) acc.push(full)
  }
  return acc
}

const LINK_RE = /\[([^\]]*)\]\(([^)\s]+)\)/g
const errors = []

for (const file of walk(ROOT)) {
  const content = readFileSync(file, 'utf8')
  const lines = content.split('\n')
  for (let i = 0; i < lines.length; i++) {
    if (lines[i].trimStart().startsWith('```')) continue
    LINK_RE.lastIndex = 0
    let match
    while ((match = LINK_RE.exec(lines[i])) !== null) {
      const href = match[2]
      if (/^(https?:|mailto:|#)/.test(href)) continue
      const pathOnly = href.split('#')[0]
      if (!pathOnly) continue
      const target = normalize(resolve(dirname(file), pathOnly))
      if (!target.startsWith(ROOT)) continue
      if (!existsSync(target)) {
        const rel = file.slice(ROOT.length + 1).replace(/\\/g, '/')
        errors.push(`${rel}:${i + 1}  ${href}`)
      }
    }
  }
}

if (errors.length) {
  console.error(`${errors.length} broken link(s):`)
  for (const err of errors) console.error(`  ${err}`)
  process.exit(1)
}

console.log('All relative Markdown links resolve.')

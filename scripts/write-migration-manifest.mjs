#!/usr/bin/env node
/**
 * Record where each JavaForge file came from.
 * Compares against the historical JavaMastery checkout when it is present.
 * Usage: node scripts/write-migration-manifest.mjs
 */
import { createHash } from 'crypto'
import { readFileSync, writeFileSync, existsSync, readdirSync, statSync, mkdirSync } from 'fs'
import { join, dirname, relative, resolve } from 'path'
import { fileURLToPath } from 'url'

const ROOT = resolve(dirname(fileURLToPath(import.meta.url)), '..')
const SOURCE = resolve(ROOT, '..', 'JavaMastery')
const SOURCE_COMMIT = '3a409be922a305190662a923b82fbfbbda064f54'
const SKIP = new Set(['.git', '.migration', 'node_modules', 'target'])

function walk(dir, acc = []) {
  for (const entry of readdirSync(dir)) {
    if (SKIP.has(entry)) continue
    const full = join(dir, entry)
    const info = statSync(full)
    if (info.isDirectory()) walk(full, acc)
    else acc.push(full)
  }
  return acc
}

function sha256(file) {
  return createHash('sha256').update(readFileSync(file)).digest('hex')
}

function rel(file) {
  return relative(ROOT, file).replace(/\\/g, '/')
}

if (!existsSync(SOURCE)) {
  console.error(`Historical source not found at ${SOURCE}`)
  process.exit(1)
}

const files = walk(ROOT).map((file) => {
  const path = rel(file)
  const sourceFile = join(SOURCE, path)
  const targetSha = sha256(file)
  let status = 'added'
  let sourceSha = null
  if (existsSync(sourceFile) && statSync(sourceFile).isFile()) {
    sourceSha = sha256(sourceFile)
    status = sourceSha === targetSha ? 'unchanged' : 'modified'
  }
  return {
    sourcePath: path,
    targetPath: path,
    sourceSha256: sourceSha,
    targetSha256: targetSha,
    status,
  }
})

files.sort((a, b) => a.targetPath.localeCompare(b.targetPath))

const counts = { unchanged: 0, modified: 0, added: 0 }
for (const file of files) counts[file.status]++

const missingFromForge = walk(SOURCE)
  .map((file) => relative(SOURCE, file).replace(/\\/g, '/'))
  .filter((path) => !existsSync(join(ROOT, path)))

const manifest = {
  sourceRepository: 'JavaMastery',
  sourceCommit: SOURCE_COMMIT,
  generatedAt: new Date().toISOString(),
  note: 'JavaForge is canonical after this migration. JavaMastery is historical and is not a runtime dependency.',
  counts,
  missingFromForge,
  files,
}

mkdirSync(join(ROOT, '.migration'), { recursive: true })
writeFileSync(join(ROOT, '.migration', 'source-manifest.json'), JSON.stringify(manifest, null, 2) + '\n')

const modified = files.filter((f) => f.status === 'modified').map((f) => f.targetPath)
const added = files.filter((f) => f.status === 'added').map((f) => f.targetPath)

const report = `# Migration report

JavaForge was copied from JavaMastery \`${SOURCE_COMMIT}\`.

| Status | Files |
|---|---|
| Unchanged | ${counts.unchanged} |
| Modified in JavaForge | ${counts.modified} |
| Added in JavaForge | ${counts.added} |
| Present in JavaMastery but missing here | ${missingFromForge.length} |

Package names, class filenames, and \`java pkg…/File.java\` paths were not renamed.

## Modified

${modified.map((p) => `- \`${p}\``).join('\n') || '- none'}

## Added

${added.map((p) => `- \`${p}\``).join('\n') || '- none'}

## Why files changed

- \`build/README.md\` pointed at \`docs/BuildTools.md\`. The page is \`docs/04-reference/13-BuildTools.md\`.
- Twenty other relative links in \`docs/\` climbed one directory too few or too many, so they missed \`docs/05-quick-ref\`, \`docs/06-career\`, \`pkg*\`, \`projects/\`, and \`build/\`.
- Workflows no longer check out or dispatch \`JavaMastery-UI\`. JavaForge-UI consumes this repository at build time.
- The README identifies this tree as JavaForge, the canonical source.

Java 25 lessons were not added in the baseline. Java 21 material is unchanged apart from the link fixes above.
`

writeFileSync(join(ROOT, '.migration', 'migration-report.md'), report)
console.log(`Wrote .migration for ${files.length} files (${counts.unchanged} unchanged, ${counts.modified} modified, ${counts.added} added)`)
if (missingFromForge.length) {
  console.log('Missing:')
  for (const path of missingFromForge) console.log(`  ${path}`)
}

import { readdirSync, readFileSync, writeFileSync } from 'fs'
import { join } from 'path'

const dir = join('docs', '02-learn')
const arrow = '\u2192' // →

for (const f of readdirSync(dir).filter((x) => x.endsWith('.md'))) {
  const p = join(dir, f)
  let c = readFileSync(p, 'utf8')
  const orig = c

  // Fix mojibake arrows from prior bad writes
  c = c.replace(/\u00E2\u20AC\u2122|\uFFFD\+'\s*|\u00E2\u2020\u2122/g, arrow)
  c = c.replace(/\*\*Related .{1,8}\*\*/g, `**Related ${arrow}**`)
  c = c.replace(/\*\*Next .{1,8}\*\*/g, `**Next ${arrow}**`)

  // Fix label-less footers: ** →** [text](href)  OR  ** �** variants
  c = c.replace(/\*\*\s*[^\n\[]{0,6}\*\*\s*\[([^\]]+)\]\(([^)]+)\)/g, (m, text, href) => {
    // Only rewrite if this looks like a broken arrow footer (no word before arrow)
    if (/Related|Next|Previous|Deep dive|Interview|Quick|Start|Milestone/i.test(m) && !/\*\*\s*\u2192\*\*/.test(m) && !/\*\*\s*\?\*\*/.test(m) && !/\*\*\s*→\*\*/.test(m)) {
      // already labeled somehow — leave unless broken
    }
    if (/^\*\*\s*→\*\*/.test(m.trim()) || /^\*\*\s*\u2192\*\*/.test(m.trim()) || /^\*\*\s*.{1,3}\*\*\s*\[/.test(m.trim()) && !/Related|Next|Previous|Deep|Interview|Quick|Start/i.test(m)) {
      const label = /03-interview|04-reference/.test(href) ? 'Related' : 'Next'
      return `**${label} ${arrow}** [${text}](${href})`
    }
    return m
  })

  // Explicit simple replace for the known broken pattern
  c = c.replace(/\*\* →\*\* \[([^\]]+)\]\(([^)]+)\)/g, (_, text, href) => {
    const label = /03-interview|04-reference/.test(href) ? 'Related' : 'Next'
    return `**${label} ${arrow}** [${text}](${href})`
  })
  c = c.replace(/\*\* \u2192\*\* \[([^\]]+)\]\(([^)]+)\)/g, (_, text, href) => {
    const label = /03-interview|04-reference/.test(href) ? 'Related' : 'Next'
    return `**${label} ${arrow}** [${text}](${href})`
  })

  if (c !== orig) {
    writeFileSync(p, c, 'utf8')
    console.log('fixed', f)
  }
}

import { readFileSync, writeFileSync, readdirSync } from 'fs'
import { join } from 'path'

const roots = [
  'pkg5leetcode/blind75',
  'pkg5leetcode/interview150',
  'pkg5leetcode/top100',
  'pkg5leetcode/official75',
]

function annotate(dir) {
  let n = 0
  for (const f of readdirSync(dir).filter((x) => x.endsWith('.java'))) {
    const p = join(dir, f)
    let c = readFileSync(p, 'utf8')
    const orig = c
    if (c.includes('static class ListNode') && !c.includes('pkg5leetcode/common/ListNode')) {
      c = c.replace(
        /(\n\s*)(static class ListNode\b)/,
        '$1/** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */\n$1$2'
      )
    }
    if (c.includes('static class TreeNode') && !c.includes('pkg5leetcode/common/TreeNode')) {
      c = c.replace(
        /(\n\s*)(static class TreeNode\b)/,
        '$1/** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */\n$1$2'
      )
    }
    if (c !== orig) {
      writeFileSync(p, c)
      n++
    }
  }
  console.log(dir, n)
}

for (const r of roots) annotate(r)

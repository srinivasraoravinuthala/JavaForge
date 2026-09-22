const API = 'http://localhost:8080/api/notes'
const listEl = document.getElementById('list')
const form = document.getElementById('form')
const errorEl = document.getElementById('error')

async function loadNotes() {
  errorEl.hidden = true
  const res = await fetch(API)
  if (!res.ok) throw new Error('Failed to load notes — is Spring Boot running?')
  const notes = await res.json()
  listEl.innerHTML = ''
  for (const note of notes) {
    const li = document.createElement('li')
    li.innerHTML = `<div><strong>${escapeHtml(note.title)}</strong><div class="muted">${escapeHtml(note.body || '')}</div></div>`
    const btn = document.createElement('button')
    btn.textContent = 'Delete'
    btn.onclick = async () => {
      await fetch(`${API}/${note.id}`, { method: 'DELETE' })
      loadNotes().catch(showError)
    }
    li.appendChild(btn)
    listEl.appendChild(li)
  }
}

form.addEventListener('submit', async (e) => {
  e.preventDefault()
  const title = document.getElementById('title').value.trim()
  const body = document.getElementById('body').value.trim()
  try {
    const res = await fetch(API, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title, body }),
    })
    if (!res.ok) throw new Error('Create failed')
    form.reset()
    await loadNotes()
  } catch (err) {
    showError(err)
  }
})

function showError(err) {
  errorEl.hidden = false
  errorEl.textContent = err.message || String(err)
}

function escapeHtml(s) {
  return String(s)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
}

loadNotes().catch(showError)

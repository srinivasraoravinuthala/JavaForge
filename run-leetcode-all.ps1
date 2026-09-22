# Smoke-test all LeetCode solution files (pkg5leetcode and subfolders).
param([string]$Folder = "pkg5leetcode")

$ErrorActionPreference = "Continue"
$files = Get-ChildItem -Path $Folder -Recurse -Filter *.java | Sort-Object FullName
$pass = 0; $fail = 0; $failed = @()

foreach ($f in $files) {
    Write-Host "RUN  $($f.Name)" -ForegroundColor Cyan
    java $f.FullName 2>&1 | Out-Null
    if ($LASTEXITCODE -eq 0) {
        $pass++
        Write-Host "PASS $($f.Name)" -ForegroundColor Green
    } else {
        $fail++; $failed += $f.FullName
        Write-Host "FAIL $($f.Name)" -ForegroundColor Red
    }
}

Write-Host "`n================ LEETCODE SUMMARY ================"
Write-Host "Passed: $pass  Failed: $fail" -ForegroundColor Yellow
if ($fail -gt 0) { $failed | ForEach-Object { Write-Host "  - $_" -ForegroundColor Red } }

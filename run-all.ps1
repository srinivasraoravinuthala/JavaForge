# Runs every standalone Java file via single-file source launch.
# Skips Maven module sources (pkg14testing, pkg20serialization, spi-demo, jmh-demo, build/).
# Usage:  ./run-all.ps1            (run all packages)
#         ./run-all.ps1 pkg1core   (run one package)
# For Maven modules: ./run-maven-all.ps1
param([string]$Path = ".")

$ErrorActionPreference = "Continue"
$exclude = @('\pkg14testing\', '\pkg20serialization\', '\spi-demo\', '\jmh-demo\', '\build\',
             '\blind75\', '\official75\', '\interview150\', '\top100\')
$files = Get-ChildItem -Path $Path -Recurse -Filter *.java | Where-Object {
    $p = $_.FullName
  foreach ($e in $exclude) { if ($p -like "*$e*") { return $false } }
    return $true
} | Sort-Object FullName

$pass = 0; $fail = 0; $failed = @()

foreach ($f in $files) {
    Write-Host "RUN  $($f.FullName)" -ForegroundColor Cyan
    java $f.FullName 2>&1 | Out-Null
    if ($LASTEXITCODE -eq 0) {
        $pass++
        Write-Host "PASS $($f.Name)" -ForegroundColor Green
    } else {
        $fail++; $failed += $f.FullName
        Write-Host "FAIL $($f.Name)" -ForegroundColor Red
    }
}

Write-Host "`n================ SUMMARY ================"
Write-Host "Passed: $pass  Failed: $fail" -ForegroundColor Yellow
if ($fail -gt 0) { $failed | ForEach-Object { Write-Host "  - $_" -ForegroundColor Red } }

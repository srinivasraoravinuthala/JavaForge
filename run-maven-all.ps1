# Runs Maven modules (testing, serialization, JMH, SPI demo, build examples).
$ErrorActionPreference = "Continue"
$root = $PSScriptRoot
$modules = @(
    "pkg14testing/pom.xml",
    "pkg20serialization/pom.xml",
    "pkg15modules/spi-demo/pom.xml",
    "pkg19performance/jmh-demo/pom.xml",
    "build/maven/simple-app/pom.xml",
    "build/maven/multi-module/parent/pom.xml"
)

$pass = 0; $fail = 0
foreach ($pom in $modules) {
    $path = Join-Path $root $pom
    Write-Host "MAVEN $pom" -ForegroundColor Cyan
    if ($pom -like "*jmh-demo*") {
        mvn -q -f $path package 2>&1 | Out-Null
    } elseif ($pom -like "*pkg14testing*") {
        mvn -q -f $path test 2>&1 | Out-Null
    } else {
        mvn -q -f $path compile 2>&1 | Out-Null
    }
    if ($LASTEXITCODE -eq 0) {
        $pass++; Write-Host "PASS $pom" -ForegroundColor Green
    } else {
        $fail++; Write-Host "FAIL $pom" -ForegroundColor Red
    }
}
Write-Host "`nMaven modules: Passed=$pass Failed=$fail"

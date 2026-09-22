#!/usr/bin/env bash
# Runs every standalone Java file via single-file source launch.
# Usage:  ./run-all.sh            (run all packages)
#         ./run-all.sh pkg1core   (run one package)
set -u
ROOT="${1:-.}"
pass=0; fail=0; failed=()

while IFS= read -r -d '' f; do
    echo "RUN  $f"
    if java "$f" >/dev/null 2>&1; then
        pass=$((pass+1)); echo "PASS $(basename "$f")"
    else
        fail=$((fail+1)); failed+=("$f"); echo "FAIL $(basename "$f")"
    fi
done < <(find "$ROOT" -name '*.java' -print0 | sort -z)

echo
echo "================ SUMMARY ================"
echo "Passed: $pass  Failed: $fail"
for x in "${failed[@]:-}"; do [ -n "$x" ] && echo "  - $x"; done

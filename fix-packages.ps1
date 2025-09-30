# Fix package declarations
$files = @(
    "src\main\java\com\jaba\heraldism\domain\ports\MatchesRepository.java",
    "src\main\java\com\jaba\heraldism\domain\ports\OpenDotaClient.java",
    "src\main\java\com\jaba\heraldism\domain\ports\PlayersRepository.java",
    "src\main\java\com\jaba\heraldism\domain\ports\RAGAdvisor.java",
    "src\main\java\com\jaba\heraldism\infrastructure\idempotency\IdempotencyStore.java",
    "src\main\java\com\jaba\heraldism\infrastructure\persistence\dynamodb\DynamoDbPlayersRepository.java"
)

foreach ($file in $files) {
    $fullPath = Join-Path "d:\G code\AWS\heraldism" $file
    if (Test-Path $fullPath) {
        (Get-Content $fullPath) -replace 'package main\.java\.com\.jaba\.heraldism', 'package com.jaba.heraldism' | Set-Content $fullPath
        Write-Host "Fixed: $file"
    }
}

Write-Host "Done!"

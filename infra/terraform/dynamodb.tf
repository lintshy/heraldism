# DynamoDB Table: Players
resource "aws_dynamodb_table" "players" {
  name           = "${var.project_name}-${var.environment}-players"
  billing_mode   = var.dynamodb_billing_mode
  hash_key       = "playerId"
  
  attribute {
    name = "playerId"
    type = "S"
  }
  
  attribute {
    name = "steamId"
    type = "S"
  }
  
  global_secondary_index {
    name            = "SteamIdIndex"
    hash_key        = "steamId"
    projection_type = "ALL"
  }
  
  point_in_time_recovery {
    enabled = var.enable_point_in_time_recovery
  }
  
  tags = merge(
    var.tags,
    {
      Name = "${var.project_name}-${var.environment}-players"
    }
  )
}

# DynamoDB Table: Matches
resource "aws_dynamodb_table" "matches" {
  name           = "${var.project_name}-${var.environment}-matches"
  billing_mode   = var.dynamodb_billing_mode
  hash_key       = "matchId"
  range_key      = "timestamp"
  
  attribute {
    name = "matchId"
    type = "S"
  }
  
  attribute {
    name = "timestamp"
    type = "N"
  }
  
  attribute {
    name = "playerId"
    type = "S"
  }
  
  global_secondary_index {
    name            = "PlayerIdIndex"
    hash_key        = "playerId"
    range_key       = "timestamp"
    projection_type = "ALL"
  }
  
  point_in_time_recovery {
    enabled = var.enable_point_in_time_recovery
  }
  
  tags = merge(
    var.tags,
    {
      Name = "${var.project_name}-${var.environment}-matches"
    }
  )
}

# DynamoDB Table: Match Scores
resource "aws_dynamodb_table" "match_scores" {
  name           = "${var.project_name}-${var.environment}-match-scores"
  billing_mode   = var.dynamodb_billing_mode
  hash_key       = "matchId"
  range_key      = "playerId"
  
  attribute {
    name = "matchId"
    type = "S"
  }
  
  attribute {
    name = "playerId"
    type = "S"
  }
  
  point_in_time_recovery {
    enabled = var.enable_point_in_time_recovery
  }
  
  tags = merge(
    var.tags,
    {
      Name = "${var.project_name}-${var.environment}-match-scores"
    }
  )
}

# DynamoDB Table: Idempotency
resource "aws_dynamodb_table" "idempotency" {
  name           = "${var.project_name}-${var.environment}-idempotency"
  billing_mode   = var.dynamodb_billing_mode
  hash_key       = "idempotencyKey"
  
  attribute {
    name = "idempotencyKey"
    type = "S"
  }
  
  ttl {
    attribute_name = "expirationTime"
    enabled        = true
  }
  
  point_in_time_recovery {
    enabled = var.enable_point_in_time_recovery
  }
  
  tags = merge(
    var.tags,
    {
      Name = "${var.project_name}-${var.environment}-idempotency"
    }
  )
}

# Outputs
output "players_table_name" {
  value       = aws_dynamodb_table.players.name
  description = "Name of the players DynamoDB table"
}

output "matches_table_name" {
  value       = aws_dynamodb_table.matches.name
  description = "Name of the matches DynamoDB table"
}

output "match_scores_table_name" {
  value       = aws_dynamodb_table.match_scores.name
  description = "Name of the match scores DynamoDB table"
}

output "idempotency_table_name" {
  value       = aws_dynamodb_table.idempotency.name
  description = "Name of the idempotency DynamoDB table"
}

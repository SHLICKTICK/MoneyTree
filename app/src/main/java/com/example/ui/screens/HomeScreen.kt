package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.DirectionsSubway
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TransactionEntity
import com.example.ui.SproutViewModel
import com.example.ui.theme.SproutBadgeBlueBg
import com.example.ui.theme.SproutBadgeBlueText
import com.example.ui.theme.SproutBadgeGreenBg
import com.example.ui.theme.SproutBadgeOrangeBg
import com.example.ui.theme.SproutBadgeOrangeText
import com.example.ui.theme.SproutBadgePinkBg
import com.example.ui.theme.SproutBadgePinkText
import com.example.ui.theme.SproutBadgePurpleBg
import com.example.ui.theme.SproutBadgePurpleText
import com.example.ui.theme.SproutBadgeYellowBg
import com.example.ui.theme.SproutBadgeYellowText
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutPrimaryContainer
import com.example.ui.theme.SproutPrimaryFixed
import com.example.ui.theme.SproutSecondary
import java.util.Locale

@Composable
fun HomeScreen(
  viewModel: SproutViewModel,
  onOpenLogSheet: () -> Unit,
  onOpenOnboarding: () -> Unit,
  onNavigateToScreen: (String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val transactions by viewModel.transactions.collectAsState()

  // Compute live budget balances
  val totalBudget = 3100.00
  val totalExpenses = transactions
    .filter { it.type == "Expense" }
    .sumOf { it.amount }
  val totalSpentMonth = 1080.00 + totalExpenses // Realistic base + user logged
  val remainingBudget = (totalBudget - totalSpentMonth).coerceAtLeast(0.0)
  val progressFraction = (totalSpentMonth / totalBudget).toFloat().coerceIn(0f, 1f)

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .statusBarsPadding()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item {
      // User Profile Header
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 10.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp),
          modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onNavigateToScreen("profile") }
            .padding(4.dp)
            .testTag("home_header_profile")
        ) {
          Surface(
            shape = CircleShape,
            color = SproutPrimaryFixed.copy(alpha = 0.5f),
            modifier = Modifier.size(42.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Text(
                text = "A",
                color = SproutPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
              )
            }
          }

          Column {
            Text(
              text = "Welcome back",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontWeight = FontWeight.Medium
            )
            Text(
              text = "Alex River",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          // View Meet Sprout tour trigger
          IconButton(
            onClick = onOpenOnboarding,
            modifier = Modifier.testTag("onboarding_shortcut")
          ) {
            Icon(
              imageVector = Icons.Default.Spa,
              contentDescription = "Meet Sprout",
              tint = SproutPrimary
            )
          }

          IconButton(
            onClick = { /* Notifications */ },
            modifier = Modifier.testTag("notifications_button")
          ) {
            Icon(
              imageVector = Icons.Default.Notifications,
              contentDescription = "Notifications",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }

    item {
      // Total Remaining Budget Card
      Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(24.dp))
          .clickable { onNavigateToScreen("budgets") }
          .testTag("home_budget_card")
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text = "Total Remaining Budget",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "View Budgets",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                modifier = Modifier.size(14.dp)
              )
            }
            Surface(
              shape = CircleShape,
              color = MaterialTheme.colorScheme.surfaceContainer
            ) {
              Text(
                text = "8 days left",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }

          Text(
            text = "R${String.format(Locale.US, "%,.2f", remainingBudget)}",
            fontSize = 38.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface,
            letterSpacing = (-1).sp
          )

          Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            LinearProgressIndicator(
              progress = { progressFraction },
              modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(CircleShape),
              color = SproutSecondary,
              trackColor = MaterialTheme.colorScheme.surfaceContainer
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "${(progressFraction * 100).toInt()}% of R3,100 limit",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = "On track",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SproutSecondary
              )
            }
          }
        }
      }
    }

    item {
      // 2-Column Bento Stat Cards
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Card(
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
          ),
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onNavigateToScreen("insights") }
            .testTag("home_stat_spent")
        ) {
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Spent this month",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "View Insights",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(16.dp)
              )
            }
            Text(
              text = "R${String.format(Locale.US, "%,.2f", totalSpentMonth)}",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = SproutPrimary
            )
          }
        }

        Card(
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
          ),
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onNavigateToScreen("budgets") }
            .testTag("home_stat_saved")
        ) {
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Saved so far",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "View Budgets",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(16.dp)
              )
            }
            Text(
              text = "R450.00",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = SproutSecondary
            )
          }
        }
      }
    }

    item {
      // Quick Trigger Log Transaction Banner
      Button(
        onClick = onOpenLogSheet,
        colors = ButtonDefaults.buttonColors(
          containerColor = SproutPrimaryContainer,
          contentColor = Color.White
        ),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .testTag("home_log_tx_button")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
          )
          Text(
            text = "Log New Transaction",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    item {
      // Section Title with View Insights shortcut
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Recent Transactions",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onNavigateToScreen("insights") }
            .padding(horizontal = 6.dp, vertical = 2.dp)
            .testTag("home_view_insights_link")
        ) {
          Text(
            text = "View Insights",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = SproutPrimary
          )
          Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = SproutPrimary,
            modifier = Modifier.size(14.dp)
          )
        }
      }
    }

    // Transactions List
    items(transactions, key = { it.id }) { tx ->
      TransactionRow(
        transaction = tx,
        onDelete = { viewModel.deleteTransaction(tx.id) }
      )
    }

    item {
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

@Composable
fun TransactionRow(
  transaction: TransactionEntity,
  onDelete: () -> Unit
) {
  val (icon, bg, tint) = getCategoryVisuals(transaction.category)

  Surface(
    shape = RoundedCornerShape(18.dp),
    color = MaterialTheme.colorScheme.surfaceContainerLowest,
    shadowElevation = 1.dp,
    modifier = Modifier
      .fillMaxWidth()
      .testTag("tx_${transaction.id}")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Surface(
          shape = CircleShape,
          color = bg,
          modifier = Modifier.size(44.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = icon,
              contentDescription = transaction.category,
              tint = tint,
              modifier = Modifier.size(22.dp)
            )
          }
        }

        Column {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Text(
              text = transaction.merchant,
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold,
              color = MaterialTheme.colorScheme.onSurface
            )
            if (transaction.isSplit) {
              Surface(
                shape = CircleShape,
                color = SproutBadgeGreenBg
              ) {
                Text(
                  text = "Split",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = SproutSecondary,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }

          Text(
            text = "${transaction.dateDisplay} • ${transaction.category}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        val isExpense = transaction.type == "Expense"
        Text(
          text = "${if (isExpense) "-" else "+"}R${String.format(Locale.US, "%.2f", transaction.amount)}",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = if (isExpense) MaterialTheme.colorScheme.onSurface else SproutSecondary
        )

        IconButton(
          onClick = onDelete,
          modifier = Modifier.size(32.dp)
        ) {
          Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}

fun getCategoryVisuals(category: String): Triple<ImageVector, Color, Color> {
  return when (category.lowercase()) {
    "groceries" -> Triple(Icons.Default.ShoppingBasket, SproutBadgeGreenBg, SproutSecondary)
    "dining" -> Triple(Icons.Default.Restaurant, SproutBadgeOrangeBg, SproutBadgeOrangeText)
    "coffee" -> Triple(Icons.Default.LocalCafe, SproutBadgeYellowBg, SproutBadgeYellowText)
    "transport" -> Triple(Icons.Default.DirectionsSubway, SproutBadgeBlueBg, SproutBadgeBlueText)
    "shopping" -> Triple(Icons.Default.ShoppingBag, SproutBadgePurpleBg, SproutBadgePurpleText)
    "health" -> Triple(Icons.Default.Favorite, SproutBadgePinkBg, SproutBadgePinkText)
    else -> Triple(Icons.Default.Category, Color(0xFFEFEEEB), Color(0xFF59413C))
  }
}

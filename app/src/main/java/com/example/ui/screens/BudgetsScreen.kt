package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.SproutViewModel
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutSecondary
import java.util.Locale

data class EnvelopeBudget(
  val category: String,
  val monthlyCap: Double,
  val baseSpent: Double
)

@Composable
fun BudgetsScreen(
  viewModel: SproutViewModel,
  onOpenLogForCategory: (String) -> Unit,
  onNavigateBack: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  val transactions by viewModel.transactions.collectAsState()

  val envelopes = listOf(
    EnvelopeBudget("Groceries", 350.0, 142.80),
    EnvelopeBudget("Dining", 200.0, 68.50),
    EnvelopeBudget("Coffee", 60.0, 32.45),
    EnvelopeBudget("Transport", 120.0, 64.00),
    EnvelopeBudget("Shopping", 150.0, 98.25),
    EnvelopeBudget("Health", 80.0, 24.00)
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .statusBarsPadding()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 10.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        if (onNavigateBack != null) {
          IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.testTag("budgets_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back to Home",
              tint = MaterialTheme.colorScheme.onSurface
            )
          }
        }
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
          Text(
            text = "Smart Envelopes",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Flexible spending caps that adjust with your mindful habits.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    items(envelopes) { envelope ->
      val categorySpent = envelope.baseSpent + transactions
        .filter { it.category.equals(envelope.category, ignoreCase = true) && it.type == "Expense" }
        .sumOf { it.amount }
      val remaining = (envelope.monthlyCap - categorySpent).coerceAtLeast(0.0)
      val progress = (categorySpent / envelope.monthlyCap).toFloat().coerceIn(0f, 1f)
      val (icon, bg, tint) = getCategoryVisuals(envelope.category)

      Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("envelope_${envelope.category.lowercase()}")
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Surface(
                shape = CircleShape,
                color = bg,
                modifier = Modifier.size(38.dp)
              ) {
                androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(20.dp)
                  )
                }
              }
              Column {
                Text(
                  text = envelope.category,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = "R${String.format(Locale.US, "%.2f", remaining)} remaining",
                  fontSize = 12.sp,
                  color = if (remaining > 20) SproutSecondary else SproutPrimary,
                  fontWeight = FontWeight.SemiBold
                )
              }
            }

            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Text(
                text = "R${String.format(Locale.US, "%.0f", categorySpent)} / R${String.format(Locale.US, "%.0f", envelope.monthlyCap)}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              IconButton(
                onClick = { onOpenLogLogCategory(viewModel, envelope.category, onOpenLogSheet = onOpenLogForCategory) },
                modifier = Modifier.size(32.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Add,
                  contentDescription = "Add to ${envelope.category}",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(20.dp)
                )
              }
            }
          }

          LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
              .fillMaxWidth()
              .height(8.dp)
              .clip(CircleShape),
            color = if (progress > 0.85f) SproutPrimary else SproutSecondary,
            trackColor = MaterialTheme.colorScheme.surfaceContainer
          )
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

private fun onOpenLogLogCategory(
  viewModel: SproutViewModel,
  category: String,
  onOpenLogSheet: (String) -> Unit
) {
  viewModel.setCategory(category)
  onOpenLogSheet(category)
}

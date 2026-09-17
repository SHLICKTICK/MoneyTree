package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Spa
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
import com.example.ui.theme.SproutBadgeGreenBg
import com.example.ui.theme.SproutBadgeYellowBg
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutSecondary
import java.util.Locale

@Composable
fun InsightsScreen(
  viewModel: SproutViewModel,
  onNavigateBack: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  val transactions by viewModel.transactions.collectAsState()

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
            modifier = Modifier.testTag("insights_back_button")
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
            text = "Mindful Insights",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Actionable observations to build calm, healthy money habits.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    item {
      // Daily Average Spending
      Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("insights_daily_average")
      ) {
        Column(
          modifier = Modifier.padding(18.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Daily Safe Spend",
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
              shape = CircleShape,
              color = SproutBadgeGreenBg
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Eco,
                  contentDescription = null,
                  tint = SproutSecondary,
                  modifier = Modifier.size(13.dp)
                )
                Text(
                  text = "Healthy Pace",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = SproutSecondary
                )
              }
            }
          }

          Text(
            text = "R41.90 / day",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "You are spending 14% less than last month at this point in the cycle.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    item {
      // Category Breakdown Card
      Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier.padding(18.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Text(
            text = "Top Spending Categories",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )

          val categories = listOf(
            Triple("Groceries", 0.42f, "R342.80"),
            Triple("Dining", 0.24f, "R186.50"),
            Triple("Transport", 0.16f, "R124.00"),
            Triple("Shopping", 0.12f, "R98.50"),
            Triple("Coffee", 0.06f, "R48.90")
          )

          categories.forEach { (cat, pct, amt) ->
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = cat,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = amt,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
              LinearProgressIndicator(
                progress = { pct },
                modifier = Modifier
                  .fillMaxWidth()
                  .height(6.dp)
                  .clip(CircleShape),
                color = if (cat == "Groceries") SproutSecondary else SproutPrimary,
                trackColor = MaterialTheme.colorScheme.surfaceContainer
              )
            }
          }
        }
      }
    }

    item {
      // Mindful Tip Card
      Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
          containerColor = SproutBadgeYellowBg.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            modifier = Modifier.size(40.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Spa,
                contentDescription = null,
                tint = SproutPrimary,
                modifier = Modifier.size(22.dp)
              )
            }
          }

          Column {
            Text(
              text = "Mindful Pause",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "You have stayed within your Groceries envelope for 3 consecutive weeks! That gave your Rainy Day Fund +R85 extra.",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 16.sp
            )
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

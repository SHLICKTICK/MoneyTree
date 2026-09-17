package com.example.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.SproutViewModel
import com.example.ui.theme.SproutBadgeGreenBg
import com.example.ui.theme.SproutBadgeYellowBg
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutPrimaryFixed
import com.example.ui.theme.SproutSecondary

@Composable
fun ProfileScreen(
  viewModel: SproutViewModel,
  onOpenOnboarding: () -> Unit,
  onNavigateBack: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
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
            modifier = Modifier.testTag("profile_back_button")
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
            text = "Profile & Settings",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Manage your connected accounts, envelopes, and mindful habits.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    item {
      // User Profile Card
      Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(18.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = SproutPrimaryFixed,
            modifier = Modifier.size(54.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Text(
                text = "A",
                color = SproutPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
              )
            }
          }

          Column {
            Text(
              text = "Alex River",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "alex.river@example.com",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
              shape = CircleShape,
              color = SproutBadgeGreenBg,
              modifier = Modifier.padding(top = 6.dp)
            ) {
              Text(
                text = "Mindful Member • Active",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SproutSecondary,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
              )
            }
          }
        }
      }
    }

    item {
      Text(
        text = "Connected Accounts",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(top = 4.dp)
      )
    }

    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
          // Account 1
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier.size(40.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.AccountBalance,
                    contentDescription = null,
                    tint = SproutPrimary,
                    modifier = Modifier.size(20.dp)
                  )
                }
              }
              Column {
                Text(
                  text = "Everyday Checking (...4021)",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = "Primary spending account",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Text(
              text = "R3,210.00",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = SproutSecondary
            )
          }

          androidx.compose.material3.HorizontalDivider(
            color = MaterialTheme.colorScheme.surfaceContainer
          )

          // Account 2
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier.size(40.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.Savings,
                    contentDescription = null,
                    tint = SproutSecondary,
                    modifier = Modifier.size(20.dp)
                  )
                }
              }
              Column {
                Text(
                  text = "High-Yield Savings (...8829)",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = "Rainy Day & long-term goals",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Text(
              text = "R8,450.00",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = SproutSecondary
            )
          }
        }
      }
    }

    item {
      Text(
        text = "Preferences & Exploration",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(top = 4.dp)
      )
    }

    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
          // Re-visit Onboarding
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable(onClick = onOpenOnboarding)
              .padding(vertical = 12.dp)
              .testTag("profile_view_onboarding"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Surface(
                shape = CircleShape,
                color = SproutBadgeYellowBg,
                modifier = Modifier.size(36.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null,
                    tint = SproutPrimary,
                    modifier = Modifier.size(18.dp)
                  )
                }
              }
              Column {
                Text(
                  text = "Meet Sprout (Onboarding Tour)",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = "Review the mindful budgeting principles",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Icon(
              imageVector = Icons.Default.ChevronRight,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.outlineVariant
            )
          }

          androidx.compose.material3.HorizontalDivider(
            color = MaterialTheme.colorScheme.surfaceContainer
          )

          // Reset to Sample Data
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.resetToSampleData() }
              .padding(vertical = 12.dp)
              .testTag("profile_reset_data"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                modifier = Modifier.size(36.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                  )
                }
              }
              Column {
                Text(
                  text = "Reset Sample Transactions",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = "Restore initial Trader Joe's & coffee expenses",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Icon(
              imageVector = Icons.Default.ChevronRight,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.outlineVariant
            )
          }
        }
      }
    }

    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Security,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.outline,
          modifier = Modifier.size(14.dp)
        )
        Text(
          text = "  Sprout v1.0 • Bank-grade 256-bit encryption",
          fontSize = 11.sp,
          color = MaterialTheme.colorScheme.outline
        )
      }
    }

    item {
      Spacer(modifier = Modifier.height(80.dp))
    }
  }
}

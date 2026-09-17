package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutPrimaryContainer

@Composable
fun SproutBottomNav(
  currentScreen: String,
  onScreenSelected: (String) -> Unit,
  onAddClicked: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .navigationBarsPadding()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    contentAlignment = Alignment.Center
  ) {
    Surface(
      shape = RoundedCornerShape(32.dp),
      color = MaterialTheme.colorScheme.surfaceContainerLowest,
      shadowElevation = 8.dp,
      border = androidx.compose.foundation.BorderStroke(
        1.dp,
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f)
      ),
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 420.dp)
        .height(64.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Home Tab
        NavItem(
          label = "Home",
          selectedIcon = Icons.Filled.Home,
          unselectedIcon = Icons.Outlined.Home,
          isSelected = currentScreen == "home",
          onClick = { onScreenSelected("home") }
        )

        // Budgets Tab
        NavItem(
          label = "Budgets",
          selectedIcon = Icons.Filled.AccountBalanceWallet,
          unselectedIcon = Icons.Outlined.AccountBalanceWallet,
          isSelected = currentScreen == "budgets",
          onClick = { onScreenSelected("budgets") }
        )

        // Center "Add" Action Button aligned with the rest of the nav items
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onAddClicked)
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .testTag("nav_add_button")
        ) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Log Transaction",
            tint = SproutPrimary,
            modifier = Modifier.size(22.dp)
          )
          Text(
            text = "Add",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = SproutPrimary
          )
        }

        // Insights Tab
        NavItem(
          label = "Insights",
          selectedIcon = Icons.Filled.Insights,
          unselectedIcon = Icons.Outlined.Insights,
          isSelected = currentScreen == "insights",
          onClick = { onScreenSelected("insights") }
        )

        // Profile Tab
        NavItem(
          label = "Profile",
          selectedIcon = Icons.Filled.Person,
          unselectedIcon = Icons.Outlined.Person,
          isSelected = currentScreen == "profile",
          onClick = { onScreenSelected("profile") }
        )
      }
    }
  }
}

@Composable
private fun NavItem(
  label: String,
  selectedIcon: ImageVector,
  unselectedIcon: ImageVector,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
      .clip(RoundedCornerShape(16.dp))
      .clickable(onClick = onClick)
      .padding(horizontal = 10.dp, vertical = 6.dp)
      .testTag("nav_${label.lowercase()}")
  ) {
    Icon(
      imageVector = if (isSelected) selectedIcon else unselectedIcon,
      contentDescription = label,
      tint = if (isSelected) SproutPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
      modifier = Modifier.size(22.dp)
    )
    Text(
      text = label,
      fontSize = 11.sp,
      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
      color = if (isSelected) SproutPrimary else MaterialTheme.colorScheme.onSurfaceVariant
    )
  }
}

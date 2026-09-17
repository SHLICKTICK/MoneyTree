package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SproutBadgeGreenBg
import com.example.ui.theme.SproutBadgeGreenText
import com.example.ui.theme.SproutBadgeOrangeBg
import com.example.ui.theme.SproutBadgeOrangeText
import com.example.ui.theme.SproutBadgeYellowBg
import com.example.ui.theme.SproutBadgeYellowText
import com.example.ui.theme.SproutBackground
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutPrimaryContainer
import com.example.ui.theme.SproutPrimaryFixed
import com.example.ui.theme.SproutSecondary
import com.example.ui.theme.SproutSecondaryFixed

@Composable
fun WelcomeScreen(
  onContinue: () -> Unit,
  onExploreFeatures: () -> Unit = onContinue
) {
  val scrollState = rememberScrollState()

  // Pulsing ambient glow
  val infiniteTransition = rememberInfiniteTransition(label = "ambientPulse")
  val pulseAlpha by infiniteTransition.animateFloat(
    initialValue = 0.35f,
    targetValue = 0.75f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseAlpha"
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(SproutBackground)
      .testTag("welcome_screen")
  ) {
    // Ambient Decorative Glows
    Box(
      modifier = Modifier
        .size(280.dp)
        .offset(x = (-80).dp, y = (-60).dp)
        .background(
          brush = Brush.radialGradient(
            colors = listOf(SproutPrimaryFixed.copy(alpha = pulseAlpha), Color.Transparent)
          ),
          shape = CircleShape
        )
    )
    Box(
      modifier = Modifier
        .size(240.dp)
        .align(Alignment.TopEnd)
        .offset(x = 80.dp, y = 100.dp)
        .background(
          brush = Brush.radialGradient(
            colors = listOf(SproutSecondaryFixed.copy(alpha = 0.35f), Color.Transparent)
          ),
          shape = CircleShape
        )
    )

    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
    ) {
      // Main Scrollable Welcome Body
      Column(
        modifier = Modifier
          .weight(1f)
          .verticalScroll(scrollState)
          .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
      ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Hero Sprout Brand Icon with soft layered rings
        Box(contentAlignment = Alignment.Center) {
          Surface(
            shape = CircleShape,
            color = SproutPrimaryFixed.copy(alpha = 0.5f),
            modifier = Modifier.size(96.dp)
          ) {}
          Surface(
            shape = CircleShape,
            color = SproutPrimaryContainer,
            shadowElevation = 6.dp,
            modifier = Modifier.size(72.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Spa,
                contentDescription = "Sprout Emblem",
                tint = Color.White,
                modifier = Modifier.size(38.dp)
              )
            }
          }
        }

        // Welcome Headline & Tagline
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = SproutBadgeGreenBg,
            modifier = Modifier.padding(bottom = 2.dp)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.EnergySavingsLeaf,
                contentDescription = null,
                tint = SproutBadgeGreenText,
                modifier = Modifier.size(14.dp)
              )
              Text(
                text = "South African Rand (ZAR)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SproutBadgeGreenText
              )
            }
          }

          Text(
            text = "Welcome to Sprout",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface,
            letterSpacing = (-0.8).sp,
            textAlign = TextAlign.Center
          )

          Text(
            text = "Mindful, stress-free money management. Grow your savings in South African Rands with calm daily allowances.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 21.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
          )
        }

        // Hero Preview Card (Interactive Bento Summary in ZAR)
        Surface(
          shape = RoundedCornerShape(26.dp),
          color = MaterialTheme.colorScheme.surfaceContainerLowest,
          shadowElevation = 4.dp,
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Today's Safe Allowance",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Surface(
                shape = CircleShape,
                color = SproutBadgeGreenBg
              ) {
                Text(
                  text = "On Track",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = SproutSecondary,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
              }
            }

            Text(
              text = "R450.00",
              fontSize = 34.sp,
              fontWeight = FontWeight.ExtraBold,
              color = SproutSecondary,
              letterSpacing = (-1).sp
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              // Bento sub-card 1
              Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                  containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
                modifier = Modifier.weight(1f)
              ) {
                Column(modifier = Modifier.padding(12.dp)) {
                  Text(
                    text = "Rainy Day Fund",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                  Text(
                    text = "R8,450.00",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                }
              }

              // Bento sub-card 2
              Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                  containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
                modifier = Modifier.weight(1f)
              ) {
                Column(modifier = Modifier.padding(12.dp)) {
                  Text(
                    text = "Monthly Cap",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                  Text(
                    text = "R3,100.00",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = SproutPrimary
                  )
                }
              }
            }
          }
        }

        // Feature Highlights
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          WelcomeFeatureRow(
            icon = Icons.Default.AccountBalanceWallet,
            iconBg = SproutBadgeOrangeBg,
            iconColor = SproutBadgeOrangeText,
            title = "Native Keyboard Entry",
            description = "Quickly log purchases with your device's decimal keyboard."
          )

          WelcomeFeatureRow(
            icon = Icons.Default.TrendingUp,
            iconBg = SproutBadgeGreenBg,
            iconColor = SproutBadgeGreenText,
            title = "Flexible Envelopes",
            description = "Groceries, Dining, and Transport caps tailored in Rands."
          )

          WelcomeFeatureRow(
            icon = Icons.Default.Security,
            iconBg = SproutBadgeYellowBg,
            iconColor = SproutBadgeYellowText,
            title = "Private & Offline First",
            description = "Your financial data stays securely stored on your device."
          )
        }
      }

      // Sticky Bottom Call-to-Action
      Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLowest,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 14.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Button(
            onClick = onContinue,
            colors = ButtonDefaults.buttonColors(
              containerColor = SproutPrimaryContainer,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("welcome_continue_button")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(
                text = "Continue to Sprout",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
              )
            }
          }

          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Lock,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.outline,
              modifier = Modifier.size(13.dp)
            )
            Text(
              text = "100% Local • Private • Encrypted",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.outline
            )
          }
        }
      }
    }
  }
}

@Composable
private fun WelcomeFeatureRow(
  icon: ImageVector,
  iconBg: Color,
  iconColor: Color,
  title: String,
  description: String
) {
  Surface(
    shape = RoundedCornerShape(18.dp),
    color = MaterialTheme.colorScheme.surfaceContainerLowest,
    shadowElevation = 1.dp,
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      Surface(
        shape = CircleShape,
        color = iconBg,
        modifier = Modifier.size(42.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
          )
        }
      }

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = description,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          lineHeight = 16.sp
        )
      }

      Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = null,
        tint = SproutSecondary,
        modifier = Modifier.size(18.dp)
      )
    }
  }
}

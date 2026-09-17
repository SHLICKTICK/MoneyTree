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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.theme.SproutBadgeGreenBg
import com.example.ui.theme.SproutBadgeGreenText
import com.example.ui.theme.SproutBadgeOrangeBg
import com.example.ui.theme.SproutBadgeOrangeText
import com.example.ui.theme.SproutBadgePurpleBg
import com.example.ui.theme.SproutBadgePurpleText
import com.example.ui.theme.SproutBadgeYellowBg
import com.example.ui.theme.SproutBadgeYellowText
import com.example.ui.theme.SproutBackground
import com.example.ui.theme.SproutPrimary
import com.example.ui.theme.SproutPrimaryContainer
import com.example.ui.theme.SproutPrimaryFixed
import com.example.ui.theme.SproutSecondary
import com.example.ui.theme.SproutSecondaryFixed

@Composable
fun OnboardingScreen(
  onGetStarted: () -> Unit,
  onSkip: () -> Unit
) {
  val scrollState = rememberScrollState()

  // Pulsing animation for the Rainy Day Fund indicator
  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseAlpha by infiniteTransition.animateFloat(
    initialValue = 0.4f,
    targetValue = 1.0f,
    animationSpec = infiniteRepeatable(
      animation = tween(800, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseAlpha"
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(SproutBackground)
  ) {
    // Ambient Top Glow Organic Auras
    Box(
      modifier = Modifier
        .size(240.dp)
        .offset(x = (-60).dp, y = (-50).dp)
        .background(
          brush = Brush.radialGradient(
            colors = listOf(SproutPrimaryFixed.copy(alpha = 0.5f), Color.Transparent)
          ),
          shape = CircleShape
        )
    )
    Box(
      modifier = Modifier
        .size(200.dp)
        .align(Alignment.TopEnd)
        .offset(x = 60.dp, y = 140.dp)
        .background(
          brush = Brush.radialGradient(
            colors = listOf(SproutSecondaryFixed.copy(alpha = 0.4f), Color.Transparent)
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
      // Header: Brand and Skip
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = SproutPrimaryContainer,
            modifier = Modifier.size(40.dp),
            shadowElevation = 2.dp
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Spa,
                contentDescription = "Sprout Logo",
                tint = Color.White,
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
                text = "Sprout",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = (-0.5).sp
              )
              Surface(
                shape = CircleShape,
                color = SproutPrimaryFixed,
                modifier = Modifier.padding(top = 1.dp)
              ) {
                Text(
                  text = "PWA",
                  color = SproutPrimary,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                )
              }
            }
            Text(
              text = "Meet Sprout",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontWeight = FontWeight.Medium
            )
          }
        }

        Surface(
          shape = CircleShape,
          color = MaterialTheme.colorScheme.surfaceContainerLow,
          modifier = Modifier
            .clip(CircleShape)
            .clickable(onClick = onSkip)
            .testTag("skip_button")
        ) {
          Text(
            text = "Skip",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      // Scrollable Content
      Column(
        modifier = Modifier
          .weight(1f)
          .verticalScroll(scrollState)
          .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Hero Interactive Card Composition
        Surface(
          shape = RoundedCornerShape(28.dp),
          color = MaterialTheme.colorScheme.surfaceContainerLowest,
          shadowElevation = 4.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            // Upper Floating Chips
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = SproutBadgeGreenBg
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = SproutBadgeGreenText,
                    modifier = Modifier.size(15.dp)
                  )
                  Text(
                    text = "+18% saved",
                    color = SproutBadgeGreenText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }

              Surface(
                shape = CircleShape,
                color = SproutBadgeYellowBg
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null,
                    tint = SproutBadgeYellowText,
                    modifier = Modifier.size(14.dp)
                  )
                  Text(
                    text = "Stress-free budgeting",
                    color = SproutBadgeYellowText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }

            // Illustrated Hero Image Scene with Coil + Overlay Pill
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            ) {
              AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuB3YVF05dRhrbdRndrtk-L9FqKSlzc_JXTrovw3wvmB148DeQP1FPDJB9iNdtIszei7AvFzlwSMj10y_NQCZ6Jv9UMocK4RIP4xyaFVDaA26jMG1vRACutP0eHJW8PSNrb2N65BhgzgxkCNCC4fklp9UzxstXUC06t7bTzEiOPuAIZqA31mtAU5125vs88w2zqT0ZmlPVlBT9MvR4P5NT3bAW6g9fyq5wPmVyRnuYZIKcNDbUucirJWwg",
                contentDescription = "Sprout Mindful Plant and Coins Illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
              )

              // Soft bottom gradient
              Box(
                modifier = Modifier
                  .fillMaxSize()
                  .background(
                    Brush.verticalGradient(
                      colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.35f))
                    )
                  )
              )

              // Tactile Floating Pill Inside Hero
              Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.92f),
                shadowElevation = 3.dp,
                modifier = Modifier
                  .align(Alignment.BottomCenter)
                  .padding(10.dp)
                  .fillMaxWidth()
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                  ) {
                    Box(
                      modifier = Modifier
                        .size(9.dp)
                        .background(
                          color = Color(0xFF10B981).copy(alpha = pulseAlpha),
                          shape = CircleShape
                        )
                    )
                    Text(
                      text = "Rainy Day Fund",
                      fontSize = 12.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  }

                  Text(
                    text = "R1,450.00",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = SproutSecondary
                  )
                }
              }
            }

            // Daily Gauge Progress Module
            Surface(
              shape = RoundedCornerShape(14.dp),
              color = MaterialTheme.colorScheme.surfaceContainerLow,
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "Today's Mindful Balance",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = "R42.50 left",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = SproutSecondary
                  )
                }
                LinearProgressIndicator(
                  progress = { 0.65f },
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                  color = SproutSecondary,
                  trackColor = MaterialTheme.colorScheme.surfaceContainerHighest
                )
              }
            }
          }
        }

        // Copy and Carousel Dots
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = "Budgeting that feels like second nature",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 30.sp
          )
          Text(
            text = "Track expenses effortlessly, set flexible envelopes, and watch your rainy day funds grow without the stress.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
          )

          // Step Indicators
          Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp)
          ) {
            Box(
              modifier = Modifier
                .width(22.dp)
                .height(6.dp)
                .background(SproutPrimaryContainer, CircleShape)
            )
            Box(
              modifier = Modifier
                .size(6.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHighest, CircleShape)
            )
            Box(
              modifier = Modifier
                .size(6.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHighest, CircleShape)
            )
          }
        }

        // Tactile Feature Highlights (Bento Cards)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          FeatureCard(
            icon = Icons.Default.AccountBalanceWallet,
            iconBg = SproutBadgePurpleBg,
            iconColor = SproutBadgePurpleText,
            title = "Smart Envelopes",
            subtitle = "Flexible spending caps that adjust with your life."
          )
          FeatureCard(
            icon = Icons.Default.SentimentSatisfied,
            iconBg = SproutBadgeGreenBg,
            iconColor = SproutBadgeGreenText,
            title = "Zero-Stress Daily Allowance",
            subtitle = "Always know exactly what is safe to spend today."
          )
          FeatureCard(
            icon = Icons.Default.WifiOff,
            iconBg = SproutBadgeOrangeBg,
            iconColor = SproutBadgeOrangeText,
            title = "Offline-Ready PWA",
            subtitle = "Log coffees and split bills anywhere, spotty Wi-Fi ok."
          )
        }
      }

      // Bottom Action Area
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Button(
          onClick = onGetStarted,
          colors = ButtonDefaults.buttonColors(
            containerColor = SproutPrimaryContainer,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(26.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("get_started_button")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(
              text = "Get Started with Sprout",
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

        Text(
          text = "I already have an account",
          fontSize = 14.sp,
          fontWeight = FontWeight.SemiBold,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          modifier = Modifier
            .clickable(onClick = onSkip)
            .padding(vertical = 4.dp)
            .testTag("existing_account_button")
        )

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(5.dp),
          modifier = Modifier.padding(top = 2.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(12.dp)
          )
          Text(
            text = "Bank-grade 256-bit encryption • Read-only sync",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.outline
          )
        }
      }
    }
  }
}

@Composable
private fun FeatureCard(
  icon: ImageVector,
  iconBg: Color,
  iconColor: Color,
  title: String,
  subtitle: String
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
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      Surface(
        shape = RoundedCornerShape(14.dp),
        color = iconBg,
        modifier = Modifier.size(44.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(22.dp)
          )
        }
      }

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = subtitle,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          lineHeight = 16.sp
        )
      }

      Icon(
        imageVector = Icons.Default.ChevronRight,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.outlineVariant,
        modifier = Modifier.size(20.dp)
      )
    }
  }
}

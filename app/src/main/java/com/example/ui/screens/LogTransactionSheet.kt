package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsSubway
import androidx.compose.material.icons.filled.EventRepeat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.SproutViewModel
import com.example.ui.theme.SproutBadgeBlueBg
import com.example.ui.theme.SproutBadgeBlueText
import com.example.ui.theme.SproutBadgeGreenBg
import com.example.ui.theme.SproutBadgeGreenText
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
import com.example.ui.theme.SproutSecondaryFixed

@Composable
fun LogTransactionSheet(
  viewModel: SproutViewModel,
  onDismiss: () -> Unit
) {
  val currentAmount by viewModel.currentAmount.collectAsState()
  val selectedType by viewModel.selectedType.collectAsState()
  val merchantInput by viewModel.merchantInput.collectAsState()
  val selectedCategory by viewModel.selectedCategory.collectAsState()
  val selectedAccount by viewModel.selectedAccount.collectAsState()
  val dateDisplay by viewModel.dateDisplay.collectAsState()
  val isSplit by viewModel.isSplit.collectAsState()
  val isRecurring by viewModel.isRecurring.collectAsState()
  val isSavedSuccess by viewModel.saveSuccessEvent.collectAsState()

  val scrollState = rememberScrollState()
  val amountFocusRequester = remember { FocusRequester() }
  val focusManager = LocalFocusManager.current

  // Automatically request focus on amount input when sheet opens
  LaunchedEffect(Unit) {
    amountFocusRequester.requestFocus()
  }

  // Blinking cursor
  val infiniteTransition = rememberInfiniteTransition(label = "cursor")
  val cursorAlpha by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(500),
      repeatMode = RepeatMode.Reverse
    ),
    label = "cursorAlpha"
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black.copy(alpha = 0.45f))
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onDismiss
      )
  ) {
    // Interactive Sheet Canvas
    Surface(
      shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp),
      color = MaterialTheme.colorScheme.surfaceContainerLowest,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
        .align(Alignment.BottomCenter)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null,
          onClick = {} // Consume clicks inside sheet
        )
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .navigationBarsPadding()
      ) {
        // Drag Handle
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 4.dp),
          contentAlignment = Alignment.Center
        ) {
          Box(
            modifier = Modifier
              .width(44.dp)
              .height(6.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.surfaceContainerHighest)
          )
        }

        // Modal Header: Close, Type Switcher, Receipt Scan
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          IconButton(
            onClick = onDismiss,
            modifier = Modifier
              .size(40.dp)
              .testTag("close_sheet_button")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          // Segmented Pill Switcher
          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainer,
            modifier = Modifier.padding(1.dp)
          ) {
            Row(
              modifier = Modifier.padding(3.dp),
              horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
              listOf("Expense", "Income", "Transfer").forEach { type ->
                val isSelected = selectedType == type
                Surface(
                  shape = CircleShape,
                  color = if (isSelected) SproutPrimaryContainer else Color.Transparent,
                  shadowElevation = if (isSelected) 1.dp else 0.dp,
                  modifier = Modifier
                    .clip(CircleShape)
                    .clickable { viewModel.setType(type) }
                    .testTag("type_${type.lowercase()}")
                ) {
                  Text(
                    text = type,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                  )
                }
              }
            }
          }

          IconButton(
            onClick = { /* Quick trigger receipt */ },
            modifier = Modifier
              .size(40.dp)
              .testTag("receipt_button")
          ) {
            Icon(
              imageVector = Icons.Default.ReceiptLong,
              contentDescription = "Receipt",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        // Scrollable Body Content
        Column(
          modifier = Modifier
            .weight(1f)
            .verticalScroll(scrollState)
            .padding(horizontal = 18.dp, vertical = 4.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          // AMOUNT Hero Entry Display with Native Device Keyboard
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "AMOUNT (ZAR)",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.2.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Native Device Keyboard Input for Amount
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center,
              modifier = Modifier
                .clickable { amountFocusRequester.requestFocus() }
                .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
              Text(
                text = "R",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = SproutPrimaryContainer,
                modifier = Modifier.padding(end = 4.dp)
              )

              BasicTextField(
                value = currentAmount,
                onValueChange = { viewModel.setAmount(it) },
                textStyle = TextStyle(
                  color = MaterialTheme.colorScheme.onSurface,
                  fontSize = 40.sp,
                  fontWeight = FontWeight.ExtraBold,
                  letterSpacing = (-1).sp,
                  textAlign = TextAlign.Start
                ),
                keyboardOptions = KeyboardOptions(
                  keyboardType = KeyboardType.Decimal,
                  imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                  onNext = { focusManager.clearFocus() }
                ),
                singleLine = true,
                cursorBrush = SolidColor(SproutPrimaryContainer),
                modifier = Modifier
                  .focusRequester(amountFocusRequester)
                  .testTag("amount_display")
                  .testTag("amount_input"),
                decorationBox = { innerTextField ->
                  Box(contentAlignment = Alignment.CenterStart) {
                    if (currentAmount.isEmpty()) {
                      Text(
                        text = "0.00",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        letterSpacing = (-1).sp
                      )
                    }
                    innerTextField()
                  }
                }
              )
            }

            // Quick Preset Chips (in ZAR)
            Row(
              modifier = Modifier
                .padding(top = 8.dp)
                .horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              PresetChip(label = "+R5") { viewModel.onQuickAdd(5.0) }
              PresetChip(label = "+R10") { viewModel.onQuickAdd(10.0) }
              PresetChip(label = "+R20") { viewModel.onQuickAdd(20.0) }
              PresetChip(label = "+R50") { viewModel.onQuickAdd(50.0) }

              Surface(
                shape = CircleShape,
                color = SproutPrimaryFixed.copy(alpha = 0.6f),
                modifier = Modifier
                  .clip(CircleShape)
                  .clickable { viewModel.onRoundUp() }
                  .testTag("round_up_chip")
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.NorthEast,
                    contentDescription = null,
                    tint = SproutPrimary,
                    modifier = Modifier.size(13.dp)
                  )
                  Text(
                    text = "Round up",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SproutPrimary
                  )
                }
              }
            }
          }

          // Merchant / Description Input
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              MaterialTheme.colorScheme.surfaceContainerHighest
            ),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 11.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Storefront,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
              )
              BasicTextField(
                value = merchantInput,
                onValueChange = { viewModel.setMerchant(it) },
                textStyle = TextStyle(
                  color = MaterialTheme.colorScheme.onSurface,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Medium
                ),
                cursorBrush = SolidColor(SproutPrimaryContainer),
                modifier = Modifier
                  .weight(1f)
                  .testTag("merchant_input"),
                decorationBox = { innerTextField ->
                  if (merchantInput.isEmpty()) {
                    Text(
                      text = "Merchant or description",
                      color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                      fontSize = 15.sp
                    )
                  }
                  innerTextField()
                }
              )
              if (merchantInput.isNotEmpty()) {
                Icon(
                  imageVector = Icons.Default.Cancel,
                  contentDescription = "Clear",
                  tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                  modifier = Modifier
                    .size(18.dp)
                    .clickable { viewModel.clearMerchant() }
                    .testTag("clear_merchant_button")
                )
              }
            }
          }

          // Category Picker (Horizontal Carousel)
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Select Category",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = "$selectedCategory selected",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SproutPrimary
              )
            }

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              CategoryItem(
                category = "Groceries",
                icon = Icons.Default.ShoppingBasket,
                bg = SproutBadgeGreenBg,
                iconColor = SproutSecondary,
                isSelected = selectedCategory == "Groceries",
                onSelect = { viewModel.setCategory("Groceries") }
              )
              CategoryItem(
                category = "Dining",
                icon = Icons.Default.Restaurant,
                bg = SproutBadgeOrangeBg,
                iconColor = SproutBadgeOrangeText,
                isSelected = selectedCategory == "Dining",
                onSelect = { viewModel.setCategory("Dining") }
              )
              CategoryItem(
                category = "Coffee",
                icon = Icons.Default.LocalCafe,
                bg = SproutBadgeYellowBg,
                iconColor = SproutBadgeYellowText,
                isSelected = selectedCategory == "Coffee",
                onSelect = { viewModel.setCategory("Coffee") }
              )
              CategoryItem(
                category = "Transport",
                icon = Icons.Default.DirectionsSubway,
                bg = SproutBadgeBlueBg,
                iconColor = SproutBadgeBlueText,
                isSelected = selectedCategory == "Transport",
                onSelect = { viewModel.setCategory("Transport") }
              )
              CategoryItem(
                category = "Shopping",
                icon = Icons.Default.ShoppingBag,
                bg = SproutBadgePurpleBg,
                iconColor = SproutBadgePurpleText,
                isSelected = selectedCategory == "Shopping",
                onSelect = { viewModel.setCategory("Shopping") }
              )
              CategoryItem(
                category = "Health",
                icon = Icons.Default.Favorite,
                bg = SproutBadgePinkBg,
                iconColor = SproutBadgePinkText,
                isSelected = selectedCategory == "Health",
                onSelect = { viewModel.setCategory("Health") }
              )
              CategoryItem(
                category = "More",
                icon = Icons.Default.MoreHoriz,
                bg = MaterialTheme.colorScheme.surfaceContainer,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                isSelected = selectedCategory == "More",
                onSelect = { viewModel.setCategory("More") }
              )
            }
          }

          // Payment Method / Account Selector
          Surface(
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.8f),
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("account_selector")
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = MaterialTheme.colorScheme.surfaceContainerLowest,
                  border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                  ),
                  shadowElevation = 1.dp,
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
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text(
                      text = selectedAccount,
                      fontSize = 14.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                      imageVector = Icons.Default.KeyboardArrowDown,
                      contentDescription = null,
                      tint = MaterialTheme.colorScheme.onSurfaceVariant,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                  Text(
                    text = "R3,210.00 available",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SproutSecondary
                  )
                }
              }

              Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                border = androidx.compose.foundation.BorderStroke(
                  1.dp,
                  MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                )
              ) {
                Text(
                  text = "Default",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Medium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
              }
            }
          }

          // Bento Options: Date & Split with friends
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            // Date Picker Pill
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = MaterialTheme.colorScheme.surfaceContainerLow,
              border = androidx.compose.foundation.BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)
              ),
              modifier = Modifier
                .weight(1f)
                .clickable { /* Toggle date */ }
                .testTag("date_picker_button")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.CalendarToday,
                  contentDescription = null,
                  tint = SproutPrimaryContainer,
                  modifier = Modifier.size(18.dp)
                )
                Column {
                  Text(
                    text = "Date",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                  Text(
                    text = dateDisplay,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }

            // Split with Friends Button
            val splitBg by animateColorAsState(
              targetValue = if (isSplit) SproutSecondaryFixed.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceContainerLow,
              label = "splitBg"
            )
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = splitBg,
              border = androidx.compose.foundation.BorderStroke(
                1.dp,
                if (isSplit) SproutSecondary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)
              ),
              modifier = Modifier
                .weight(1f)
                .clickable { viewModel.toggleSplit() }
                .testTag("split_button")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                  Surface(
                    shape = CircleShape,
                    color = SproutSecondaryFixed.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                  ) {
                    Box(contentAlignment = Alignment.Center) {
                      Icon(
                        imageVector = Icons.Default.GroupAdd,
                        contentDescription = null,
                        tint = SproutSecondary,
                        modifier = Modifier.size(14.dp)
                      )
                    }
                  }
                  Column {
                    Text(
                      text = "Split",
                      fontSize = 11.sp,
                      color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                      text = "With friends",
                      fontSize = 13.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  }
                }

                Box(
                  modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (isSplit) SproutSecondary else MaterialTheme.colorScheme.outlineVariant)
                )
              }
            }
          }

          // Make Recurring Monthly
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)
            ),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.EventRepeat,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(18.dp)
                )
                Column {
                  Text(
                    text = "Make recurring monthly",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = "Auto-logs on the 24th of each month",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }

              Switch(
                checked = isRecurring,
                onCheckedChange = { viewModel.setRecurring(it) },
                colors = SwitchDefaults.colors(
                  checkedThumbColor = Color.White,
                  checkedTrackColor = SproutPrimaryContainer,
                  uncheckedThumbColor = Color.White,
                  uncheckedTrackColor = MaterialTheme.colorScheme.surfaceContainerHighest
                ),
                modifier = Modifier.testTag("recurring_switch")
              )
            }
          }

          // Spacing for comfortable bottom scrolling with device keyboard
          Spacer(modifier = Modifier.height(32.dp))
        }

        // Sticky Bottom CTA Action
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLowest,
          shadowElevation = 8.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 18.dp, vertical = 12.dp)
          ) {
            val ctaButtonColor by animateColorAsState(
              targetValue = if (isSavedSuccess) SproutSecondary else SproutPrimaryContainer,
              label = "ctaColor"
            )

            Button(
              onClick = { viewModel.saveCurrentTransaction() },
              colors = ButtonDefaults.buttonColors(
                containerColor = ctaButtonColor,
                contentColor = Color.White
              ),
              shape = RoundedCornerShape(26.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("save_transaction_button")
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Icon(
                  imageVector = if (isSavedSuccess) Icons.Default.TaskAlt else Icons.Default.CheckCircle,
                  contentDescription = null,
                  modifier = Modifier.size(20.dp)
                )
                Text(
                  text = if (isSavedSuccess) "Transaction Recorded!" else "Save Transaction (R${currentAmount.ifEmpty { "0.00" }})",
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun PresetChip(label: String, onClick: () -> Unit) {
  Surface(
    shape = CircleShape,
    color = MaterialTheme.colorScheme.surfaceContainer,
    modifier = Modifier
      .clip(CircleShape)
      .clickable(onClick = onClick)
      .testTag("preset_$label")
  ) {
    Text(
      text = label,
      fontSize = 12.sp,
      fontWeight = FontWeight.SemiBold,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
    )
  }
}

@Composable
private fun CategoryItem(
  category: String,
  icon: ImageVector,
  bg: Color,
  iconColor: Color,
  isSelected: Boolean,
  onSelect: () -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(6.dp),
    modifier = Modifier
      .clickable(onClick = onSelect)
      .testTag("category_$category")
  ) {
    Box(
      modifier = Modifier
        .size(54.dp)
        .clip(CircleShape)
        .background(bg)
        .then(
          if (isSelected) {
            Modifier.border(2.dp, SproutPrimaryContainer, CircleShape)
          } else {
            Modifier
          }
        ),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = category,
        tint = iconColor,
        modifier = Modifier.size(24.dp)
      )

      if (isSelected) {
        Surface(
          shape = CircleShape,
          color = SproutPrimaryContainer,
          modifier = Modifier
            .size(16.dp)
            .align(Alignment.BottomEnd)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(11.dp)
            )
          }
        }
      }
    }

    Text(
      text = category,
      fontSize = 12.sp,
      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
      color = if (isSelected) SproutPrimary else MaterialTheme.colorScheme.onSurfaceVariant
    )
  }
}

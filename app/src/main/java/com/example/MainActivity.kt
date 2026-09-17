package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.SproutViewModel
import com.example.ui.components.SproutBottomNav
import com.example.ui.screens.BudgetsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.InsightsScreen
import com.example.ui.screens.LogTransactionSheet
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.WelcomeScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        SproutApp()
      }
    }
  }
}

@Composable
fun SproutApp(viewModel: SproutViewModel = viewModel()) {
  val currentScreen by viewModel.currentScreen.collectAsState()
  val isLogModalOpen by viewModel.isLogModalOpen.collectAsState()

  BackHandler(enabled = isLogModalOpen || (currentScreen != "home" && currentScreen != "welcome")) {
    if (isLogModalOpen) {
      viewModel.closeLogModal()
    } else if (currentScreen != "home" && currentScreen != "welcome") {
      viewModel.navigateTo("home")
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    if (currentScreen == "welcome") {
      WelcomeScreen(
        onContinue = { viewModel.navigateTo("home") },
        onExploreFeatures = { viewModel.navigateTo("onboarding") }
      )
    } else if (currentScreen == "onboarding") {
      OnboardingScreen(
        onGetStarted = { viewModel.navigateTo("home") },
        onSkip = { viewModel.navigateTo("home") }
      )
    } else {
      // Main App Content with persistent bottom navigation
      Box(modifier = Modifier.fillMaxSize()) {
        when (currentScreen) {
          "home" -> HomeScreen(
            viewModel = viewModel,
            onOpenLogSheet = { viewModel.openLogModal() },
            onOpenOnboarding = { viewModel.navigateTo("onboarding") },
            onNavigateToScreen = { viewModel.navigateTo(it) }
          )
          "budgets" -> BudgetsScreen(
            viewModel = viewModel,
            onOpenLogForCategory = { cat -> viewModel.openLogModal(cat) },
            onNavigateBack = { viewModel.navigateTo("home") }
          )
          "insights" -> InsightsScreen(
            viewModel = viewModel,
            onNavigateBack = { viewModel.navigateTo("home") }
          )
          "profile" -> ProfileScreen(
            viewModel = viewModel,
            onOpenOnboarding = { viewModel.navigateTo("onboarding") },
            onNavigateBack = { viewModel.navigateTo("home") }
          )
        }

        // Floating Bottom Navigation Bar
        SproutBottomNav(
          currentScreen = currentScreen,
          onScreenSelected = { viewModel.navigateTo(it) },
          onAddClicked = { viewModel.openLogModal() },
          modifier = Modifier.align(Alignment.BottomCenter)
        )
      }

      // Log Transaction Bottom Sheet Modal with underlay scrim
      AnimatedVisibility(
        visible = isLogModalOpen,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
      ) {
        LogTransactionSheet(
          viewModel = viewModel,
          onDismiss = { viewModel.closeLogModal() }
        )
      }
    }
  }
}


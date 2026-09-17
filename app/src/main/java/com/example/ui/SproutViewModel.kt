package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SproutDatabase
import com.example.data.TransactionEntity
import com.example.data.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.ceil

class SproutViewModel(application: Application) : AndroidViewModel(application) {

  private val repository: TransactionRepository

  val transactions: StateFlow<List<TransactionEntity>>

  // Active Top-level Navigation Destination: "welcome", "home", "budgets", "insights", "profile", "onboarding"
  private val _currentScreen = MutableStateFlow("welcome")
  val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()

  // Modal State for Log Transaction Bottom Sheet
  private val _isLogModalOpen = MutableStateFlow(false)
  val isLogModalOpen: StateFlow<Boolean> = _isLogModalOpen.asStateFlow()

  // Log Transaction Form Fields
  private val _currentAmount = MutableStateFlow("42.80")
  val currentAmount: StateFlow<String> = _currentAmount.asStateFlow()

  private val _selectedType = MutableStateFlow("Expense")
  val selectedType: StateFlow<String> = _selectedType.asStateFlow()

  private val _merchantInput = MutableStateFlow("Trader Joe's")
  val merchantInput: StateFlow<String> = _merchantInput.asStateFlow()

  private val _selectedCategory = MutableStateFlow("Groceries")
  val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

  private val _selectedAccount = MutableStateFlow("Everyday Checking (...4021)")
  val selectedAccount: StateFlow<String> = _selectedAccount.asStateFlow()

  private val _dateDisplay = MutableStateFlow("Today, Oct 24")
  val dateDisplay: StateFlow<String> = _dateDisplay.asStateFlow()

  private val _isSplit = MutableStateFlow(false)
  val isSplit: StateFlow<Boolean> = _isSplit.asStateFlow()

  private val _isRecurring = MutableStateFlow(false)
  val isRecurring: StateFlow<Boolean> = _isRecurring.asStateFlow()

  private val _saveSuccessEvent = MutableStateFlow(false)
  val saveSuccessEvent: StateFlow<Boolean> = _saveSuccessEvent.asStateFlow()

  init {
    val db = SproutDatabase.getDatabase(application)
    repository = TransactionRepository(db.transactionDao())
    transactions = repository.allTransactions.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

    viewModelScope.launch {
      repository.seedInitialDataIfEmpty()
    }
  }

  fun navigateTo(screen: String) {
    _currentScreen.value = screen
  }

  fun openLogModal(initialCategory: String? = null) {
    if (initialCategory != null) {
      _selectedCategory.value = initialCategory
    }
    _saveSuccessEvent.value = false
    _isLogModalOpen.value = true
  }

  fun closeLogModal() {
    _isLogModalOpen.value = false
  }

  fun setType(type: String) {
    _selectedType.value = type
  }

  fun setMerchant(merchant: String) {
    _merchantInput.value = merchant
  }

  fun clearMerchant() {
    _merchantInput.value = ""
  }

  fun setCategory(category: String) {
    _selectedCategory.value = category
  }

  fun toggleSplit() {
    _isSplit.value = !_isSplit.value
  }

  fun setRecurring(recurring: Boolean) {
    _isRecurring.value = recurring
  }

  fun setDate(date: String) {
    _dateDisplay.value = date
  }

  // Set Amount directly from native device keyboard with decimal validation
  fun setAmount(rawAmount: String) {
    // Filter to valid numeric with at most one decimal and 2 decimal places
    val cleaned = rawAmount.filter { it.isDigit() || it == '.' }
    val dotIndex = cleaned.indexOf('.')
    if (dotIndex != -1) {
      val beforeDot = cleaned.substring(0, dotIndex).take(8)
      val afterDot = cleaned.substring(dotIndex + 1).filter { it.isDigit() }.take(2)
      _currentAmount.value = "$beforeDot.$afterDot"
    } else {
      _currentAmount.value = cleaned.take(8)
    }
  }

  // Tactile Keypad Logic
  fun onKeypadDigit(digit: String) {
    val current = _currentAmount.value
    if (current == "0" || current == "0.00") {
      _currentAmount.value = digit
    } else {
      val parts = current.split(".")
      if (parts.size > 1 && parts[1].length >= 2) {
        return // Max 2 decimal digits
      }
      if (current.length < 9) {
        _currentAmount.value = current + digit
      }
    }
  }

  fun onKeypadDot() {
    val current = _currentAmount.value
    if (!current.contains(".")) {
      _currentAmount.value = if (current.isEmpty()) "0." else "$current."
    }
  }

  fun onKeypadBackspace() {
    val current = _currentAmount.value
    if (current.isNotEmpty()) {
      val shortened = current.dropLast(1)
      _currentAmount.value = if (shortened.isEmpty()) "0" else shortened
    }
  }

  fun onQuickAdd(addition: Double) {
    val currentNum = _currentAmount.value.toDoubleOrNull() ?: 0.0
    val newTotal = currentNum + addition
    _currentAmount.value = String.format(Locale.US, "%.2f", newTotal)
  }

  fun onRoundUp() {
    val currentNum = _currentAmount.value.toDoubleOrNull() ?: 0.0
    val ceiling = ceil(currentNum)
    val nextTarget = if (ceiling == currentNum) ceiling + 1.0 else ceiling
    _currentAmount.value = String.format(Locale.US, "%.2f", nextTarget)
  }

  fun saveCurrentTransaction() {
    val amountDouble = _currentAmount.value.toDoubleOrNull() ?: 0.0
    if (amountDouble <= 0.0) return

    val merchant = _merchantInput.value.ifBlank { "Expense" }
    val newTx = TransactionEntity(
      amount = amountDouble,
      merchant = merchant,
      category = _selectedCategory.value,
      type = _selectedType.value,
      account = _selectedAccount.value,
      dateDisplay = _dateDisplay.value,
      timestamp = System.currentTimeMillis(),
      isSplit = _isSplit.value,
      isRecurring = _isRecurring.value
    )

    viewModelScope.launch {
      repository.insert(newTx)
      _saveSuccessEvent.value = true
      kotlinx.coroutines.delay(1200)
      _isLogModalOpen.value = false
      _saveSuccessEvent.value = false
    }
  }

  fun deleteTransaction(id: Long) {
    viewModelScope.launch {
      repository.deleteById(id)
    }
  }

  fun resetToSampleData() {
    viewModelScope.launch {
      repository.deleteAll()
      repository.seedInitialDataIfEmpty()
    }
  }
}

package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val amount: Double,
  val merchant: String,
  val category: String, // "Groceries", "Dining", "Coffee", "Transport", "Shopping", "Health", "More"
  val type: String,     // "Expense", "Income", "Transfer"
  val account: String,  // "Everyday Checking (...4021)"
  val dateDisplay: String, // "Today, Oct 24"
  val timestamp: Long = System.currentTimeMillis(),
  val isSplit: Boolean = false,
  val isRecurring: Boolean = false
)

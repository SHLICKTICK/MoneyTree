package com.example.data

import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

  val allTransactions: Flow<List<TransactionEntity>> = transactionDao.getAllTransactions()

  suspend fun insert(transaction: TransactionEntity): Long {
    return transactionDao.insertTransaction(transaction)
  }

  suspend fun deleteById(id: Long) {
    transactionDao.deleteTransactionById(id)
  }

  suspend fun deleteAll() {
    transactionDao.deleteAll()
  }

  suspend fun seedInitialDataIfEmpty() {
    if (transactionDao.getCount() == 0) {
      val defaultTransactions = listOf(
        TransactionEntity(
          amount = 42.80,
          merchant = "Trader Joe's",
          category = "Groceries",
          type = "Expense",
          account = "Everyday Checking (...4021)",
          dateDisplay = "Today, Oct 24",
          timestamp = System.currentTimeMillis() - 1000 * 60 * 30, // 30 mins ago
          isSplit = false,
          isRecurring = false
        ),
        TransactionEntity(
          amount = 6.45,
          merchant = "Blue Bottle Coffee",
          category = "Coffee",
          type = "Expense",
          account = "Everyday Checking (...4021)",
          dateDisplay = "Today, Oct 24",
          timestamp = System.currentTimeMillis() - 1000 * 60 * 180, // 3 hours ago
          isSplit = false,
          isRecurring = false
        ),
        TransactionEntity(
          amount = 32.00,
          merchant = "Subway Metro Pass",
          category = "Transport",
          type = "Expense",
          account = "Everyday Checking (...4021)",
          dateDisplay = "Yesterday, Oct 23",
          timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 26,
          isSplit = false,
          isRecurring = true
        ),
        TransactionEntity(
          amount = 78.50,
          merchant = "Target Market",
          category = "Shopping",
          type = "Expense",
          account = "Everyday Checking (...4021)",
          dateDisplay = "Oct 22",
          timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 50,
          isSplit = true,
          isRecurring = false
        ),
        TransactionEntity(
          amount = 18.25,
          merchant = "Sweetgreen",
          category = "Dining",
          type = "Expense",
          account = "Everyday Checking (...4021)",
          dateDisplay = "Oct 21",
          timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 75,
          isSplit = false,
          isRecurring = false
        )
      )
      defaultTransactions.forEach { transactionDao.insertTransaction(it) }
    }
  }
}

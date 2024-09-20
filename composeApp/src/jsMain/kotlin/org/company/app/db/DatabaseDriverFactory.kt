package org.company.app.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import kotlinx.coroutines.await

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver = initSqlDriver(AppDatabase.Schema).await()
}
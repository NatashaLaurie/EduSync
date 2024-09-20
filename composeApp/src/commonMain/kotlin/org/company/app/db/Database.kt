package org.company.app.db

import app.cash.sqldelight.ColumnAdapter
import org.company.app.domain.model.Schedule
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Database(
    private val driverProvider: DatabaseDriverFactory,
) {
    private var database: AppDatabase? = null

    private val listOfStrings = object : ColumnAdapter<List<Schedule>, String> {
        override fun decode(databaseValue: String): List<Schedule> =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                Json.decodeFromString<List<Schedule>>(databaseValue)
            }

        override fun encode(value: List<Schedule>): String =
            Json.encodeToString<List<Schedule>>(value)
    }

    private suspend fun initDatabase() {
        if (database == null) {
            database = AppDatabase.invoke(
                driverProvider.createDriver(),
                CachedCourses.Adapter(listOfStrings)
            )
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }
}
package com.samra.mvvmtodolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.samra.mvvmtodolist.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()
//default list set
            applicationScope.launch {
                dao.insert(Task("Apple"))
                dao.insert(Task("Mango"))
                dao.insert(Task("Banana", important = true))
                dao.insert(Task("PineApple", completed = true))
                dao.insert(Task("Grapes"))
                dao.insert(Task("Lechee", completed = true))
                dao.insert(Task("Kewee"))
                dao.insert(Task("Strawberry"))
            }
        }
    }
}
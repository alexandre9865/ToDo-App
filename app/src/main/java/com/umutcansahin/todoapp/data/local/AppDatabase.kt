package com.umutcansahin.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
                ToDoEntity::class,
                CategoryEntity::class
               ],
    version = 2

)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract val toDoDao: ToDoDao
    abstract val categoryDao: CategoryDao

    companion object Migrations {
        val M_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `category` (" +
                            "`id` INTEGER NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`parentId` INTEGER, " +
                            "`color` TEXT NOT NULL , " +
                            "PRIMARY KEY(`id`)," +
                            "FOREIGN KEY(parentId) REFERENCES category(id) ON UPDATE CASCADE ON DELETE CASCADE)"
                )
                database.execSQL("CREATE INDEX IF NOT EXISTS `index_category_parentId` ON `category` (`parentId`)");
                database.execSQL("INSERT INTO `category` (`id`, `name`, `color`) VALUES (1, 'Business', '#FFA5D6A7')")
                database.execSQL("INSERT INTO `category` (`id`, `name`, `color`) VALUES (2, 'School', '#FFFFAB91')")
                database.execSQL("INSERT INTO `category` (`id`, `name`, `color`) VALUES (3, 'Shopping', '#FF80DEEA')")
                database.execSQL("INSERT INTO `category` (`id`, `name`, `color`) VALUES (4, 'Sport', '#FFFFCC80')")

                database.execSQL("ALTER TABLE `todo` ADD COLUMN categoryId INTEGER NOT NULL DEFAULT 0 REFERENCES category(id) ON UPDATE CASCADE ON DELETE CASCADE;")

                database.execSQL("UPDATE `todo` SET categoryId = 1 WHERE type = 'Business'")
                database.execSQL("UPDATE `todo` SET categoryId = 2 WHERE type = 'School'")
                database.execSQL("UPDATE `todo` SET categoryId = 3 WHERE type = 'Shopping'")
                database.execSQL("UPDATE `todo` SET categoryId = 4 WHERE type = 'Sport'")
                database.execSQL("UPDATE `todo` SET type = null")

                database.execSQL("ALTER TABLE `todo` RENAME COLUMN type TO note;")

            }
        }
    }

}
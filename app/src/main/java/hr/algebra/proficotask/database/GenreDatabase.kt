package hr.algebra.proficotask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.algebra.proficotask.database.model.GenreDb

@Database(entities = [GenreDb::class], version = 1)
abstract class GenreDatabase: RoomDatabase() {

    abstract fun genreDao(): GenreDao

    companion object {
        private var instance: GenreDatabase? = null

        fun getDatabase(context: Context): GenreDatabase?{
            if (instance == null){
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context): GenreDatabase =
            Room.databaseBuilder(context, GenreDatabase::class.java, "GenreDatabase")
                .allowMainThreadQueries().build()
    }

}
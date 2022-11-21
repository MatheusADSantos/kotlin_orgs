package MatheusADSantos.com.github.orgs.database

import MatheusADSantos.com.github.orgs.database.converter.Converters
import MatheusADSantos.com.github.orgs.database.dao.ProdutoDAO
import MatheusADSantos.com.github.orgs.model.Produto
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDAO

    companion object {
        fun instancia(contexto: Context): AppDatabase {
            return Room.databaseBuilder(
                contexto,
                AppDatabase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build()
        }
    }
}
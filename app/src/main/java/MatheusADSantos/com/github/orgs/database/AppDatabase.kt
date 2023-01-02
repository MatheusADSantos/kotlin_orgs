package MatheusADSantos.com.github.orgs.database

import MatheusADSantos.com.github.orgs.database.converter.Converters
import MatheusADSantos.com.github.orgs.database.dao.ProdutoDao
import MatheusADSantos.com.github.orgs.model.Produto
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Produto::class
//        Usuario::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
//    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        fun instancia(contexto: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                contexto,
                AppDatabase::class.java,
                "orgs.db"
            ).build()
                .also {
                    db = it
                }
        }
    }
}
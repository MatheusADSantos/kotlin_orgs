package MatheusADSantos.com.github.orgs.database

import MatheusADSantos.com.github.orgs.database.converter.Converters
import MatheusADSantos.com.github.orgs.database.dao.ProdutoDao
import MatheusADSantos.com.github.orgs.database.dao.UsuarioDao
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.model.Usuario
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Produto::class,
        Usuario::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        fun instancia(contexto: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                contexto,
                AppDatabase::class.java,
                "orgs.db"
            )
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2)
                .build()
                .also {
                    db = it
                }
        }
    }
}
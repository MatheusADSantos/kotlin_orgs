package MatheusADSantos.com.github.orgs.database.dao

import MatheusADSantos.com.github.orgs.model.Usuario
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salva(usuario: Usuario)

    @Query("""SELECT * FROM Usuario WHERE id = :usuarioId AND senha = :senha""")
    suspend fun autentica(usuarioId: String, senha: String): Usuario?

    @Query("""SELECT * FROM Usuario WHERE id = :usuarioId""")
    fun buscaPorId(usuarioId: String): Flow<Usuario>

}
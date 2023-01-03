package MatheusADSantos.com.github.orgs.database.dao

import MatheusADSantos.com.github.orgs.model.Usuario
import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salva(usuario: Usuario)

}
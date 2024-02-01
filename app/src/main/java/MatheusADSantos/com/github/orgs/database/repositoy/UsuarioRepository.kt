package MatheusADSantos.com.github.orgs.database.repositoy

import MatheusADSantos.com.github.orgs.database.dao.UsuarioDao
import MatheusADSantos.com.github.orgs.model.Usuario

class UsuarioRepository(private val dao: UsuarioDao) {
    
    suspend fun autentica(usuario: String, senha: String) = dao.autentica(usuario, senha)

    suspend fun salva(usuario: Usuario) = dao.salva(usuario)

    fun buscaPorId(idUsuario: String) = dao.buscaPorId(idUsuario)
}

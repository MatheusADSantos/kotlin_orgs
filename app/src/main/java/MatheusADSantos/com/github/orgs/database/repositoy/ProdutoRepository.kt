package MatheusADSantos.com.github.orgs.database.repositoy

import MatheusADSantos.com.github.orgs.database.dao.ProdutoDao
import MatheusADSantos.com.github.orgs.model.Produto

class ProdutoRepository(private val dao: ProdutoDao) {

    fun buscaPorID(produtoId: Long) = dao.buscaPorID(produtoId)

    suspend fun remove(produto: Produto) = dao.remove(produto)

    suspend fun salva(produtoNovo: Produto) = dao.salva(produtoNovo)

    suspend fun deletaTodos() = dao.deletaTodos()

    fun buscaTodosOrdenadoPorNomeDesc() = dao.buscaTodosOrdenadoPorNomeDesc()

    fun buscaTodosOrdenadoPorNomeAsc() = dao.buscaTodosOrdenadoPorNomeAsc()

    fun buscaTodosOrdenadoPorDescricaoDesc() = dao.buscaTodosOrdenadoPorDescricaoDesc()

    fun buscaTodosOrdenadoPorDescricaoAsc() = dao.buscaTodosOrdenadoPorDescricaoAsc()

    fun buscaTodosOrdenadoPorValorDesc()  = dao.buscaTodosOrdenadoPorValorDesc()

    fun buscaTodosOrdenadoPorValorAsc() = dao.buscaTodosOrdenadoPorValorAsc()

    fun buscaTodosDoUsuario(usuarioId: String) = dao.buscaTodosDoUsuario(usuarioId)

    fun buscaTodos() = dao.buscaTodos()


}

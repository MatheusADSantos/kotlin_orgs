package MatheusADSantos.com.github.orgs.dao

import MatheusADSantos.com.github.orgs.model.Produto

class ProdutoDAO {

    fun adiciona(protudo: Produto) {
        produtos.add(protudo)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}
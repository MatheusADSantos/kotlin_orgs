package MatheusADSantos.com.github.orgs.dao

import MatheusADSantos.com.github.orgs.model.Produto
import java.math.BigDecimal

class ProdutoDAO {

    fun adiciona(protudo: Produto) {
        produtos.add(protudo)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de frutas",
                descricao = "Laranja, maçãs e uva",
                valor = BigDecimal("19.83")
            )
        )
    }


}
package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.databinding.ActivityFormularioProdutoBinding
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.dialog.FormularioImagemmDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var produtoId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastro de Produtos"
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemmDialog(contexto = this).mostra(urlPadrao = url) { imagem: String ->
                url = imagem
                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
            }
        }
        recebendoProdutoParaEditar()
    }

    private fun recebendoProdutoParaEditar() {
        title = "Alterar Produto"
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoParaEdicao ->
            produtoId = produtoParaEdicao.id
            url = produtoParaEdicao.imagem
            binding.activityFormularioProdutoImagem.tentaCarregarImagem(produtoParaEdicao.imagem)
            binding.activityFormularioProdutoNome.setText(produtoParaEdicao.nome)
            binding.activityFormularioProdutoDescricao.setText(produtoParaEdicao.descricao)
            binding.activityFormularioProdutoValor.setText(produtoParaEdicao.valor.toString())
        }
    }


    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if (produtoId > 0) {
                produtoDao.altera(produtoNovo)
            } else {
                produtoDao.salva(produtoNovo)
            }
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campovalor = binding.activityFormularioProdutoValor
        val valorTexto = campovalor.text.toString()
        val valor = if (valorTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorTexto)
        }
        return Produto(
            id = produtoId,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }

}
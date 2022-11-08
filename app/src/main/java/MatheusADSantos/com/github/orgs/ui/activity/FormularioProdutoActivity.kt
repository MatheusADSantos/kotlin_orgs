package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.dao.ProdutoDAO
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemmDialog(contexto = this).mostra(urlPadrao = url) { imagem: String ->
                url = imagem
                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
            }
        }
    }


        private fun configuraBotaoSalvar() {
            val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
            val dao = ProdutoDAO()
            botaoSalvar.setOnClickListener {
                val produtoNovo = criaProduto()
                dao.adiciona(produtoNovo)
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
            return Produto(nome = nome, descricao = descricao, valor = valor, imagem = url)
        }

    }
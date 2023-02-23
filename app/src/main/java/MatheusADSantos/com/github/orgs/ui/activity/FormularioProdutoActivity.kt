package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.databinding.ActivityFormularioProdutoBinding
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.dialog.FormularioImagemmDialog
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

private const val TAG = "FormularioProduto"

class FormularioProdutoActivity : UsuarioBaseActivity() {

    private var url: String? = null
    private var produtoId = 0L
    private val binding by lazy { ActivityFormularioProdutoBinding.inflate(layoutInflater) }
    private val produtoDao by lazy { AppDatabase.instancia(this).produtoDao() }
    private val usuarioDao by lazy { AppDatabase.instancia(this).usuarioDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastro de Produtos"
        configuraBotaoSalvar()
        configuraImagem()
        tentaCarregarProduto()
        tentaBuscarProduto()
//        lifecycleScope.launch {
//            usuario.filterNotNull().collect { usuario ->
//                Log.i(TAG, "onCreate: $usuario")
//            }
//        }
    }

    private fun configuraImagem() {
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemmDialog(contexto = this)
                .mostra(urlPadrao = url) { imagem: String ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun tentaBuscarProduto() {
        lifecycleScope.launch {
            produtoDao.buscaPorID(produtoId).collect { produto ->
                produto?.let {
                    preencheCampos(it)
                }
            }
        }
    }

    private fun preencheCampos(produto: Produto) {
        title = "Alterar Produto"
        url = produto.imagem
        with(binding) {
            activityFormularioProdutoImagem.tentaCarregarImagem(produto.imagem)
            activityFormularioProdutoNome.setText(produto.nome)
            activityFormularioProdutoDescricao.setText(produto.descricao)
            activityFormularioProdutoValor.setText(produto.valor.toString())
        }
    }


    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        botaoSalvar.setOnClickListener {
            lifecycleScope.launch {
                usuario.value?.let { usuario ->
                    val produtoNovo = criaProduto(usuario.id)
                    produtoDao.salva(produtoNovo)
                    finish()
                }
            }
        }
    }

    private fun criaProduto(usuarioId: String): Produto {
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
            imagem = url,
            usuarioId = usuarioId
        )
    }

}
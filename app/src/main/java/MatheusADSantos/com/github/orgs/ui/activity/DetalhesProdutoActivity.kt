package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.databinding.ActivityDetalheProdutoBinding
import MatheusADSantos.com.github.orgs.extensions.formataParaMoedaBrasileira
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import MatheusADSantos.com.github.orgs.model.Produto
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "DetalhesProdutoActivity"

class DetalhesProdutoActivity : AppCompatActivity() {

    private var produtoId: Long? = null
    private var produto: Produto? = null
    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDAO by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        produtoId?.let { id ->
            produto = produtoDAO.buscaPorID(id)
        }
        produto?.let { produto ->
            preencheCampos(produto)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_editar -> {
                Log.i(TAG, "onOptionsItemSelected: Editando produto: $produto")
                val intent = Intent(this, FormularioProdutoActivity::class.java)
                intent.apply { putExtra(CHAVE_PRODUTO, produto) }
                startActivity(intent)
            }
            R.id.menu_detalhes_produto_remover -> {
                Log.e(TAG, "onOptionsItemSelected: Removendo produto: $produto")
                produto?.let { produtoDAO.remove(it) }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
//            produto = produtoCarregado
            produtoId = produtoCarregado.id
//            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}
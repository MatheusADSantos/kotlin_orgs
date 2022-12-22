package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.database.converter.Converters
import MatheusADSantos.com.github.orgs.databinding.ActivityListaProdutosBinding
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaProdutosAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "ListaProdutosActivity"

class ListaProdutosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    private val adapter = ListaProdutosAdapter(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFAB()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ordenacao_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter_produto_deleta_todos -> {
                Log.e(TAG, "onOptionsItemSelected: Deleta TODOS")
                produtoDao.deletaTodos()
                adapter.atualiza(mutableListOf<Produto>())
            }
            R.id.menu_filter_produto_nome_desc -> {
                ordenandoProdutos("nome", false)
            }
            R.id.menu_filter_produto_nome_asc -> {
                ordenandoProdutos("nome", true)
            }
            R.id.menu_filter_produto_descricao_desc -> {
                ordenandoProdutos("descricao", false)
            }
            R.id.menu_filter_produto_descricao_asc -> {
                ordenandoProdutos("descricao", true)
            }
            R.id.menu_filter_produto_valor_desc -> {
                ordenandoProdutos("valor", false)
            }
            R.id.menu_filter_produto_valor_asc -> {
                ordenandoProdutos("valor", true)
            }
            R.id.menu_filter_produto_sem_ordenacao -> {
                adapter.atualiza(produtoDao.buscaTodos())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ordenandoProdutos(tipo: String, ascendente: Boolean) {
        if (ascendente) {
            produtoDao.buscaTodos().sortedBy {
                when (tipo) {
                    "nome" -> {
                        it.nome
                    }
                    "descricao" -> {
                        it.descricao
                    }
                    else -> {
                        Converters().deBigDecimalParaDouble(it.valor).toString()
                    }
                }
            }.apply {
                adapter.atualiza(this)
            }
        } else {
            produtoDao.buscaTodos().sortedByDescending {
                when (tipo) {
                    "nome" -> {
                        it.nome
                    }
                    "descricao" -> {
                        it.descricao
                    }
                    else -> {
                        Log.i(TAG, "ordenandoProdutos: $tipo")
                        Log.i(TAG, "ordenandoProdutos: ${Converters().deBigDecimalParaInt(it.valor).toString()}")
                        Converters().deBigDecimalParaInt(it.valor).toString()
                    }
                }
            }.apply {
                adapter.atualiza(this)
            }
        }

    }

    private fun configuraFAB() {
        val fab = binding.activitityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerview
        recyclerView.adapter = adapter

        /* Implementação do listener para abrir a Activity de detalhes do produto
        com o produto clicado*/
        entrandoDetalhe()

        adapter.quandoClicaEmEditar = {
            Log.i(TAG, "configuraRecyclerView: EDITAR $it")
        }
        adapter.quandoClicaEmRemover = {
            Log.i(TAG, "configuraRecyclerView: REMOVER $it")
        }
    }

    private fun entrandoDetalhe() {
        adapter.quandoClicaNoItem = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                // envio do produto por meio do extra
                this.putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
    }
}
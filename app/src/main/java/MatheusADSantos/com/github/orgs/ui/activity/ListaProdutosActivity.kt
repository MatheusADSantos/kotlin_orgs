package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.database.repositoy.ProdutoRepository
import MatheusADSantos.com.github.orgs.databinding.ActivityListaProdutosBinding
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaProdutosAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

private const val TAG = "ListaProdutosActivity"

class ListaProdutosActivity : UsuarioBaseActivity() {

    private val binding by lazy { ActivityListaProdutosBinding.inflate(layoutInflater) }
    private val repository by lazy {
        val dao = AppDatabase.instancia(this).produtoDao()
        ProdutoRepository(dao)
    }
    private val adapter = ListaProdutosAdapter(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFAB()
        lifecycleScope.launch {
            launch {
                usuario.filterNotNull().collect { usuario ->
                    Log.i(TAG, "onCreate: $usuario")
                    buscaProdutosUsuario(usuario.id)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ordenacao_produto, menu)
        menuInflater.inflate(R.menu.menu_lista_produtos_perfil, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_lista_produtos_perfil -> {
                lifecycleScope.launch {
                    Log.e(TAG, "onOptionsItemSelected: Entrando no Perfil!")
                    vaiParaPerfilUsuario()
//                    deslogaUsuario()
                }
            }
            R.id.menu_filter_produto_deleta_todos -> {
                Log.e(TAG, "onOptionsItemSelected: Deleta TODOS")
                lifecycleScope.launch {
                    repository.deletaTodos()
                    adapter.atualiza(mutableListOf<Produto>())
                }
            }
            R.id.menu_filter_produto_nome_desc -> {
                lifecycleScope.launch {
                    repository.buscaTodosOrdenadoPorNomeDesc().collect {
                        adapter.atualiza(it)
                    }
                }
            }
            R.id.menu_filter_produto_nome_asc -> {
                lifecycleScope.launch {
                    repository.buscaTodosOrdenadoPorNomeAsc().collect {
                        adapter.atualiza(it)
                    }
                }
            }
            R.id.menu_filter_produto_descricao_desc -> {
                lifecycleScope.launch {
                    repository.buscaTodosOrdenadoPorDescricaoDesc().collect {
                        adapter.atualiza(it)
                    }
                }
            }
            R.id.menu_filter_produto_descricao_asc -> {
                lifecycleScope.launch {
                    repository.buscaTodosOrdenadoPorDescricaoAsc().collect {
                        adapter.atualiza(it)
                    }
                }
            }
            R.id.menu_filter_produto_valor_desc -> {
                lifecycleScope.launch {
                    repository.buscaTodosOrdenadoPorValorDesc().collect {
                        adapter.atualiza(it)
                    }
                }
            }
            R.id.menu_filter_produto_valor_asc -> {
                lifecycleScope.launch {
                    repository.buscaTodosOrdenadoPorValorAsc().collect {
                        adapter.atualiza(it)
                    }
                }
            }
            R.id.menu_filter_produto_sem_ordenacao -> {
                lifecycleScope.launch {
                    buscaProdutos()
                }
            }
            R.id.menu_filter_todos_produtos -> {
                lifecycleScope.launch {
                    vaiParaTodosProdutos()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private suspend fun buscaProdutosUsuario(usuarioId: String) {
        repository.buscaTodosDoUsuario(usuarioId).collect { produtos ->
            adapter.atualiza(produtos)
        }
    }

    private suspend fun buscaProdutos() {
        repository.buscaTodos().collect { produtos ->
            adapter.atualiza(produtos)
        }
    }

    private fun configuraFAB() {
        val fab = binding.activitityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        Intent(this, FormularioProdutoActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun vaiParaPerfilUsuario() {
        Intent(this, PerfilActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun vaiParaTodosProdutos() {
        Intent(this, ListaTodosProdutosActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerview
        recyclerView.adapter = adapter
        entraDetalheItem()
        editaItem()
        removeItem()
    }

    private fun removeItem() {
        adapter.quandoClicaEmRemover = { produtoSelecionado ->
            Log.i(TAG, "configuraRecyclerView: REMOVER $produtoSelecionado")
            lifecycleScope.launch {
                repository.remove(produtoSelecionado)
//                buscaProdutos()
            }
        }
    }

    private fun editaItem() {
        adapter.quandoClicaEmEditar = {
            Log.i(TAG, "configuraRecyclerView: EDITAR $it")
            Intent(this, FormularioProdutoActivity::class.java).apply {
                this.putExtra(CHAVE_PRODUTO_ID, it.id)
                startActivity(this)
            }
        }
    }

    private fun entraDetalheItem() {
        adapter.quandoClicaNoItem = {
            Intent(this, DetalhesProdutoActivity::class.java).apply {
                this.putExtra(CHAVE_PRODUTO_ID, it.id)
                startActivity(this)
            }
        }
    }
}
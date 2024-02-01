package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.database.repositoy.ProdutoRepository
import MatheusADSantos.com.github.orgs.databinding.ActivityListaTodosProdutosBinding
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.recyclerview.CabecalhoListaTodosProdutosAdapter
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaTodosProdutosAdapter
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

//
class ListaTodosProdutosActivity : UsuarioBaseActivity() {

    private val binding by lazy { ActivityListaTodosProdutosBinding.inflate(layoutInflater) }
    private val repository by lazy {
        val dao = AppDatabase.instancia(this).produtoDao()
        ProdutoRepository(dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerViewTodosProdutos()
    }

    private fun configuraRecyclerViewTodosProdutos() {
        val recyclerview = binding.activityListaTodosProdutosRecyclerview
        lifecycleScope.launch {
            repository.buscaTodos().map { produtos ->
                produtos.sortedBy {
                    it.usuarioId
                }.groupBy {
                    it.usuarioId
                }.map {
                    criaAdapterDeProdutosComCabecalho(it)
                }.flatten()
            }.collect { adapter ->
                recyclerview.adapter = ConcatAdapter(adapter)
            }
        }
    }

    private fun criaAdapterDeProdutosComCabecalho(produtosUsuario: Map.Entry<String?, List<Produto>>) =
        listOf(
            CabecalhoListaTodosProdutosAdapter(this, listOf(produtosUsuario.key)),
            ListaTodosProdutosAdapter(this, produtosUsuario.value)
//            { produtoClicado ->
//                vaiPara(DetalhesProdutoActivity::class.java) {
//                    putExtra(CHAVE_PRODUTO_ID, produtoClicado.id)
//                }
//            }
        )
}
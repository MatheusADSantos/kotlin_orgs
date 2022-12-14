package MatheusADSantos.com.github.orgs.ui.recyclerview

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.databinding.ProdutoItemBinding
import MatheusADSantos.com.github.orgs.extensions.formataParaMoedaBrasileira
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import MatheusADSantos.com.github.orgs.model.Produto
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    // declaração da função para o listener do adapter
    var quandoClicaNoItem: (produto: Produto) -> Unit = {},
    var quandoClicaEmEditar: (produto: Produto) -> Unit = {},
    var quandoClicaEmRemover: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()


    // utilização do inner na classe interna para acessar membros da classe superior
    // nesse caso, a utilização da variável quandoClicaNoItem
    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        // Considerando que o ViewHolder modifica de valor com base na posição
        // é necessário o uso de properties mutáveis, para evitar nullables
        // utilizamos o lateinit, properties que podem ser inicializar depois
        private lateinit var produto: Produto

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }

            // implementação do listener longClick
            // Apresentar PopupMenu: infla-lo, setar o click na viewHolder e mostrar
            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = produto.valor
                .formataParaMoedaBrasileira()
            valor.text = valorEmMoeda
            val visibilidade = if (produto.imagem != null) View.VISIBLE else View.GONE
            binding.imageView.visibility = visibilidade
            binding.imageView.tentaCarregarImagem(produto.imagem)
        }

        private fun formataParaMoedaBrasileira(valor: BigDecimal): String {
            val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when(it.itemId) {
                    R.id.menu_detalhes_produto_editar -> {
                        quandoClicaEmEditar(produto)
                    }
                    R.id.menu_detalhes_produto_remover -> {
                        quandoClicaEmRemover(produto)
                    }
                }
            }
            return true
        }
    }


    // Criando/Config./Inflando os items do parent(RyclerView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Aqui estou preenchendo os dados nos itens(ViewHolder)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size


    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}

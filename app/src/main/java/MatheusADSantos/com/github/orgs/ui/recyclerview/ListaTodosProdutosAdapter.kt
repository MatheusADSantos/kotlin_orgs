package MatheusADSantos.com.github.orgs.ui.recyclerview

import MatheusADSantos.com.github.orgs.databinding.ProdutoItemBinding
import MatheusADSantos.com.github.orgs.extensions.formataParaMoedaBrasileira
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import MatheusADSantos.com.github.orgs.model.Produto
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListaTodosProdutosAdapter(
    private val contexto: Context,
    produtos: List<Produto> = emptyList()
) : RecyclerView.Adapter<ListaTodosProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    // Aqui onde eu vou configurar tudo relacionado ao viewHolder(item do recycler view)
    // Obs: Inner para conseguir acessar membros da classe superior(ListaTodosProdutosAdapter)
    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Cons. que o valor produto vai mudar de acordo com a posic. precisa ser um var
        private lateinit var produto: Produto

        fun binding(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = produto.valor.formataParaMoedaBrasileira()
            valor.text = valorEmMoeda
            val visibilidade = if (produto.imagem != null) View.VISIBLE else View.GONE
            binding.imageView.visibility = visibilidade
            binding.imageView.tentaCarregarImagem(produto.imagem)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contexto)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    // Fazendo o binding(vinculação) dos item view ...
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.binding(produto)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
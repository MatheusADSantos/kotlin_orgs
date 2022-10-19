package MatheusADSantos.com.github.orgs.ui.recyclerview

import MatheusADSantos.com.github.orgs.databinding.ProdutoItemBinding
import MatheusADSantos.com.github.orgs.model.Produto
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            valor.text = produto.valor.toPlainString()
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

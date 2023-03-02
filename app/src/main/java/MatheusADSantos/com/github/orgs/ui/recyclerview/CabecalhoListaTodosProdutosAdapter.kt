package MatheusADSantos.com.github.orgs.ui.recyclerview

import MatheusADSantos.com.github.orgs.databinding.UsuarioItemTodosBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CabecalhoListaTodosProdutosAdapter(
    private val contexto: Context,
    usuarios: List<String?> = emptyList()
) : RecyclerView.Adapter<CabecalhoListaTodosProdutosAdapter.ViewHolder>() {

    private val usuarios: List<String?> = usuarios.toMutableList()

    inner class ViewHolder(internal val binding: UsuarioItemTodosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(usuario: String?) {
            binding.usuario.text = usuario
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contexto)
        val binding = UsuarioItemTodosBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = if (usuarios[position].isNullOrBlank()) "Sem usuário" else usuarios[position]
        holder.binding(usuario)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

}
package MatheusADSantos.com.github.orgs.ui.dialog

import MatheusADSantos.com.github.orgs.databinding.FormularioImageBinding
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

class FormularioImagemmDialog(private val contexto: Context) {

    fun mostra(quandoImagemCarregada: (imagem: String) -> Unit) {
        val binding = FormularioImageBinding
            .inflate(LayoutInflater.from(contexto))
        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentaCarregarImagem(url)
        }

        AlertDialog.Builder(contexto)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoImagemCarregada(url)
            }
            .setNegativeButton("Cancelar") { _, _ ->
            }
            .show()
    }
}
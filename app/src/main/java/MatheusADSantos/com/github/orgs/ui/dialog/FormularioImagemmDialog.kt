package MatheusADSantos.com.github.orgs.ui.dialog

import MatheusADSantos.com.github.orgs.databinding.FormularioImageBinding
import MatheusADSantos.com.github.orgs.extensions.tentaCarregarImagem
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

class FormularioImagemmDialog(private val contexto: Context) {

    fun mostra(urlPadrao: String? = null, quandoImagemCarregada: (imagem: String) -> Unit) {

        FormularioImageBinding.
        inflate(LayoutInflater.from(contexto)).apply {

            urlPadrao?.let {
                formularioImagemImageview.tentaCarregarImagem(it)
                formularioImagemUrl.setText(it)
            }

            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentaCarregarImagem(url)
            }

            AlertDialog.Builder(contexto)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->
                }
                .show()

        }
    }
}
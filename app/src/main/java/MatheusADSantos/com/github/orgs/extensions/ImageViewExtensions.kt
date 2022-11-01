package MatheusADSantos.com.github.orgs.extensions

import MatheusADSantos.com.github.orgs.R
import android.widget.ImageView
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null){
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}
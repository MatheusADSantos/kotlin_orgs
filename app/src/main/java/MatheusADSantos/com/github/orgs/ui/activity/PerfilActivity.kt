package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.databinding.ActivityPerfilUsuarioBinding
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

private const val TAG = "PerfilActivity"

class PerfilActivity: UsuarioBaseActivity() {

    private val binding by lazy { ActivityPerfilUsuarioBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraNomeEUsuario()
        configuraBotaoDeslogar()
    }

    private fun configuraNomeEUsuario() {
        Log.e(TAG, "configuraNomeEUsuario: ", )
        val perfilNome = binding.perfilNome
        val perfilUsuario = binding.perfilUsuario
        lifecycleScope.launch {
            usuario?.filterNotNull()?.collect { usuario ->
                perfilUsuario.text = usuario.id
                perfilNome.text = usuario.nome
            }
        }
    }

    private fun configuraBotaoDeslogar() {
        val botaoDeslogar = binding.buttonPerfilDeslogar
        botaoDeslogar.setOnClickListener {
            Log.e(TAG, "Deslogando... ", )
            lifecycleScope.launch { deslogaUsuario() }
        }
    }

}
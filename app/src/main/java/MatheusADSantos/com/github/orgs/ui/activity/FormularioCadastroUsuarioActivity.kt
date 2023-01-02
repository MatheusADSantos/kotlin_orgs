package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.databinding.ActivityFormularioCadastroUsuarioBinding
import MatheusADSantos.com.github.orgs.model.Usuario
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class FormularioCadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            val novoUsuario = criarUsuario()
            Log.i("CadastroUsuario", "onCreate: $novoUsuario")
            finish()
        }
    }

    private fun criarUsuario(): Usuario {
        val usuario = binding.activityFormularioCadastroUsuario.text.toString()
        val nome = binding.activityFormularioCadastroNome.text.toString()
        val senha = binding.activityFormularioCadastroSenha.text.toString()
        return Usuario(id = usuario, nome = nome, senha = senha)
    }
}
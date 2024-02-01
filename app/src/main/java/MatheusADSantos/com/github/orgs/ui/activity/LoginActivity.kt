package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.database.repositoy.UsuarioRepository
import MatheusADSantos.com.github.orgs.databinding.ActivityLoginBinding
import MatheusADSantos.com.github.orgs.extensions.toast
import MatheusADSantos.com.github.orgs.extensions.vaiPara
import MatheusADSantos.com.github.orgs.preferences.dataStore
import MatheusADSantos.com.github.orgs.preferences.usuarioLogadoPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val repository by lazy {
        val dao = AppDatabase.instancia(this).usuarioDao()
        UsuarioRepository(dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.activityLoginBotaoEntrar.setOnClickListener {
            val usuario = binding.activityLoginUsuario.text.toString()
            val senha = binding.activityLoginSenha.text.toString()
            autentica(usuario, senha)
        }
    }

    private fun autentica(usuario: String, senha: String) {
        lifecycleScope.launch {
            repository.autentica(usuario, senha)?.let { usuario ->
                dataStore.edit { preferences ->
                    Log.i(TAG, "autentica: $preferences - \nUsuário: ${usuario.id} ")
                    preferences[usuarioLogadoPreferences] = usuario.id
                }
                vaiPara(ListaProdutosActivity::class.java)
                finish()
            } ?: toast("Falha na Autenticação")
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiPara(FormularioCadastroUsuarioActivity::class.java)
        }
    }
}

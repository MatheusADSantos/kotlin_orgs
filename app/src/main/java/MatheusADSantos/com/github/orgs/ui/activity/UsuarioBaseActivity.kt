package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.database.repositoy.UsuarioRepository
import MatheusADSantos.com.github.orgs.extensions.vaiPara
import MatheusADSantos.com.github.orgs.model.Usuario
import MatheusADSantos.com.github.orgs.preferences.dataStore
import MatheusADSantos.com.github.orgs.preferences.usuarioLogadoPreferences
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

private const val TAG = "UsuarioBaseActivity"

abstract class UsuarioBaseActivity : AppCompatActivity() {

    private val repository by lazy {
        val dao = AppDatabase.instancia(this).usuarioDao()
        UsuarioRepository(dao)
    }
    private val _usuario: MutableStateFlow<Usuario?> = MutableStateFlow(null)
    protected val usuario: StateFlow<Usuario?> = _usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            verificaUsuarioLogado()
        }
    }

    private suspend fun verificaUsuarioLogado() {
        dataStore.data.collect { preferences ->
            preferences[usuarioLogadoPreferences]?.let { idUsuario ->
                Log.i(TAG, "verificaUsuarioLogado: $idUsuario")
                buscaUsuario(idUsuario)
            } ?: vaiParaLogin()
        }
    }

    private suspend fun buscaUsuario(idUsuario: String): Usuario? {
        return repository
            .buscaPorId(idUsuario)
            .firstOrNull().also {
                _usuario.value = it
            }
    }

    protected suspend fun deslogaUsuario() {
        dataStore.edit { preferences ->
            Log.e(TAG, "deslogaUsuario: $preferences")
            preferences.remove(usuarioLogadoPreferences)
        }
    }

    private fun vaiParaLogin() {
        vaiPara(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }
}
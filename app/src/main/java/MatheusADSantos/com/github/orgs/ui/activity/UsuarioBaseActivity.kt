package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
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

    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }
    private var _usuario: MutableStateFlow<Usuario?> = MutableStateFlow(null)
    protected var usuario: StateFlow<Usuario?> = _usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            verificaUsuarioLogado()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        lifecycleScope.launch {
//            verificaUsuarioLogado()
//        }
//    }

    private suspend fun verificaUsuarioLogado() {
        dataStore.data.collect { preferences ->
            preferences[usuarioLogadoPreferences]?.let { idUsuario ->
                Log.i(TAG, "verificaUsuarioLogado: $idUsuario")
                buscaUsuario(idUsuario)
            } ?: vaiParaLogin()
        }
    }

    private suspend fun buscaUsuario(idUsuario: String) {
        _usuario.value = usuarioDao
            .buscaPorId(idUsuario)
            .firstOrNull()
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
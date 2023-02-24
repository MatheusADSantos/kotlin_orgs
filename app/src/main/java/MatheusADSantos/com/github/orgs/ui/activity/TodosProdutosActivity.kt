package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.databinding.ActivityTodosProdutosBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TodosProdutosActivity: AppCompatActivity() {

    private val binding by lazy {
        ActivityTodosProdutosBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.dao.ProdutoDAO
import MatheusADSantos.com.github.orgs.databinding.ActivityListaProdutosBinding
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaProdutosAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ListaProdutosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val dao = ProdutoDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFAB()

        AlertDialog.Builder(this, 0)
            .setTitle("Título")
            .setMessage("Mensagem teste")
            .setPositiveButton("Confirmar") { _, _ ->
                Log.i("ListaProdutosActivity", "onCreate: CONFIRMOU")
            }
            .setNegativeButton("Cancelar") { _, _ ->
                Log.e("ListaProdutosActivity", "onCreate: CANCELOU")
            }
            .setView(R.layout.activity_formulario_produto)
            .show()
    }


    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFAB() {
        val fab = binding.activitityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerview
        recyclerView.adapter = adapter
    }

}
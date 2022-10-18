package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.dao.ProdutoDAO
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaProdutosAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {

    private val dao = ProdutoDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        configuraFAB()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFAB() {
        val fab = findViewById<FloatingActionButton>(R.id.activitity_lista_produtos_fab)
        fab.setOnClickListener {
            vaiPara()
        }
    }

    private fun vaiPara() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_produtos_recyclerview)
        recyclerView.adapter = adapter
    }

}
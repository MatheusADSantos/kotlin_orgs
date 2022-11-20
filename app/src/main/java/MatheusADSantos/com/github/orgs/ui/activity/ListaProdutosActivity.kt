package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.dao.ProdutoDAO
import MatheusADSantos.com.github.orgs.database.AppDatabase
import MatheusADSantos.com.github.orgs.databinding.ActivityListaProdutosBinding
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.dialog.FormularioImagemmDialog
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaProdutosAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import java.math.BigDecimal

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
//        FormularioImagemmDialog(this).mostra { imagem: String ->
//            Log.i("ListaProdutosActivity", "onCreate: $imagem")
//        }

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "orgs.db"
        ).allowMainThreadQueries()
            .build()
        val produtoDao = db.produtoDao()
        produtoDao.salva(
            Produto(
                nome = "teste nome 1",
                descricao = "teste desc 1",
                valor = BigDecimal("10.0")
            )
        )
        adapter.atualiza(produtoDao.buscaTodos())

    }

    override fun onResume() {
        super.onResume()
//        adapter.atualiza(dao.buscaTodos())
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

        // implementação do listener para abrir a Activity de detalhes do produto
        // com o produto clicado
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                // envio do produto por meio do extra
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }
}
package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.model.Produto
import MatheusADSantos.com.github.orgs.ui.recyclerview.ListaProdutosAdapter
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem Vindo(a) ao Orgs!", Toast.LENGTH_LONG).show()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(
            context = this,
            produtos = listOf(
                Produto(
                    nome = "teste",
                    descricao = "desc teste",
                    valor = "9.99".toBigDecimal()
                ),Produto(
                    nome = "teste2",
                    descricao = "desc2 teste",
                    valor = "29.99".toBigDecimal()
                ),Produto(
                    nome = "teste3",
                    descricao = "desc3 teste",
                    valor = "39.99".toBigDecimal()
                ),Produto(
                    nome = "teste4",
                    descricao = "desc4 teste",
                    valor = "49.99".toBigDecimal()
                ),
            )
        )

    }

}
package MatheusADSantos.com.github.orgs

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem Vindo(a) ao Orgs!", Toast.LENGTH_LONG).show()
        setContentView(R.layout.activity_main)

//        val nome = findViewById<TextView>(R.id.nome)
//        nome.text = "Salada de Frutas"
//
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "Laranja, Manga e Uva"
//
//        val preco = findViewById<TextView>(R.id.valor)
//        preco.text = "19.99"


    }

}
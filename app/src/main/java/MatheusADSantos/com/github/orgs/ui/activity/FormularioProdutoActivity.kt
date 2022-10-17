package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.model.Produto
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val botaoSalvar = findViewById<Button>(R.id.bota_salvar)
        botaoSalvar.setOnClickListener {

            val campoNome = findViewById<EditText>(R.id.nome)
            val nome = campoNome.text.toString()

            val campoDescricao = findViewById<EditText>(R.id.descricao)
            val descricao = campoDescricao.text.toString()

            val campovalor = findViewById<EditText>(R.id.valor)
            val valorTexto = campovalor.text.toString()
            val valor = if (valorTexto.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorTexto)
            }

            val produto = Produto(nome = nome, descricao = descricao, valor = valor)
            Log.i("FormularioProduto", "onCreate: $produto")

        }

    }

}
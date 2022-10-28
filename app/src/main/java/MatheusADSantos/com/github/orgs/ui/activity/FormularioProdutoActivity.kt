package MatheusADSantos.com.github.orgs.ui.activity

import MatheusADSantos.com.github.orgs.R
import MatheusADSantos.com.github.orgs.dao.ProdutoDAO
import MatheusADSantos.com.github.orgs.databinding.ActivityFormularioProdutoBinding
import MatheusADSantos.com.github.orgs.model.Produto
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        configuraImagemCarregar()
    }

    private fun configuraImagemCarregar() {
        binding.activityFormularioProdutoImagem.setOnClickListener {
            AlertDialog.Builder(this, 0)
                .setPositiveButton("Confirmar") { _, _ ->
                    Log.i("FormularioProdutoActivity", "configuraImagemCarregar: CONFIRMOU")
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    Log.e("FormularioProdutoActivity", "configuraImagemCarregar: CANCELOU")
                }
                .setView(R.layout.formulario_image)
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutoDAO()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()

        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()

        val campovalor = binding.activityFormularioProdutoValor
        val valorTexto = campovalor.text.toString()
        val valor = if (valorTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorTexto)
        }
        return Produto(nome = nome, descricao = descricao, valor = valor)
    }

}
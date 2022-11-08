package MatheusADSantos.com.github.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


// Função de extensão para reutilizar o código de formatação em diferentes pontos do código
fun BigDecimal.formataParaMoedaBrasileira(): String {
    val formatador: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt","br"))
    return formatador.format(this)
}
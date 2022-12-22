package MatheusADSantos.com.github.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter // DB -> App
    fun deDouble(valor: Double?): BigDecimal {
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun deBigDecimalParaDouble(valor: BigDecimal?): Double? {
        return valor?.let { valor.toDouble() }
    }

    @TypeConverter
    fun deBigDecimalParaInt(valor: BigDecimal?): Int? {
        return valor?.let { valor.toInt() }
    }
}
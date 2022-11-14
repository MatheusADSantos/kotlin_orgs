package MatheusADSantos.com.github.orgs.database.dao

import MatheusADSantos.com.github.orgs.model.Produto
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : List<Produto>

    @Insert
    fun salva(vararg produto:Produto)
}
package MatheusADSantos.com.github.orgs.database.dao

import MatheusADSantos.com.github.orgs.model.Produto
import androidx.room.*

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : List<Produto>

    @Insert
    fun salva(vararg produto:Produto)

    @Delete
    fun remove(produto: Produto)

    @Update
    fun altera(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id == :id")
    fun buscaPorID(id: Long) : Produto?
}
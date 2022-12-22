package MatheusADSantos.com.github.orgs.database.dao

import MatheusADSantos.com.github.orgs.model.Produto
import androidx.room.*

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : List<Produto>

    @Query("DELETE FROM Produto")
    fun deletaTodos()

    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun buscaTodosOrdenadoPorNomeAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun buscaTodosOrdenadoPorNomeDesc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER By descricao ASC")
    fun buscaTodosOrdenadoPorDescricaoAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER By descricao DESC")
    fun buscaTodosOrdenadoPorDescricaoDesc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER By valor ASC")
    fun buscaTodosOrdenadoPorValorAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER By valor DESC")
    fun buscaTodosOrdenadoPorValorDesc(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(vararg produto:Produto)

    @Delete
    fun remove(produto: Produto)

//    @Update
//    fun altera(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id == :id")
    fun buscaPorID(id: Long) : Produto?
}
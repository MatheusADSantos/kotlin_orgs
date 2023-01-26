package MatheusADSantos.com.github.orgs.database.dao

import MatheusADSantos.com.github.orgs.model.Produto
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : Flow<List<Produto>>

    @Query("DELETE FROM Produto")
    suspend fun deletaTodos()

    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun buscaTodosOrdenadoPorNomeAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun buscaTodosOrdenadoPorNomeDesc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER By descricao ASC")
    fun buscaTodosOrdenadoPorDescricaoAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER By descricao DESC")
    fun buscaTodosOrdenadoPorDescricaoDesc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER By valor ASC")
    fun buscaTodosOrdenadoPorValorAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER By valor DESC")
    fun buscaTodosOrdenadoPorValorDesc(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg produto:Produto)

    @Delete
    fun remove(produto: Produto)

//    @Update
//    fun altera(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id == :id")
    fun buscaPorID(id: Long) : Flow<Produto?>
}
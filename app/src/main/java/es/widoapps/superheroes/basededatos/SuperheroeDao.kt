package es.widoapps.superheroes.basededatos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.widoapps.superheroes.modelo.Superheroe

@Dao
// Accederemos a través de aquí a los objetos de la base de datos
interface SuperheroeDao {

    // Podemos pasar la cantidad de objetos de Superheroe que queramos y retorna la lista de Primary Key
    // suspend -> necesita ser accedida desde otro hilo para no bloquear el hilo principal
    @Insert
    suspend fun insertarTodo(vararg superheroes: Superheroe): List<Long>

    @Query("SELECT * FROM superheroe")
    suspend fun getSuperheroes(): List<Superheroe>

    @Query("SELECT * FROM superheroe WHERE uuid = :superheroe")
    suspend fun getSuperheroe(superheroe: Int): Superheroe

    @Query("DELETE FROM superheroe")
    suspend fun borrarSuperheroes()
}
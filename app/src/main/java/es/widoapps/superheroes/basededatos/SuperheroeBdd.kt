package es.widoapps.superheroes.basededatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.widoapps.superheroes.modelo.Superheroe


@Database(entities = arrayOf(Superheroe::class), version = 1)
@TypeConverters(ConvertirBdd::class)
abstract class SuperheroeBdd : RoomDatabase() {

    // Es el objeto o clase que accede a la base de datos por nosotros, puede acceder a la base de datos en un momento determinado
    // y así poder ejecutar en diferentes hilos
    abstract fun superheroeDao(): SuperheroeDao

    // Funcion estática para utilizarla en cualquier clase
    companion object {
        @Volatile
        private var instance: SuperheroeBdd? = null
        private val LOCK = Any()

        // Comprobamos si ya ha sido creada o no con el operador elvis
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // Creamos la instancia y la retornamos al invocador
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SuperheroeBdd::class.java,
            "superheroebdd"
        ).build()
    }
}
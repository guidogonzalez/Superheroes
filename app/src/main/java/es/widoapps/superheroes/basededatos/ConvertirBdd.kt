package es.widoapps.superheroes.basededatos

import androidx.room.TypeConverter
import com.google.gson.Gson
import es.widoapps.superheroes.modelo.Apariencia
import es.widoapps.superheroes.modelo.Habilidad
import es.widoapps.superheroes.modelo.UrlImagen

class ConvertirBdd {

    @TypeConverter
    fun listaJsonHabilidad(value: Habilidad) = Gson().toJson(value)

    @TypeConverter
    fun jsonListaHabilidad(value: String) =
        Gson().fromJson(value, Habilidad::class.java)

    @TypeConverter
    fun listaJsonApariencia(value: Apariencia) = Gson().toJson(value)

    @TypeConverter
    fun jsonListaApariencia(value: String) =
        Gson().fromJson(value, Apariencia::class.java)

    @TypeConverter
    fun listaJsonUrlImagen(value: UrlImagen) = Gson().toJson(value)

    @TypeConverter
    fun jsonListaUrlImagen(value: String) =
        Gson().fromJson(value, UrlImagen::class.java)
}
package es.widoapps.superheroes.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Superheroe(

    @ColumnInfo(name = "id_superheroe")
    @SerializedName("id")
    val idSuperheroe: String?,

    @ColumnInfo(name = "nombre")
    @SerializedName("name")
    val nombre: String?,

    @ColumnInfo(name = "habilidad")
    @SerializedName("powerstats")
    val habilidad: Habilidad,

    @ColumnInfo(name = "apariencia")
    @SerializedName("appearance")
    val apariencia: Apariencia,

    @ColumnInfo(name = "url_imagen")
    @SerializedName("images")
    val urlImagen: UrlImagen
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

data class Habilidad(

    @SerializedName("intelligence")
    val inteligencia: String?,

    @SerializedName("strength")
    val fuerza: String?,

    @SerializedName("speed")
    val velocidad: String?,

    @SerializedName("durability")
    val durabilidad: String?,

    @SerializedName("power")
    val poder: String?,

    @SerializedName("combat")
    val combate: String?
) {
    fun getIntString(): String = "$inteligencia/100"
    fun getFueString(): String = "$fuerza/100"
    fun getVelString(): String = "$velocidad/100"
    fun getDurString(): String = "$durabilidad/100"
    fun getPodString(): String = "$poder/100"
    fun getComString(): String = "$combate/100"
}

data class Apariencia(

    @SerializedName("race")
    val raza: String?,

    @SerializedName("height")
    val altura: List<String>?,

    @SerializedName("weight")
    val peso: List<String>?
)

data class UrlImagen(

    @SerializedName("lg")
    val lg: String?
)
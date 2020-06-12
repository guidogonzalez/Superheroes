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

    @SerializedName("strenght")
    val fuerza: String?,

    @SerializedName("speed")
    val velocidad: String?,

    @SerializedName("durability")
    val durabilidad: String?,

    @SerializedName("power")
    val poder: String?,

    @SerializedName("combat")
    val combate: String?
)

data class Apariencia(

    @SerializedName("gender")
    val genero: String?,

    @SerializedName("race")
    val raza: String?,

    @SerializedName("height")
    val altura: List<String>?,

    @SerializedName("weight")
    val peso: List<String>?
)

data class UrlImagen(

    @SerializedName("xs")
    val xs: String?,

    @SerializedName("sm")
    val sm: String?,

    @SerializedName("md")
    val md: String?,

    @SerializedName("lg")
    val lg: String?
)
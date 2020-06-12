package es.widoapps.superheroes.modelo

import com.google.gson.annotations.SerializedName

data class Superheroe(

    @SerializedName("id")
    val idSuperheroe: String?,

    @SerializedName("name")
    val nombre: String?,

    @SerializedName("powerstats")
    val habilidad: Habilidad,

    @SerializedName("appearance")
    val apariencia: Apariencia,

    @SerializedName("images")
    val urlImagen: UrlImagen
)

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
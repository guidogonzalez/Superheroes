package es.widoapps.superheroes.api

import es.widoapps.superheroes.modelo.Superheroe
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroeApiService {

    private val BASE_URL = "https://akabab.github.io"

    // Nos permite llamar el endpoint y obtener la información para pasarla a nuestro objeto
    private val API = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        // Convierte la lista de elementos en un observable que es un Single
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SuperheroesApi::class.java)

    // Retornamos un observable
    fun getSuperheroes(): Single<List<Superheroe>> {
        return API.getSuperheroes()
    }
}
package es.widoapps.superheroes.api

import es.widoapps.superheroes.modelo.Superheroe
import io.reactivex.Single
import retrofit2.http.GET

interface SuperheroesApi {

    @GET("superhero-api/api/all.json")
    fun getSuperheroes(): Single<List<Superheroe>>
}
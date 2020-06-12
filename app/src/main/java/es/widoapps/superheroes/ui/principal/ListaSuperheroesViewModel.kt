package es.widoapps.superheroes.ui.principal

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import es.widoapps.superheroes.api.SuperheroeApiService
import es.widoapps.superheroes.modelo.Superheroe
import es.widoapps.superheroes.util.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope

class ListaSuperheroesViewModel(application: Application) : BaseViewModel(application),
    CoroutineScope {

    private val superheroeService = SuperheroeApiService()
    // Nos permite observar el observable que nos da la API sin tener que preocuparnos de deshacernos de ella (memory leak)
    private val disposable = CompositeDisposable()

    // Nos proporciona la información de la lista actual de superheroes que hemos cogido de la API o base de datos local
    val superheroes = MutableLiveData<List<Superheroe>>()
    // Notifica a quien este escuchando este ViewModel que hay un error (pero no específica cual) al recibir los datos
    val superheroesErrorCargar = MutableLiveData<Boolean>()
    // Notifica a quien este escuchando este ViewModel que el sistema está cargando (cuando no hay ningún error), es decir, los datos no han llegado todavía
    val cargando = MutableLiveData<Boolean>()

    fun recargar() {

        extraerBaseDatosRemota()
    }

    fun extraerBaseDatosRemota() {

        cargando.value = true

        disposable.add(
            // Cuando obtenemos los datos de la API, no queremos hacerlo en el hilo principal de la aplicación para no bloquearla
            superheroeService.getSuperheroes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Superheroe>>() {

                    override fun onSuccess(listaSuperheroes: List<Superheroe>) {

                        superheroesRecibido(listaSuperheroes)
                    }

                    override fun onError(e: Throwable) {
                        superheroesErrorCargar.value = true
                        cargando.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun superheroesRecibido(listaSuperheroes: List<Superheroe>) {
        superheroes.value = listaSuperheroes
        superheroesErrorCargar.value = false
        cargando.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
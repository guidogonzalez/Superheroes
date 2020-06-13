package es.widoapps.superheroes.ui.principal

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import es.widoapps.superheroes.api.SuperheroeApiService
import es.widoapps.superheroes.basededatos.SuperheroeBdd
import es.widoapps.superheroes.modelo.Superheroe
import es.widoapps.superheroes.util.BaseViewModel
import es.widoapps.superheroes.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    private var prefHelper = SharedPreferencesHelper(getApplication())
    // 5 días
    private var tiempoActualizacion = 432000000L

    fun recargar() {

        // Obtenemos hace cuanto hemos guardado la base de datos
        val updateTime = prefHelper.getUpdateTime()

        // Si no han pasado más de 5 minutos desde que hemos guardado la base de datos ejecutamos desde la base de datos, sino desde el endpoint
        if (updateTime != null && updateTime != 0L && (System.currentTimeMillis() - updateTime < tiempoActualizacion)) {
            extraerBddLocal()
        } else {
            extraerBddRemota()
        }
    }

    private fun extraerBddLocal() {

        cargando.value = true;

        launch {
            val superheroes = SuperheroeBdd(getApplication()).superheroeDao().getSuperheroes()
            superheroesRecibido(superheroes)
        }
    }

    fun extraerBddRemota() {

        cargando.value = true

        disposable.add(
            // Cuando obtenemos los datos de la API, no queremos hacerlo en el hilo principal de la aplicación para no bloquearla
            superheroeService.getSuperheroes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Superheroe>>() {

                    override fun onSuccess(listaSuperheroes: List<Superheroe>) {

                        guardarBddLocal(listaSuperheroes)
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

    private fun guardarBddLocal(list: List<Superheroe>) {

        // Guardamos en la base de datos en otro hilo
        launch {

            val dao = SuperheroeBdd(getApplication()).superheroeDao()
            // Borramos los datos de la base de datos para que no haya conflicto
            dao.borrarSuperheroes()
            // Coge una lista y la expande en elementos individuales
            val result = dao.insertarTodo(*list.toTypedArray())

            var i = 0

            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }

            // Actualizamos la lista del ViewModel
            superheroesRecibido(list)
        }

        // Guardamos cuando hemos guardado la base de datos
        prefHelper.saveUpdateTime(System.currentTimeMillis())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
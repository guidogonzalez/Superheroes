package es.widoapps.superheroes.ui.detalles

import android.app.Application
import androidx.lifecycle.MutableLiveData
import es.widoapps.superheroes.basededatos.SuperheroeBdd
import es.widoapps.superheroes.modelo.Superheroe
import es.widoapps.superheroes.util.BaseViewModel
import kotlinx.coroutines.launch

class SuperheroeViewModel(application: Application) : BaseViewModel(application) {

    val superheroeLiveData = MutableLiveData<Superheroe>()

    fun extraer(uuid: Int) {

        launch {
            val superheroe = SuperheroeBdd(getApplication()).superheroeDao().getSuperheroe(uuid)
            superheroeLiveData.value = superheroe
        }
    }
}
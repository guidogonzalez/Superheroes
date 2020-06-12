package es.widoapps.superheroes.ui.principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import es.widoapps.superheroes.R
import es.widoapps.superheroes.adaptador.ListaSuperheroesAdaptador
import kotlinx.android.synthetic.main.fragment_lista_superheroes.*

class ListaSuperheroesFragment : Fragment() {

    private lateinit var viewModel: ListaSuperheroesViewModel
    // Le pasamos una lista vacía
    private val superheroesAdaptador = ListaSuperheroesAdaptador(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_lista_superheroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListaSuperheroesViewModel::class.java)
        viewModel.recargar()

        rvSuperheroes.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = superheroesAdaptador
        }

        // Cuando recarguemos la pantalla ocultamos los siguientes valores y obtenemos la base de datos desde el endpoint
        refreshLayout.setOnRefreshListener {
            rvSuperheroes.visibility = View.GONE
            tvErrorCargar.visibility = View.GONE
            pbCargando.visibility = View.VISIBLE
            viewModel.extraerBddRemota()
            refreshLayout.isRefreshing = false
        }

        observarViewModel()
    }

    private fun observarViewModel() {

        viewModel.superheroes.observe(viewLifecycleOwner, Observer { superheroes ->

            // Comprobamos que no sea nulo
            superheroes?.let {
                rvSuperheroes.visibility = View.VISIBLE
                // Actualizamos la lista con los valores que obtenemos
                superheroesAdaptador.actualizarListaSuperheroes(superheroes)
            }
        })

        viewModel.superheroesErrorCargar.observe(viewLifecycleOwner, Observer { error ->

            // Comprobamos que no sea nulo
            error?.let {
                tvErrorCargar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.cargando.observe(viewLifecycleOwner, Observer { cargando ->

            // Comprobamos que no sea nulo
            cargando?.let {

                pbCargando.visibility = if (it) View.VISIBLE else View.GONE

                // Si está cargando ocultamos el RecyclerView y TextView
                if (it) {
                    tvErrorCargar.visibility = View.GONE
                    rvSuperheroes.visibility = View.GONE
                }
            }
        })
    }
}
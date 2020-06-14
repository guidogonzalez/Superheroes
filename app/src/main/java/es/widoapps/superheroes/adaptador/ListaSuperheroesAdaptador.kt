package es.widoapps.superheroes.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import es.widoapps.superheroes.R
import es.widoapps.superheroes.databinding.ItemSuperheroeBinding
import es.widoapps.superheroes.modelo.Superheroe
import es.widoapps.superheroes.ui.detalles.SuperheroeFragment
import es.widoapps.superheroes.ui.principal.ListaSuperheroesFragment
import es.widoapps.superheroes.ui.principal.ListaSuperheroesFragmentDirections
import es.widoapps.superheroes.util.SuperheroeClickListener
import kotlinx.android.synthetic.main.item_superheroe.view.*

class ListaSuperheroesAdaptador(private val listaSuperheroes: ArrayList<Superheroe>) :
    RecyclerView.Adapter<ListaSuperheroesAdaptador.SuperheroeViewHolder>(),
    SuperheroeClickListener {

    fun actualizarListaSuperheroes(nuevaListaSuperheroes: List<Superheroe>) {
        listaSuperheroes.clear()
        listaSuperheroes.addAll(nuevaListaSuperheroes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSuperheroeBinding>(
            inflater,
            R.layout.item_superheroe,
            parent,
            false
        )

        return SuperheroeViewHolder(view)
    }

    override fun getItemCount() = listaSuperheroes.size

    override fun onBindViewHolder(holder: SuperheroeViewHolder, position: Int) {

        holder.view.superheroe = listaSuperheroes[position]

        // this -> se refiere al adaptador
        holder.view.listener = this
    }

    class SuperheroeViewHolder(var view: ItemSuperheroeBinding) : RecyclerView.ViewHolder(view.root)

    override fun onSuperheroeClicked(view: View) {

        val uuid = view.IdSuperheroe.text.toString().toInt()

        val action =
            ListaSuperheroesFragmentDirections.actionListaSuperheroesFragmentToSuperheroeFragment()
        action.idSuperheroe = uuid
        Navigation.findNavController(view).navigate(action)
    }
}
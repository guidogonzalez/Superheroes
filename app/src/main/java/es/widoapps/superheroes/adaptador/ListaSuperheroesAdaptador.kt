package es.widoapps.superheroes.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import es.widoapps.superheroes.R
import es.widoapps.superheroes.databinding.ItemSuperheroeBinding
import es.widoapps.superheroes.modelo.Superheroe

class ListaSuperheroesAdaptador(private val listaSuperheroes: ArrayList<Superheroe>) : RecyclerView.Adapter<ListaSuperheroesAdaptador.SuperheroeViewHolder>() {

    fun actualizarListaSuperheroes(nuevaListaSuperheroes: List<Superheroe>) {
        listaSuperheroes.clear()
        listaSuperheroes.addAll(nuevaListaSuperheroes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSuperheroeBinding>(inflater, R.layout.item_superheroe, parent, false)

        return SuperheroeViewHolder(view)
    }

    override fun getItemCount() = listaSuperheroes.size

    override fun onBindViewHolder(holder: SuperheroeViewHolder, position: Int) {
        holder.view.superheroe = listaSuperheroes[position]
    }

    class SuperheroeViewHolder(var view: ItemSuperheroeBinding) : RecyclerView.ViewHolder(view.root)
}
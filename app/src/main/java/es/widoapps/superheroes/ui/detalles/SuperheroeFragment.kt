package es.widoapps.superheroes.ui.detalles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.widoapps.superheroes.R
import es.widoapps.superheroes.databinding.FragmentSuperheroeBinding

class SuperheroeFragment : Fragment() {

    private lateinit var viewModel: SuperheroeViewModel
    private var superheroeUuid: Int = 0
    private lateinit var dataBinding: FragmentSuperheroeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_superheroe, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            superheroeUuid = SuperheroeFragmentArgs.fromBundle(it).idSuperheroe
        }

        viewModel = ViewModelProviders.of(this).get(SuperheroeViewModel::class.java)
        viewModel.extraer(superheroeUuid)

        observarViewModel()
    }

    private fun observarViewModel() {

        viewModel.superheroeLiveData.observe(viewLifecycleOwner, Observer { superheroe ->

            superheroe?.let {
                dataBinding.superheroe = superheroe
            }
        })
    }
}
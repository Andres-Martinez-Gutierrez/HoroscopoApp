package com.example.horoscopoapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.horoscopoapp.R
import com.example.horoscopoapp.databinding.FragmentListBinding
import com.example.horoscopoapp.ui.details.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    //Inyectar ViewModel
    private val viewModel by viewModels<ListViewModel>()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCapricornio.setOnClickListener {
            //Ir A detalle
            openDetails()
        }

        binding.btnSagitario.setOnClickListener {
            //Ir A detalle
            openDetails()

        }

        binding.btnCancer.setOnClickListener {
            //Ir A detalle
            openDetails()
        }

    }

    private fun openDetails() {
        startActivity(DetailActivity.create(requireContext()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

}
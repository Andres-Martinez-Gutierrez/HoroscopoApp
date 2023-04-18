package com.example.horoscopoapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.horoscopoapp.R
import com.example.horoscopoapp.databinding.FragmentListBinding
import com.example.horoscopoapp.databinding.FragmentLuckyBinding
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
        }

        binding.btnSagitario.setOnClickListener {
            //Ir A detalle
        }

        binding.btnCancer.setOnClickListener {
            //Ir A detalle
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

}
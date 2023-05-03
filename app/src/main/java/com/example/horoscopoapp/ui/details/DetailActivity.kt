package com.example.horoscopoapp.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscopoapp.databinding.ActivityDetailBinding
import com.example.horoscopoapp.ui.details.model.DetailsUIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent = Intent(context, DetailActivity::class.java)
    }

    //Inyectar ViewModel
    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        viewModel.getHoroscope()

    }

    private fun initUI() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{ uiState->
                    when(uiState){
                        is DetailsUIState.Error -> {
                            //Mostrar Dialogo
                            binding.progressBar.isVisible = false
                        }
                        DetailsUIState.Loading -> {
                            //Mostrar Loading
                            binding.progressBar.isVisible = true
                        }
                        is DetailsUIState.Success -> {
                            //Mostrar la Información
                            binding.progressBar.isVisible = false
                            Toast.makeText(this@DetailActivity,uiState.horoscopeModel.horoscope,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}


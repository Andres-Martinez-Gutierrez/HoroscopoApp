package com.example.horoscopoapp.ui.lucky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.horoscopoapp.R
import com.example.horoscopoapp.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckyFragment : Fragment() {
    //Inyectar ViewModel
    private val viewModel by viewModels<LuckyViewModel>()

    private var _binding: FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val rotation = AnimationUtils.loadAnimation(requireContext(),R.anim.rotation)
//        binding.tvluckyFragment.startAnimation
        binding.viewBackContainer.viewBack.setOnClickListener {

            prepareCard()
            flipCard()
        }


    }

    private fun flipCard() {
        try {
            //Visibilidad
            binding.viewFrontContainer.viewFront.isVisible = true

            //Efecto 3D
            val scala = requireContext().resources.displayMetrics.scaledDensity
            val cameraDist = 8000 * scala
            binding.viewFrontContainer.viewFront.cameraDistance = cameraDist
            binding.viewBackContainer.viewBack.cameraDistance = cameraDist

            //Recuperamos y Seteamos el FLIP OUT
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_out) as AnimatorSet

            //Donde se pone la animacion FLIP OUT
            flipOutAnimatorSet.setTarget(binding.viewBackContainer.viewBack)

            //Recuperamos y Seteamos el FLIP In
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(requireContext(), R.animator.flip_in) as AnimatorSet

            //Donde se pone la animacion FLIP IN
            flipInAnimatorSet.setTarget(binding.viewFrontContainer.viewFront)

            //Iniciamos las Animaciones
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()

            flipInAnimatorSet.doOnEnd {
                binding.viewBackContainer.imLuckyBack.isVisible = false
                binding.tvLuckyInfo.animate().alpha(1f).duration = 1500
            }

        } catch (e: java.lang.Exception) {
            Log.e("Error: ", e.toString())
        }
    }

    private fun prepareCard() {
        val image = when (Random.nextInt(0, 5)) {
            0 -> R.drawable.card_moon
            1 -> R.drawable.card_fool
            2 -> R.drawable.card_hermit
            3 -> R.drawable.card_star
            4 -> R.drawable.card_sword
            5 -> R.drawable.card_sun
            else -> R.drawable.card_reverse
        }
        binding.viewFrontContainer.imLuckyFront.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                image
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }

}
package com.lugares_j.ui.lugar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lugares_j.R
import com.lugares_j.databinding.FragmentLugarBinding
import com.lugares_j.viewmodel.LugarViewModel

class LugarFragment : Fragment() {

    private var _binding: FragmentLugarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lugarViewModel =
            ViewModelProvider(this).get(LugarViewModel::class.java)

        _binding = FragmentLugarBinding.inflate(inflater, container, false)

        binding.addLugarFabButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_lugar_to_addLugarFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
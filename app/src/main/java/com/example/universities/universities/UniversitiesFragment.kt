package com.example.universities.universities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.universities.databinding.FragmentUniversitiesBinding

class UniversitiesFragment : Fragment() {

    private var _binding: FragmentUniversitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
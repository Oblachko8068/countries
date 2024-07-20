package com.example.universities.universities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.universities.databinding.FragmentUniversitiesBinding
import com.example.universities.universities.UniversitiesViewModel.Companion.searchUniversityLiveData

const val COUNTRY = "countries"

class UniversitiesFragment : Fragment(), UniversityRecyclerAdapter.OnUniversityClickListener {

    private var _binding: FragmentUniversitiesBinding? = null
    private val binding get() = _binding!!
    private val universitiesViewModel: UniversitiesViewModel by activityViewModels()
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country = arguments?.getString(COUNTRY).toString()
        universitiesViewModel.loadDataByCountry(country)
        recyclerView = binding.recyclerViewUniversity
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = UniversityRecyclerAdapter(
            emptyList(),
            this
        )
        val adapter = recyclerView?.adapter as? UniversityRecyclerAdapter
        val mediatorLiveData = universitiesViewModel.getMediatorLiveData()
        mediatorLiveData.observe(viewLifecycleOwner) {
            adapter?.setNewData(it)
        }
        searchUniversityLiveData.observe(viewLifecycleOwner) {
            adapter?.filterCountry(universitiesViewModel.getFilteredUniversities())
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                universitiesViewModel.setSearchText(newText.orEmpty())
                return true
            }

        })
    }

    override fun onUniversityClickListener(webPage: String) {
        TODO("открытие браузера")
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView = null
        _binding = null
    }

    companion object {
        fun newInstance(country: String): UniversitiesFragment {
            return UniversitiesFragment().apply {
                arguments = Bundle().apply {
                    putString(COUNTRY, country)
                }
            }
        }
    }
}
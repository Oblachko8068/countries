package com.example.universities.universities

import android.content.Intent
import android.net.Uri
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
        setUpRecyclerView()

        val adapter = recyclerView?.adapter as? UniversityRecyclerAdapter
        val mediatorLiveData = universitiesViewModel.getMediatorLiveData()
        mediatorLiveData.observe(viewLifecycleOwner) {
            adapter?.setNewData(it)
        }
        searchViewObserver(adapter)
    }

    private fun searchViewObserver(adapter: UniversityRecyclerAdapter?) {
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

    private fun setUpRecyclerView() {
        recyclerView = binding.recyclerViewUniversity
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = UniversityRecyclerAdapter(
            emptyList(),
            this
        )
    }

    override fun onUniversityClickListener(webPage: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webPage))
        context?.startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView = null
        _binding = null
    }

    companion object {
        const val COUNTRY = "countries"

        fun newInstance(country: String): UniversitiesFragment {
            return UniversitiesFragment().apply {
                arguments = Bundle().apply {
                    putString(COUNTRY, country)
                }
            }
        }
    }
}
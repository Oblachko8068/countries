package com.example.universities.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.universities.databinding.FragmentCountriesBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.universities.MainActivity
import com.example.universities.R
import com.example.universities.countries.CountriesViewModel.Companion.searchTextLiveData
import com.example.universities.universities.COUNTRY
import com.example.universities.universities.UniversitiesFragment

class CountriesFragment : Fragment(), CountryRecyclerAdapter.OnCountryClickListener {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!
    private val countriesViewModel: CountriesViewModel by activityViewModels()
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        if ((activity as? MainActivity)?.isFirstStart == true) {
            checkSavedCountry()
            (activity as? MainActivity)?.isFirstStart = false
        }
        val adapter = recyclerView?.adapter as? CountryRecyclerAdapter
        val mediatorLiveData = countriesViewModel.getMediatorLiveData()
        mediatorLiveData.observe(viewLifecycleOwner) {
            adapter?.setNewData(it)
        }
        searchTextLiveData.observe(viewLifecycleOwner) {
            adapter?.filterCountry(countriesViewModel.getFilteredCountries())
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                countriesViewModel.setSearchText(newText.orEmpty())
                return true
            }
        })
    }

    fun checkSavedCountry() {
        val savedCountry = countriesViewModel.getSharedPrefData()
        if (savedCountry != null){
            launchFragment(savedCountry)
        }
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.recyclerViewCountry
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = CountryRecyclerAdapter(
            emptyList(),
            this
        )
    }

    override fun onCountryItemClicked(country: String) {
        countriesViewModel.saveCountryToSharedPref(country)
        launchFragment(country)
    }

    private fun launchFragment(country: String){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, UniversitiesFragment.newInstance(country))
            .commitAllowingStateLoss()
    }

    companion object {
        fun newInstance(isFirstStart: Boolean): CountriesFragment {
            return CountriesFragment().apply {
                arguments = Bundle().apply {
                    putBoolean("isFirstStart", isFirstStart)
                }
            }
        }
    }
}
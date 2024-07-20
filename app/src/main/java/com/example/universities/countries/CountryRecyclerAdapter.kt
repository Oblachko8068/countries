package com.example.universities.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Country
import com.example.universities.databinding.CountryBlockBinding

class CountryRecyclerAdapter(
    private var countryList: List<Country>,
    private val countryClickListener: OnCountryClickListener,
) : RecyclerView.Adapter<CountryRecyclerAdapter.CountryViewHolder>() {

    interface OnCountryClickListener {

        fun onCountryItemClicked(country: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding =
            CountryBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    class CountryViewHolder(val binding: CountryBlockBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.binding.countryName.text = country.country
        holder.itemView.setOnClickListener {
            countryClickListener.onCountryItemClicked(country.country)
        }
    }

    override fun getItemCount(): Int = countryList.size

    class CountryDiffCallback(
        private val oldList: List<Country>,
        private val newList: List<Country>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].country == newList[newItemPosition].country
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }


    fun setNewData(newCountryList: List<Country>?) {
        if (newCountryList == null) return

        val diffCallback = CountryDiffCallback(countryList, newCountryList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        countryList = newCountryList
        diffResult.dispatchUpdatesTo(this)
    }

    fun filterCountry(filteredCountries: List<Country>) {
        val diffCallback = CountryDiffCallback(countryList, filteredCountries)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        countryList = filteredCountries
        diffResult.dispatchUpdatesTo(this)
    }
}
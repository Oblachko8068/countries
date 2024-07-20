package com.example.universities.universities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.University
import com.example.universities.databinding.UniversityBlockBinding

class UniversityRecyclerAdapter(
    private var universityList: List<University>,
    private val universityClickListener: OnUniversityClickListener,
) : RecyclerView.Adapter<UniversityRecyclerAdapter.UniversityViewHolder>() {

    interface OnUniversityClickListener {

        fun onUniversityClickListener(webPage: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UniversityViewHolder {
        val binding =
            UniversityBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding)
    }

    class UniversityViewHolder(val binding: UniversityBlockBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: UniversityViewHolder,
        position: Int
    ) {
        val university = universityList[position]
        holder.binding.universityName.text = university.name
        holder.binding.place.text = if (university.stateProvince == null) {
            university.alphaTwoCode
        } else {
            "${university.alphaTwoCode}, ${university.stateProvince}"
        }
        holder.itemView.setOnClickListener {
            universityClickListener.onUniversityClickListener(university.webPage)
        }
    }

    override fun getItemCount(): Int = universityList.size

    class UniversityDiffCallback(
        private val oldList: List<University>,
        private val newList: List<University>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    fun setNewData(newUniversityList: List<University>) {
        val diffCallback = UniversityDiffCallback(universityList, newUniversityList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        universityList = newUniversityList
        diffResult.dispatchUpdatesTo(this)
    }

    fun filterCountry(filteredUniversities: List<University>) {
        val diffCallback = UniversityDiffCallback(universityList, filteredUniversities)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        universityList = filteredUniversities
        diffResult.dispatchUpdatesTo(this)
    }
}
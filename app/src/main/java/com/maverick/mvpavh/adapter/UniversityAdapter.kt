package com.maverick.mvpavh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maverick.mvpavh.databinding.ItemUniversityBinding
import com.maverick.mvpavh.network.model.UniversityDTO

class UniversityAdapter : RecyclerView.Adapter<UniversityAdapter.UniversityHolder>() {

    private var productList = mutableListOf<UniversityDTO>()

    interface EventListener {
        fun onItemClick(position: Int, item: UniversityDTO)
    }

    private lateinit var mEventListener: EventListener

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemUniversityBinding.inflate(
            inflater,
            parent,
            false
        )
        return UniversityHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: UniversityHolder, position: Int) {
        val currentItem = productList[position]
        try {
            holder.itemBinding.tvName.text = currentItem.name
            holder.itemBinding.tvState.text = currentItem.stateProvince
            holder.itemBinding.tvCountry.text = currentItem.country

            holder.itemBinding.root.setOnClickListener {
                mEventListener.onItemClick(position, currentItem)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = productList.size

    fun addAll(mData: List<UniversityDTO>?) {
        productList.clear()
        productList.addAll(mData!!)
        notifyDataSetChanged()
    }

    inner class UniversityHolder(internal var itemBinding: ItemUniversityBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}
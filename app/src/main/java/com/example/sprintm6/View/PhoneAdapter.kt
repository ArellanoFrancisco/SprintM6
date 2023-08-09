package com.example.sprintm6.View

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.sprintm6.Model.Local.Entities.PhoneList
import com.example.sprintm6.R
import com.example.sprintm6.databinding.PhonelistBinding

class PhoneAdapter(): RecyclerView.Adapter<PhoneAdapter.PhoneViewholder>()  {

    private var listPhone = listOf<PhoneList>()
    private val selectedPhone= MutableLiveData<PhoneList>()

    fun update (list:List<PhoneList>){
        listPhone = list
        notifyDataSetChanged()
    }

    fun selectedPhone(): LiveData<PhoneList> = selectedPhone


    inner class PhoneViewholder (private val binding: PhonelistBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        fun bind(list: PhoneList){

            Glide.with(binding.imageRv).load(list.image).centerCrop().into(binding.imageRv)
            binding.textNameRv.text= binding.root.context.getString(R.string.NameRv, list.name)
            binding.textPriceRv.text= binding.root.context.getString(R.string.PriceRv, list.price)
            itemView.setOnClickListener(this)

        }
        override fun onClick(v:View) {
            // referencia a la selección
            selectedPhone.value= listPhone[adapterPosition]
            Log.d("ONCLICK",adapterPosition.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewholder {
       return PhoneViewholder(PhonelistBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun getItemCount(): Int = listPhone.size

    override fun onBindViewHolder(holder: PhoneViewholder, position: Int) {
        val phone = listPhone[position]
        holder.bind(phone)

    }


}
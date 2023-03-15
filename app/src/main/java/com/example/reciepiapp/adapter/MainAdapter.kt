package com.example.reciepiapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reciepiapp.R
import com.example.reciepiapp.databinding.ItemRecipesBinding
import com.example.reciepiapp.models.Results

class MainAdapter(private val context:Context,private val arrayList: ArrayList<Results>):RecyclerView.Adapter<MainAdapter.MainViewHolder> (){

    class MainViewHolder(val binding:ItemRecipesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view= LayoutInflater.from(parent.context)
        val binding:ItemRecipesBinding=DataBindingUtil.inflate(view, R.layout.item_recipes,parent,false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
          return  arrayList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       val currentItem=arrayList[position]

        Glide.with(context)
            .load(currentItem.image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imageView)

        holder.binding.title.text=currentItem.title
    }
}
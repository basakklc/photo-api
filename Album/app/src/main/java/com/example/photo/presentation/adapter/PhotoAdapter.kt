package com.example.photo.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photo.databinding.PhotoRowItemBinding
import com.example.photo.domain.model.PhotoResponse

class PhotoAdapter(context: Context, photoList: MutableList<PhotoResponse>, listener: PhotoAdapter.RecyclerviewClickListener): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    var photos = photoList
    var rcListener = listener
    var context = context

    override fun getItemCount(): Int {
        Log.e("tag-adapter", photos.size.toString())
        return photos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var photoRawItemBinding: PhotoRowItemBinding =PhotoRowItemBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(photoRawItemBinding)
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        var photo:PhotoResponse= photos.get(position)

        holder.photoRawItemBinding.photo = photo
        Glide.with(context)
            .load(photo.thumbnailUrl+".png")
            .into(holder.photoRawItemBinding.thumbnail)
    }

    fun loadData(list: List<PhotoResponse>){
        photos.addAll(list)
    }

    inner class PhotoViewHolder(var photoRawItemBinding: PhotoRowItemBinding)
        : RecyclerView.ViewHolder(photoRawItemBinding.root), View.OnClickListener {

        init{
            photoRawItemBinding.root.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            rcListener.onClick(p0!!, adapterPosition)
        }
    }

    interface RecyclerviewClickListener{
        fun onClick(v: View, position: Int)
    }
}

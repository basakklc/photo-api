package com.example.photo.presentation.adapter

import com.example.photo.databinding.CommentRowItemBinding
import com.example.photo.domain.model.CommentResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(commentList: MutableList<CommentResponse> ): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var comments = commentList

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var commentRawItemBinding: CommentRowItemBinding = CommentRowItemBinding.inflate(layoutInflater, parent, false)
        return CommentViewHolder(commentRawItemBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var comment:CommentResponse= comments.get(position)
        holder.commentRowBinding.comment = comment
    }

    fun loadData(list: List<CommentResponse>){
        comments.addAll(list)
    }

    inner class CommentViewHolder(var commentRowBinding: CommentRowItemBinding)
        : RecyclerView.ViewHolder(commentRowBinding.root) {

    }

}

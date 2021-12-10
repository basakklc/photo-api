package com.example.photo.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.photo.R
import com.example.photo.data.repository.CommentListingRepositoryImpl
import com.example.photo.databinding.ActivityCommentListingBinding
import com.example.photo.domain.model.CommentResponse
import com.example.photo.domain.model.CommentDto
import com.example.photo.domain.usecase.CommentListingUseCase
import com.example.photo.presentation.adapter.CommentAdapter
import com.example.photo.presentation.viewmodel.CommentListingViewModel
import com.example.photo.presentation.viewmodel.CommentListingViewModelFactory

class CommentListingActivity : AppCompatActivity() {

    private lateinit var adapter: CommentAdapter
    lateinit var binding: ActivityCommentListingBinding

    lateinit var bundle: Bundle
    lateinit var commentDto: CommentDto

    private lateinit var commentListingViewModel: CommentListingViewModel
    var repository= CommentListingRepositoryImpl()
    var photoDetailUseCase = CommentListingUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_listing)

        binding = DataBindingUtil.setContentView(this@CommentListingActivity,R.layout.activity_comment_listing)

        bundle = intent.extras!!

        commentDto = bundle.getSerializable( "commentDto") as CommentDto

        var factory = CommentListingViewModelFactory(photoDetailUseCase, commentDto.postID)
        commentListingViewModel = ViewModelProvider(this,factory).get(CommentListingViewModel::class.java)


        setupUI()
        observableViewModel()
    }

    private fun observableViewModel() {
        commentListingViewModel.getComments().observe(this, Observer { it ->
            Log.e("tag-test",it.toString())
            renderList(it)
        })
    }

    private fun renderList(response: List<CommentResponse>) {

            adapter.loadData(response)
            adapter.notifyDataSetChanged()


    }

    private fun setupUI() {
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        Glide.with(this)
            .load(commentDto.thumbnailUrl+".png")
            .into(binding.img)

        binding.tvCommentList.text = commentDto.title
        binding.rcylrComments.layoutManager = linearLayoutManager
        adapter = CommentAdapter(arrayListOf())
        binding.rcylrComments.adapter = adapter
    }

}

package com.example.photo.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.photo.R
import com.example.photo.data.repository.PhotoListingRepositoryImpl
import com.example.photo.databinding.ActivityPhotoListingBinding
import com.example.photo.domain.model.CommentDto
import com.example.photo.domain.model.PhotoResponse
import com.example.photo.domain.usecase.PhotoListingUseCase
import com.example.photo.presentation.adapter.PhotoAdapter
import com.example.photo.presentation.viewmodel.PhotoListingViewModel
import com.example.photo.presentation.viewmodel.PhotoListingViewModelFactory
import java.io.Serializable

class PhotoListingActivity : AppCompatActivity() {

    private lateinit var adapter: PhotoAdapter
    private lateinit var rcListener: PhotoAdapter.RecyclerviewClickListener
    lateinit var binding: ActivityPhotoListingBinding

    private lateinit var photoListingViewModel: PhotoListingViewModel
    var repository= PhotoListingRepositoryImpl()
    var photoListingUseCase = PhotoListingUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_listing)
        binding = DataBindingUtil.setContentView(this@PhotoListingActivity,R.layout.activity_photo_listing)

        var factory = PhotoListingViewModelFactory(photoListingUseCase)
        photoListingViewModel = ViewModelProvider(this,factory).get(PhotoListingViewModel::class.java)

        setupUI()
        photoListingViewModel.fetchPhotos()
        observablePhoto()

    }

    private fun setupUI() {
        setOnClickListener()

        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcylrPhotos.layoutManager = linearLayoutManager
        adapter = PhotoAdapter(this,arrayListOf(), rcListener)
        binding.rcylrPhotos.adapter = adapter

    }

    private fun setOnClickListener() {
        rcListener = object : PhotoAdapter.RecyclerviewClickListener {
            override fun onClick(v: View, position: Int) {
                    var photo = adapter.photos.get(position)
                    var commentDto = CommentDto(photo.id,photo.title,photo.thumbnailUrl)
                    var  intent = Intent(applicationContext,CommentListingActivity::class.java)
                    intent.putExtra("commentDto", commentDto as Serializable)
                    startActivity(intent)
            }
        }
    }

    private fun observablePhoto() {
        photoListingViewModel.getPhoto().observe(this,Observer{ photo ->
                renderList(photo)
        })
    }

    private fun renderList(response: List<PhotoResponse>) {
            mainPhotoBind(response.first().url)
            adapter.loadData(response)
            adapter.notifyDataSetChanged()
    }

    private fun mainPhotoBind(url:String) {
        Glide.with(this)
            .load(url+".png")
            .into(binding.imgMainPhoto)
    }
}

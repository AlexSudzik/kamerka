package com.example.kamerka

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cheese = findViewById<Button>(R.id.cheese)
        val photo = findViewById<ImageView>(R.id.photo)

        cheese.isEnabled = false

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
        }
        else
            cheese.isEnabled = true


        cheese.setOnClickListener(View.OnClickListener { view ->
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
        })
    }

    override fun onActivityResult(requestCode, Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101)
        {
            val binding = ActivityMainBinding.inflate(LayoutInflater)
            setContentView(binding.root)
            var photo: Bitmap?
            photo = data?.getParcelableExtra<Bitmap>("Data")
            binding.imageView.setImageBitmap(photo)
        }



    }
}
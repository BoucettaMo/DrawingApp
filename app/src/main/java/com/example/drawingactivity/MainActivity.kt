package com.example.drawingactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.drawingactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private lateinit var myViewModel: MyViewModel





    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         myViewModel= ViewModelProvider(this)[MyViewModel::class.java]
        draw(myViewModel.currentDrawing,myViewModel.listColor)


        binding.draw.setOnClickListener(this)
        binding.apply.setOnClickListener(this)
        binding.clear.setOnClickListener(this)
        binding.pencilLeft.setOnClickListener(this)
        binding.pencilRight.setOnClickListener(this)
        binding.pencilUp.setOnClickListener(this)
        binding.pencilDown.setOnClickListener(this)
        binding.down.setOnClickListener(this)
        binding.up.setOnClickListener(this)
        binding.left.setOnClickListener(this)
        binding.right.setOnClickListener(this)
        binding.red.setOnClickListener(this)
        binding.blue.setOnClickListener(this)
        binding.green.setOnClickListener(this)
        binding.yellow.setOnClickListener(this)
        binding.black.setOnClickListener(this)
        binding.orange.setOnClickListener(this)


    }


    private fun draw(list:List<Int>,listColor:HashMap<Int,Int>) {
        adapter=MyAdapter(applicationContext, list,myViewModel.pencil,myViewModel.color,listColor)
        binding.recyclerview.layoutManager=GridLayoutManager(applicationContext,20)
        binding.recyclerview.adapter=adapter
        myViewModel.currentDrawing=list
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.draw.id -> {
                myViewModel.isDrawing=true
                myViewModel.pencil=0
                draw(myViewModel.currentDrawing,myViewModel.listColor)
            }
            binding.apply.id -> {
                if (!myViewModel.isDrawing) return
                myViewModel.isDrawing=false
                myViewModel.currentDrawing+=myViewModel.pencil
                myViewModel.listColor[myViewModel.pencil]=myViewModel.color
                draw(myViewModel.currentDrawing,myViewModel.listColor)
                myViewModel.pencil=-1
            }
            binding.clear.id -> {
                myViewModel.isDrawing=false
                myViewModel.currentDrawing= listOf()
                myViewModel.listColor= hashMapOf()
                myViewModel.pencil=-1

                draw(myViewModel.currentDrawing,myViewModel.listColor)

            }
            binding.up.id -> {
                if (myViewModel.isDrawing) return
                val temp= hashMapOf<Int,Int>()
                for (i in myViewModel.currentDrawing) {
                    temp[Math.floorMod(i-20,400)]= myViewModel.listColor[i]!!
                }
                myViewModel.listColor=temp
                draw(myViewModel.currentDrawing.map {  Math.floorMod(it-20,400) },myViewModel.listColor)
            }
            binding.down.id -> {
                if (myViewModel.isDrawing) return
                val temp= hashMapOf<Int,Int>()
                for (i in myViewModel.currentDrawing) {
                    temp[Math.floorMod(i+20,400)]= myViewModel.listColor[i]!!
                }
                myViewModel.listColor=temp
                draw(myViewModel.currentDrawing.map { Math.floorMod(it + 20, 400) },myViewModel.listColor)
            }
            binding.left.id -> {
                if (myViewModel.isDrawing) return
                val temp= hashMapOf<Int,Int>()
                for (i in myViewModel.currentDrawing) {
                    temp[i-1]= myViewModel.listColor[i]!!
                }
                myViewModel.listColor=temp
                draw(myViewModel.currentDrawing.map {  it-1 },myViewModel.listColor)
            }
            binding.right.id -> {
                if (myViewModel.isDrawing) return
                val temp= hashMapOf<Int,Int>()
                for (i in myViewModel.currentDrawing) {
                    temp[i+1]= myViewModel.listColor[i]!!
                }
                myViewModel.listColor=temp
                draw(myViewModel.currentDrawing.map {  it+1 },myViewModel.listColor)
            }
            binding.pencilUp.id -> {
                if (!myViewModel.isDrawing) return
                myViewModel.pencil= Math.floorMod(myViewModel.pencil-20,400)
                draw(myViewModel.currentDrawing,myViewModel.listColor)
            }
            binding.pencilDown.id -> {
                if (!myViewModel.isDrawing) return
                myViewModel.pencil= Math.floorMod(myViewModel.pencil+20,400)
                draw(myViewModel.currentDrawing,myViewModel.listColor)
            }

            binding.pencilLeft.id -> {
                if (!myViewModel.isDrawing) return
                myViewModel.pencil--
                draw(myViewModel.currentDrawing,myViewModel.listColor)
            }
            binding.pencilRight.id -> {
                if (!myViewModel.isDrawing) return
                myViewModel.pencil++
                draw(myViewModel.currentDrawing,myViewModel.listColor)
            }
            binding.red.id -> myViewModel.color=R.color.red
            binding.blue.id -> myViewModel.color=R.color.blue
            binding.green.id -> myViewModel.color=R.color.green_dark
            binding.yellow.id -> myViewModel.color=R.color.yellow
            binding.orange.id -> myViewModel.color=R.color.orange
            binding.black.id -> myViewModel.color=R.color.black



        }
    }


}
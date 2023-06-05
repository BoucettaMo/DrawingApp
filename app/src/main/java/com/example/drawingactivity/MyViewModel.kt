package com.example.drawingactivity

import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {

     var pencil=-1
     var color=R.color.black
     private val permutation= arrayOf(1)
    var currentDrawing= listOf<Int>()
     var listColor= hashMapOf<Int,Int>()
     var isDrawing=false


    private fun coordinates(number: Int):Pair<Int,Int> {


        return Pair(number%10,permutation[number/10])

    }

    private fun inverse(pair:Pair<Int,Int>):Int {

        return permutation[pair.second]*10+pair.first
    }

    private fun verticalSymmetry(number:Int): Int {
        val permutation = arrayListOf(9,8,7,6,5,4,3,2,1,0)

        return permutation[number/10]*10+ number%10
    }

    private fun horizontalSymmetry(number:Int): Int {
        val permutation = arrayListOf(9,8,7,6,5,4,3,2,1,0)

        return (number/10)*10+ permutation[number%10]
    }




}
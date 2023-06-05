package com.example.drawingactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context:Context,private val list:List<Int>,private val pencil:Int,private val color:Int,
private val listColor:HashMap<Int,Int>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
      val textView:TextView=itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.model_item,parent,false)
        val layoutParams=view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width=parent.width/20
        layoutParams.height=parent.height/20
       // layoutParams.leftMargin=5
       // layoutParams.bottomMargin=5

        return ViewHolder(view)
    }

    override fun getItemCount()=400

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position==pencil) holder.textView.setBackgroundColor(ContextCompat.getColor(context,color))
      //  holder.textView.text="$position"
        if (position in list && listColor[position]!=null ) {
            holder.textView.setBackgroundColor(ContextCompat.getColor(context,listColor[position]!!))

        }

    }

}

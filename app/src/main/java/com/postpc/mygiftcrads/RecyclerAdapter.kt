package com.postpc.mygiftcrads

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val cards: ArrayList<GiftCard>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{

    lateinit var mListener : OnItemClickListener

    interface OnItemClickListener{
        fun onLongClick(position: Int): Boolean
    }

    fun setOnItemClickListener(listener : OnItemClickListener)
    {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder
    {
        val inflateView = parent.inflate(R.layout.recycler_view_card, false)
        return ViewHolder(inflateView, mListener)
    }

    override fun getItemCount(): Int
    {
        return cards.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int)
    {
        val card : GiftCard = cards[position]
        if(card.brand == "FOX")
        {
            holder.brandImage.setImageResource(R.drawable.fox)
        }
        else if(card.brand == "ACE")
        {
            holder.brandImage.setImageResource(R.drawable.ace)
        }
        else if(card.brand == "TOYSRUS")
        {
            holder.brandImage.setImageResource(R.drawable.toys_r_us)
        }
        holder.expDate.text = card.expDate
        holder.sum.text = card.value.toString()
        // set image, sum, expDate etc
    }

    class ViewHolder(v : View, listener : OnItemClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener, View.OnLongClickListener
    {
        private val TAG = "ViewHolder"
        private var view : View  = v
        private var listen : OnItemClickListener = listener

        val brandImage = view.findViewById<ImageView>(R.id.brandImageRecycler)
        val expDate = view.findViewById<TextView>(R.id.expDateRecycler)
        val sum = view.findViewById<TextView>(R.id.sumRecycler)

        init
        {
            v.setOnClickListener(this)
            v.setOnLongClickListener(this)
        }
        override fun onClick(v : View)
        {
            //set onClick
            Log.d(TAG, "clicked on recycler")
        }

        override fun onLongClick(view: View): Boolean {
            if(listen != null)
            {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                {
                    listen.onLongClick(position)
                }
            }
            return true
        }
    }

}
package com.hanseltritama.coroutinesmvvm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanseltritama.coroutinesmvvm.R
import com.hanseltritama.coroutinesmvvm.UserActivity
import com.hanseltritama.coroutinesmvvm.models.RecyclerData
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(context: Context?) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()
    val mContext = context

    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(mContext: Context?, view: View) : RecyclerView.ViewHolder(view) {
        private val imageThumb: ImageView = view.findViewById(R.id.image_thumbnail)
        private val titleText: TextView = view.findViewById(R.id.title_text)
        private val descText: TextView = view.findViewById(R.id.desc_text)
        private val card = view
        private val myContext = mContext

        fun bind(data: RecyclerData) {
            titleText.text = data.name
            descText.text = data.description

            val url = data.owner.avatar_url

            Picasso.get()
                .load(url)
                .into(imageThumb)

            card.setOnClickListener {
                val intent = Intent(myContext, UserActivity::class.java)
                intent.putExtra("login", data.owner.login)
                myContext?.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)
        return MyViewHolder(mContext, view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
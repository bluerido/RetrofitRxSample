package test.rido.com.myapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_item_search.view.*
import test.rido.com.myapplication.R
import test.rido.com.myapplication.data.GitInfo
import test.rido.com.myapplication.view.adapter.CustomRecyclerAdpater.ViewHolder


class CustomRecyclerAdpater(list: ArrayList<GitInfo>) :
    RecyclerView.Adapter<ViewHolder>() {
    private var data: ArrayList<GitInfo>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.recycler_item_search, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let {
            holder.tv.text = it.name
            Glide
                .with(holder.img.view_img_item_search.context)
                .load(it.url)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.img.view_img_item_search)
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal var tv: TextView = itemView.view_tv_item_search
        internal var img: ImageView = itemView.view_img_item_search
    }


}
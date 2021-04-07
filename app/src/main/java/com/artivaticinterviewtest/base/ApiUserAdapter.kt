package com.artivaticinterviewtest.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artivaticinterviewtest.R
import com.artivaticinterviewtest.model.RowsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*
import java.net.URL


class ApiUserAdapter(
    private val rowsItem: ArrayList<RowsItem>,
    private val context : Context
) : RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rowsItem: RowsItem, context: Context) {
            if(!rowsItem.title.isNullOrEmpty()){
                itemView.textTitle.visibility = View.VISIBLE
                itemView.textTitle.text = rowsItem.title
            }
            if(!rowsItem.description.isNullOrEmpty()){
                itemView.textDescription.visibility = View.VISIBLE
                itemView.textDescription.text = rowsItem.description
            }
            if(!rowsItem.imageHref.isNullOrEmpty()) {
//               itemView.imageViewAvatar?.setImageUrl(rowsItem.imageHref)
                Glide.with(context).load(URL(rowsItem.imageHref)).into(itemView.imageViewAvatar)
            }else{
                itemView.imageViewAvatar.visibility = View.GONE
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = rowsItem.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(rowsItem[position],context)

    fun addData(list: ArrayList<RowsItem?>?) {
        for( i in 0 until (list?.size ?: 0)){
            rowsItem.add(list!![i]!!)
        }
    }
}
package com.example.lotteryapp

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LotteryListAdapter(val dataList : MutableList<String>, val pref:SharedPreferences)
    : RecyclerView.Adapter<LotteryListAdapter.ItemViewHolder>()
{
    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // 한 항목을 표시할 레이아웃 관련 뷰를 만들어 줌
        // (viewType값이 바로 getItemViewType에서 반환한 레이아웃 리소스 식별자)
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val number = dataList[position]
        holder.view.findViewById<TextView>(R.id.num).text = number
        val deleteNumBtn = holder.view.findViewById<Button>(R.id.del_num)
        deleteNumBtn.setOnClickListener {
            dataList.removeAt(position)
            saveLottoNumArray(pref, dataList)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int = R.layout.lotto_num_item
}
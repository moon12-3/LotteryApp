package com.example.lotteryapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class showLottoNum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_lotto_num)

        val lottoSize = intent.getIntExtra("size", 0)
        Toast.makeText(this, "${lottoSize}개의 로또 번호가 저장되어 있습니다.", Toast.LENGTH_SHORT)

        val pref = getSharedPreferences("lotto", Context.MODE_PRIVATE)
        val lottoStr = pref.getString("lottonums", "")
        val lottoNum = if (lottoStr == "") {
            mutableListOf<String>()
        } else {
            lottoStr!!.split(",").toMutableList()
        }

        val layoutManager = LinearLayoutManager(this)

        val adapter = LotteryListAdapter(lottoNum, pref)

        val recyclerView = findViewById<RecyclerView>(R.id.lotto_list)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}


package com.example.lotteryapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var currentNums : String

    private fun generateRandomLottoNum(num : Int, sep : String = "-"): String {
        val nums = mutableListOf<Int>()
        for (i in 1..num) nums.add((1..45).random())
        return nums.joinToString(sep)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("lotto", Context.MODE_PRIVATE)

        val lottoNumView = findViewById<TextView>(R.id.lotto_num)

        lottoNumView.text = "로또 번호"

        val generateNumberBtn = findViewById<Button>(R.id.gen_num)
            generateNumberBtn.setOnClickListener {
                currentNums = generateRandomLottoNum(6)
                lottoNumView.text = currentNums
            }

        val saveNumberBtn = findViewById<Button>(R.id.sav_num)
        saveNumberBtn.setOnClickListener {
            var lottoNums = pref.getString("lottonums", "")
            var numList = if(lottoNums=="") {
                mutableListOf<String>()
            }
            else {
                lottoNums!!.split(",").toMutableList()
            }
            numList.add(currentNums)
            saveLottoNumArray(pref, numList)
        }
        val showNumberBtn = findViewById<Button>(R.id.show_num)
        showNumberBtn.setOnClickListener {
            val intent = Intent(this, showLottoNum::class.java)

            intent.putExtra("size", pref.getInt("size", 0))

            startActivity(intent)
        }

        val showLottoSite = findViewById<Button>(R.id.lotto_site)
        showLottoSite.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.dhlottery.co.kr/common.do?method=main"))
            startActivity(intent)
        }
    }

}
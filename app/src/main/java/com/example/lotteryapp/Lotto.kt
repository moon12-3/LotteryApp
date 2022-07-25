package com.example.lotteryapp

import android.content.SharedPreferences

fun saveLottoNumArray(pref:SharedPreferences, numList:MutableList<String>) {
    val editor = pref.edit()
    var lottoNumStr = numList.joinToString(",")
    editor.putString("lottonums", lottoNumStr)
    editor.putInt("size", numList.size)
    editor.apply()
}
package com.example.midtermapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.midtermapp.DateUtil.getCurrentDate
import com.example.midtermapp.databinding.ActivityPlayBinding
import java.util.Random

class PlayActivity : AppCompatActivity() {
    var binding: ActivityPlayBinding? = null
    var right_num = 0
    var COUNT = 0
    var des = ""
    var scoreDao: ScoreDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play)
        scoreDao = MainApplication.Companion.getInstance().getScoreDataBase().scoreDao()
        //生成1-100随机数
        val random = Random()
        right_num = random.nextInt(100)
        //点击加号
        binding.jian.setOnClickListener(View.OnClickListener {
            val number = binding.etGuess.text.toString()
            val name = binding.etName.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(applicationContext, "Enter name", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(applicationContext, "Enter number", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            binding.etGuess.setText((number.toInt() - 1).toString())
        })
        //点击减号
        binding.jia.setOnClickListener(View.OnClickListener {
            val number = binding.etGuess.text.toString()
            val name = binding.etName.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(applicationContext, "Enter name", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(applicationContext, "Enter number", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            binding.etGuess.setText((number.toInt() + 1).toString())
        })
        binding.ok.setOnClickListener(View.OnClickListener {
            val name = binding.etName.text.toString()
            val number = binding.etGuess.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(applicationContext, "Enter name", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(applicationContext, "Enter number", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            compare(number.toInt(), name)
        })
    }

    private fun compare(num: Int, name: String) {
        ++COUNT
        des += """
            $name:${getCurrentDate(DateUtil.FORMAT)},number of times：$COUNT
            
            """.trimIndent()
        binding!!.tvResult.text = des
        if (num == right_num) {
            Toast.makeText(applicationContext, "right", Toast.LENGTH_SHORT).show()
            val score = Score()
            score.time = getCurrentDate(DateUtil.FORMAT)
            score.name = name
            score.count = COUNT
            scoreDao!!.insertScore(score)
            setResult(22, Intent().putExtra("name", name).putExtra("count", COUNT))
            finish()
            return
        }
        if (num < right_num) {
            Toast.makeText(
                applicationContext,
                "The entered number is smaller than the correct number",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        Toast.makeText(
            applicationContext,
            "The entered number is greater than the correct number",
            Toast.LENGTH_SHORT
        ).show()
    }
}
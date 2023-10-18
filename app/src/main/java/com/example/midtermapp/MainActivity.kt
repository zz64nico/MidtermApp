package com.example.midtermapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.midtermapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun scores(view: View?) {
        startActivity(Intent(this@MainActivity, ScoreListActivity::class.java))
    }

    fun play(view: View?) {
        startActivityForResult(Intent(this@MainActivity, PlayActivity::class.java), 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == 22) {
            val name = data!!.getStringExtra("name")
            val count = data.getIntExtra("count", 0)
            if (count != 0) {
                binding!!.tvTips.text = "$name Score:$count\nPlay Another Game?"
            } else {
                binding!!.tvTips.text = "WellCome to the game"
            }
        }
    }
}
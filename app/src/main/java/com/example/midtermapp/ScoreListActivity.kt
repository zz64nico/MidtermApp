package com.example.midtermapp

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermapp.databinding.ActivityScoreBinding

class ScoreListActivity : AppCompatActivity() {
    var binding: ActivityScoreBinding? = null
    var scoreAdapter: ScoreAdapter? = null
    var scoreDao: ScoreDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score)
        scoreAdapter = ScoreAdapter()
        scoreDao = MainApplication.Companion.getInstance().getScoreDataBase().scoreDao()
        binding.rlScore.layoutManager = LinearLayoutManager(
            applicationContext
        )
        binding.rlScore.addItemDecoration(MyItemDecoration(10))
        binding.rlScore.adapter = scoreAdapter
        val scoreList = scoreDao!!.queryScoreList()
        if (scoreList == null || scoreList.size == 0) {
            binding.tvTips.visibility = View.VISIBLE
            return
        }
        val levels: MutableList<Level> = ArrayList()
        val levelList: MutableList<Level> = ArrayList()
        for (i in scoreList.indices) {
            levels.add(Level(i + 1, scoreList[i].name, scoreList[i].time, scoreList[i].count))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            levels.sort(java.util.Comparator { o1, o2 ->
                val one = o1.count
                val two = o2.count
                one.compareTo(two)
            })
        }
        for (l in levels.indices) {
            levelList.add(Level(l + 1, levels[l].name, levels[l].time, levels[l].count))
        }
        scoreAdapter!!.setNewData(levelList)
        scoreAdapter!!.notifyDataSetChanged()
    }
}
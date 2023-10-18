package com.example.midtermapp

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ScoreAdapter : BaseQuickAdapter<Level, BaseViewHolder>(R.layout.item_level) {
    override fun convert(baseViewHolder: BaseViewHolder, level: Level) {
        baseViewHolder.setText(R.id.tv_count, "Count：" + level.count + "")
        baseViewHolder.setText(R.id.tv_name, "Name：" + level.name + "")
        baseViewHolder.setText(R.id.tv_time, "Time：" + level.time + "")
        baseViewHolder.setText(R.id.tv_level, "Ranking：" + level.level + "")
    }
}
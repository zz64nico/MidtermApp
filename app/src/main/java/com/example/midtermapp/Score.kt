package com.example.midtermapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Score {
    var name //姓名
            : String? = null

    @PrimaryKey
    var time //时间
            : String = null
    var count //次数
            = 0
}
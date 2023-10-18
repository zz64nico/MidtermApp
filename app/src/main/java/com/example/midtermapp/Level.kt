package com.example.midtermapp

import java.io.Serializable

class Level : Serializable {
    var level = 0
    var name //姓名
            : String? = null
    var time //时间
            : String? = null
    var count //次数
            = 0

    constructor() {}
    constructor(level: Int, name: String?, time: String?, count: Int) {
        this.level = level
        this.name = name
        this.time = time
        this.count = count
    }
}
package com.hakjae.lotto.lib.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Lotto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lottoDao(): LottoDao
}
package com.hakjae.lotto.lib.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lotto(
    @PrimaryKey(autoGenerate = true) val idx: Int = 0,
    @ColumnInfo(name = "round") val round: Int,
    @ColumnInfo(name = "A_one") val A_one: Int,
    @ColumnInfo(name = "A_two") val A_two: Int,
    @ColumnInfo(name = "A_three") val A_three: Int,
    @ColumnInfo(name = "A_four") val A_four: Int,
    @ColumnInfo(name = "A_five") val A_five: Int,
    @ColumnInfo(name = "A_six") val A_six: Int,
    @ColumnInfo(name = "B_one") val B_one: Int? = null,
    @ColumnInfo(name = "B_two") val B_two: Int? = null,
    @ColumnInfo(name = "B_three") val B_three: Int? = null,
    @ColumnInfo(name = "B_four") val B_four: Int? = null,
    @ColumnInfo(name = "B_five") val B_five: Int? = null,
    @ColumnInfo(name = "B_six") val B_six: Int? = null,
    @ColumnInfo(name = "C_one") val C_one: Int? = null,
    @ColumnInfo(name = "C_two") val C_two: Int? = null,
    @ColumnInfo(name = "C_three") val C_three: Int? = null,
    @ColumnInfo(name = "C_four") val C_four: Int? = null,
    @ColumnInfo(name = "C_five") val C_five: Int? = null,
    @ColumnInfo(name = "C_six") val C_six: Int? = null,
    @ColumnInfo(name = "D_one") val D_one: Int? = null,
    @ColumnInfo(name = "D_two") val D_two: Int? = null,
    @ColumnInfo(name = "D_three") val D_three: Int? = null,
    @ColumnInfo(name = "D_four") val D_four: Int? = null,
    @ColumnInfo(name = "D_five") val D_five: Int? = null,
    @ColumnInfo(name = "D_six") val D_six: Int? = null,
    @ColumnInfo(name = "E_one") val E_one: Int? = null,
    @ColumnInfo(name = "E_two") val E_two: Int? = null,
    @ColumnInfo(name = "E_three") val E_three: Int? = null,
    @ColumnInfo(name = "E_four") val E_four: Int? = null,
    @ColumnInfo(name = "E_five") val E_five: Int? = null,
    @ColumnInfo(name = "E_six") val E_six: Int? = null,
    @ColumnInfo(name = "date") val date: String
)
package com.hakjae.lotto.lib.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LottoDao {
    @Query("SELECT * FROM lotto")
    fun getAll(): List<Lotto>

    @Query("SELECT * FROM lotto ORDER BY round DESC, idx DESC LIMIT :start, :end")
    fun getLottoData(start: Int = 0, end: Int = 5): List<Lotto>

    @Insert
    fun insertAll(entity: Lotto)

    @Query("DELETE FROM lotto")
    fun deleteAll()

    @Query("DELETE FROM lotto WHERE idx = :idx")
    fun deleteData(idx: Int)

    @Query("UPDATE lotto SET round = :round, A_one = :aOne, A_two = :aTwo, A_three = :aThree, A_four = :aFour, A_five = :aFive, A_six = :aSix, B_one = :bOne, B_two = :bTwo, B_three = :bThree, B_four = :bFour, B_five = :bFive, B_six = :bSix, C_one = :cOne, C_two = :cTwo, C_three = :cThree, C_four = :cFour, C_five = :cFive, C_six = :cSix, D_one = :dOne, D_two = :dTwo, D_three = :dThree, D_four = :dFour, D_five = :dFive, D_six = :dSix,E_one = :eOne, E_two = :eTwo, E_three = :eThree, E_four = :eFour, E_five = :eFive, E_six = :eSix, date = :date WHERE idx = :idx")
    fun updateData(
        idx: Int, round: Int?, aOne: Int?, aTwo: Int?, aThree: Int?, aFour: Int?, aFive: Int?,
        aSix: Int?, bOne: Int?, bTwo: Int?, bThree: Int?, bFour: Int?, bFive: Int?, bSix: Int?,
        cOne: Int?, cTwo: Int?, cThree: Int?, cFour: Int?, cFive: Int?, cSix: Int?, dOne: Int?,
        dTwo: Int?, dThree: Int?, dFour: Int?, dFive: Int?, dSix: Int?, eOne: Int?, eTwo: Int?,
        eThree: Int?, eFour: Int?, eFive: Int?, eSix: Int?, date: String
    )

    @Query("SELECT COUNT(*) FROM lotto")
    fun getCount(): Int
}
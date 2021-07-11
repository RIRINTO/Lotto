package com.hakjae.lotto.http

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("totSellamnt")
    @Expose
    private var totSellamnt: Long? = null

    @SerializedName("returnValue")
    @Expose
    private var returnValue: String? = null

    @SerializedName("drwNoDate")
    @Expose
    private var drwNoDate: String? = null

    @SerializedName("firstWinamnt")
    @Expose
    private var firstWinamnt: Long? = null

    @SerializedName("drwtNo6")
    @Expose
    private var drwtNo6: Int? = null

    @SerializedName("drwtNo4")
    @Expose
    private var drwtNo4: Int? = null

    @SerializedName("firstPrzwnerCo")
    @Expose
    private var firstPrzwnerCo: Int? = null

    @SerializedName("drwtNo5")
    @Expose
    private var drwtNo5: Int? = null

    @SerializedName("bnusNo")
    @Expose
    private var bnusNo: Int? = null

    @SerializedName("firstAccumamnt")
    @Expose
    private var firstAccumamnt: Long? = null

    @SerializedName("drwNo")
    @Expose
    private var drwNo: Int? = null

    @SerializedName("drwtNo2")
    @Expose
    private var drwtNo2: Int? = null

    @SerializedName("drwtNo3")
    @Expose
    private var drwtNo3: Int? = null

    @SerializedName("drwtNo1")
    @Expose
    private var drwtNo1: Int? = null

    public fun getTotSellamnt(): Long? {
        return totSellamnt
    }

    public fun setTotSellamnt(totSellamnt: Long?) {
        this.totSellamnt = totSellamnt
    }

    public fun getReturnValue(): String? {
        return returnValue
    }

    public fun setReturnValue(returnValue: String?) {
        this.returnValue = returnValue
    }

    public fun getDrwNoDate(): String? {
        return drwNoDate
    }

    public fun setDrwNoDate(drwNoDate: String?) {
        this.drwNoDate = drwNoDate
    }

    public fun getFirstWinamnt(): Long? {
        return firstWinamnt
    }

    public fun setFirstWinamnt(firstWinamnt: Long?) {
        this.firstWinamnt = firstWinamnt
    }

    public fun getDrwtNo6(): Int? {
        return drwtNo6
    }

    public fun setDrwtNo6(drwtNo6: Int?) {
        this.drwtNo6 = drwtNo6
    }

    public fun getDrwtNo4(): Int? {
        return drwtNo4
    }

    public fun setDrwtNo4(drwtNo4: Int?) {
        this.drwtNo4 = drwtNo4
    }

    public fun getFirstPrzwnerCo(): Int? {
        return firstPrzwnerCo
    }

    public fun setFirstPrzwnerCo(firstPrzwnerCo: Int?) {
        this.firstPrzwnerCo = firstPrzwnerCo
    }

    public fun getDrwtNo5(): Int? {
        return drwtNo5
    }

    public fun setDrwtNo5(drwtNo5: Int?) {
        this.drwtNo5 = drwtNo5
    }

    public fun getBnusNo(): Int? {
        return bnusNo
    }

    public fun setBnusNo(bnusNo: Int?) {
        this.bnusNo = bnusNo
    }

    public fun getFirstAccumamnt(): Long? {
        return firstAccumamnt
    }

    public fun setFirstAccumamnt(firstAccumamnt: Long?) {
        this.firstAccumamnt = firstAccumamnt
    }

    public fun getDrwNo(): Int? {
        return drwNo
    }

    public fun setDrwNo(drwNo: Int?) {
        this.drwNo = drwNo
    }

    public fun getDrwtNo2(): Int? {
        return drwtNo2
    }

    public fun setDrwtNo2(drwtNo2: Int?) {
        this.drwtNo2 = drwtNo2
    }

    public fun getDrwtNo3(): Int? {
        return drwtNo3
    }

    public fun setDrwtNo3(drwtNo3: Int?) {
        this.drwtNo3 = drwtNo3
    }

    public fun getDrwtNo1(): Int? {
        return drwtNo1
    }

    public fun setDrwtNo1(drwtNo1: Int?) {
        this.drwtNo1 = drwtNo1
    }
}
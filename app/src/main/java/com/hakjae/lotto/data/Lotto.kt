package com.hakjae.lotto.data

import java.io.Serializable

class Lotto : Serializable {

    private var idx: Int? = null
    private var round: Int? = null
    private var AOne: Int? = null
    private var ATwo: Int? = null
    private var AThree: Int? = null
    private var AFour: Int? = null
    private var AFive: Int? = null
    private var ASix: Int? = null
    private var BOne: Int? = null
    private var BTwo: Int? = null
    private var BThree: Int? = null
    private var BFour: Int? = null
    private var BFive: Int? = null
    private var BSix: Int? = null
    private var COne: Int? = null
    private var CTwo: Int? = null
    private var CThree: Int? = null
    private var CFour: Int? = null
    private var CFive: Int? = null
    private var CSix: Int? = null
    private var DOne: Int? = null
    private var DTwo: Int? = null
    private var DThree: Int? = null
    private var DFour: Int? = null
    private var DFive: Int? = null
    private var DSix: Int? = null
    private var EOne: Int? = null
    private var ETwo: Int? = null
    private var EThree: Int? = null
    private var EFour: Int? = null
    private var EFive: Int? = null
    private var ESix: Int? = null
    private var date: String? = null

    public fun getIdx(): Int? {
        return idx
    }

    public fun setIdx(idx: Int) {
        this.idx = idx
    }


    public fun getRound(): Int? {
        return round
    }

    public fun setRound(round: Int) {
        this.round = round
    }

    public fun getAOne(): Int? {
        return AOne
    }

    public fun setAOne(AOne: Int) {
        this.AOne = AOne
    }

    public fun getATwo(): Int? {
        return ATwo
    }

    public fun setATwo(ATwo: Int) {
        this.ATwo = ATwo
    }

    public fun getAThree(): Int? {
        return AThree
    }

    public fun setAThree(AThree: Int) {
        this.AThree = AThree
    }

    public fun getAFour(): Int? {
        return AFour
    }

    public fun setAFour(AFour: Int) {
        this.AFour = AFour
    }

    public fun getAFive(): Int? {
        return AFive
    }

    public fun setAFive(AFive: Int) {
        this.AFive = AFive
    }

    public fun getASix(): Int? {
        return ASix
    }

    public fun setASix(ASix: Int) {
        this.ASix = ASix
    }

    public fun getBOne(): Int? {
        return BOne
    }

    public fun setBOne(BOne: Int) {
        this.BOne = BOne
    }

    public fun getBTwo(): Int? {
        return BTwo
    }

    public fun setBTwo(BTwo: Int) {
        this.BTwo = BTwo
    }

    public fun getBThree(): Int? {
        return BThree
    }

    public fun setBThree(BThree: Int) {
        this.BThree = BThree
    }

    public fun getBFour(): Int? {
        return BFour
    }

    public fun setBFour(BFour: Int) {
        this.BFour = BFour
    }

    public fun getBFive(): Int? {
        return BFive
    }

    public fun setBFive(BFive: Int) {
        this.BFive = BFive
    }

    public fun getBSix(): Int? {
        return BSix
    }

    public fun setBSix(BSix: Int) {
        this.BSix = BSix
    }

    public fun getCOne(): Int? {
        return COne
    }

    public fun setCOne(COne: Int) {
        this.COne = COne
    }

    public fun getCTwo(): Int? {
        return CTwo
    }

    public fun setCTwo(CTwo: Int) {
        this.CTwo = CTwo
    }

    public fun getCThree(): Int? {
        return CThree
    }

    public fun setCThree(CThree: Int) {
        this.CThree = CThree
    }

    public fun getCFour(): Int? {
        return CFour
    }

    public fun setCFour(CFour: Int) {
        this.CFour = CFour
    }

    public fun getCFive(): Int? {
        return CFive
    }

    public fun setCFive(CFive: Int) {
        this.CFive = CFive
    }

    public fun getCSix(): Int? {
        return CSix
    }

    public fun setCSix(CSix: Int) {
        this.CSix = CSix
    }

    public fun getDOne(): Int? {
        return DOne
    }

    public fun setDOne(DOne: Int) {
        this.DOne = DOne
    }

    public fun getDTwo(): Int? {
        return DTwo
    }

    public fun setDTwo(DTwo: Int) {
        this.DTwo = DTwo
    }

    public fun getDThree(): Int? {
        return DThree
    }

    public fun setDThree(DThree: Int) {
        this.DThree = DThree
    }

    public fun getDFour(): Int? {
        return DFour
    }

    public fun setDFour(DFour: Int) {
        this.DFour = DFour
    }

    public fun getDFive(): Int? {
        return DFive
    }

    public fun setDFive(DFive: Int) {
        this.DFive = DFive
    }

    public fun getDSix(): Int? {
        return DSix
    }

    public fun setDSix(DSix: Int) {
        this.DSix = DSix
    }

    public fun getEOne(): Int? {
        return EOne
    }

    public fun setEOne(EOne: Int) {
        this.EOne = EOne
    }

    public fun getETwo(): Int? {
        return ETwo
    }

    public fun setETwo(ETwo: Int) {
        this.ETwo = ETwo
    }

    public fun getEThree(): Int? {
        return EThree
    }

    public fun setEThree(EThree: Int) {
        this.EThree = EThree
    }

    public fun getEFour(): Int? {
        return EFour
    }

    public fun setEFour(EFour: Int) {
        this.EFour = EFour
    }

    public fun getEFive(): Int? {
        return EFive
    }

    public fun setEFive(EFive: Int) {
        this.EFive = EFive
    }

    public fun getESix(): Int? {
        return ESix
    }

    public fun setESix(ESix: Int) {
        this.ESix = ESix
    }

    public fun getDate(): String? {
        return date
    }

    public fun setDate(date: String) {
        this.date = date
    }

    public fun setA(AOne: Int, ATwo: Int, AThree: Int, AFour: Int, AFive: Int, ASix: Int) {
        this.AOne = AOne
        this.ATwo = ATwo
        this.AThree = AThree
        this.AFour = AFour
        this.AFive = AFive
        this.ASix = ASix
    }

    public fun setB(BOne: Int, BTwo: Int, BThree: Int, BFour: Int, BFive: Int, BSix: Int) {
        this.BOne = BOne
        this.BTwo = BTwo
        this.BThree = BThree
        this.BFour = BFour
        this.BFive = BFive
        this.BSix = BSix
    }

    public fun setC(COne: Int, CTwo: Int, CThree: Int, CFour: Int, CFive: Int, CSix: Int) {
        this.COne = COne
        this.CTwo = CTwo
        this.CThree = CThree
        this.CFour = CFour
        this.CFive = CFive
        this.CSix = CSix
    }

    public fun setD(DOne: Int, DTwo: Int, DThree: Int, DFour: Int, DFive: Int, DSix: Int) {
        this.DOne = DOne
        this.DTwo = DTwo
        this.DThree = DThree
        this.DFour = DFour
        this.DFive = DFive
        this.DSix = DSix
    }

    public fun setE(EOne: Int, ETwo: Int, EThree: Int, EFour: Int, EFive: Int, ESix: Int) {
        this.EOne = EOne
        this.ETwo = ETwo
        this.EThree = EThree
        this.EFour = EFour
        this.EFive = EFive
        this.ESix = ESix
    }
}
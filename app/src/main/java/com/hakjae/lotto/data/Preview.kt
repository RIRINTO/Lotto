package com.hakjae.lotto.data

class Preview(
    private var one: String,
    private var two: String,
    private var three: String,
    private var four: String,
    private var five: String,
    private var six: String
) {

    public fun getOne(): String {
        return one
    }

    public fun setOne(one: String) {
        this.one = one
    }

    public fun getTwo(): String {
        return two
    }

    public fun setTwo(two: String) {
        this.two = two
    }

    public fun getThree(): String {
        return three
    }

    public fun setThree(three: String) {
        this.three = three
    }

    public fun getFour(): String {
        return four
    }

    public fun setFour(four: String) {
        this.four = four
    }

    public fun getFive(): String {
        return five
    }

    public fun setFive(five: String) {
        this.five = five
    }

    public fun getSix(): String {
        return six
    }

    public fun setSix(six: String) {
        this.six = six
    }
}
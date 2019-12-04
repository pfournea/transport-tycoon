package be.transporttycoon.transporttycoon

object Timer {
    var time : Int = -1

    fun increaseTime() = time++

    fun resetTime() {
        time = -1
    }

}
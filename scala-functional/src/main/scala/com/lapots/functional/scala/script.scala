package com.lapots.functional.scala

object script {

    def curriedForm(): Unit = {
        def isDivisibleBy(d: Int)(n: Int) = {
            println(s"Checking $n by $d")
            n % d == 0
        }

        val divBy3 = isDivisibleBy(3) _
        println(s"9 disible by 3: ${ divBy3(9) }")
    }

    def main(args: Array[String]) : Unit = {}

}
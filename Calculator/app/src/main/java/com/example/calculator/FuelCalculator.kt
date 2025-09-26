package com.example.culculatortest

data class FuelComposition(
    val H: Double,
    val C: Double,
    val S: Double,
    val N: Double,
    val O: Double,
    val W: Double,
    val A: Double
)


class FuelCalculator {


    private fun kToDry(W: Double): Double = 100.0 / (100.0 - W)
    private fun kToCombustible(W: Double, A: Double): Double = 100.0 / (100.0 - W - A)


    fun toDryBasis(comp: FuelComposition): FuelComposition {
        val k = kToDry(comp.W)
        return FuelComposition(
            H = comp.H * k,
            C = comp.C * k,
            S = comp.S * k,
            N = comp.N * k,
            O = comp.O * k,
            W = 0.0,
            A = comp.A * k
        )
    }


    fun toCombustibleBasis(comp: FuelComposition): FuelComposition {
        val k = kToCombustible(comp.W, comp.A)
        return FuelComposition(
            H = comp.H * k,
            C = comp.C * k,
            S = comp.S * k,
            N = comp.N * k,
            O = comp.O * k,
            W = 0.0,
            A = 0.0
        )
    }


    fun calcQWorking(comp: FuelComposition): Double {
        return (339 * comp.C +
                1030 * comp.H -
                108.8 * (comp.O - comp.S) -
                25 * comp.W) / 1000.0
    }


    fun calcQDry(Qr: Double, W: Double): Double {
        return Qr * 100.0 / (100.0 - W)
    }

    fun calcQCombustible(Qr: Double, W: Double, A: Double): Double {
        return Qr * 100.0 / (100.0 - W - A)
    }

}
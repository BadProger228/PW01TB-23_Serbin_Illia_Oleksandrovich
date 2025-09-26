package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.culculatortest.FuelCalculator
import com.example.culculatortest.FuelComposition

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hField = findViewById<EditText>(R.id.H_id)
        val cField = findViewById<EditText>(R.id.C_id)
        val sField = findViewById<EditText>(R.id.S_id)
        val nField = findViewById<EditText>(R.id.N_id)
        val oField = findViewById<EditText>(R.id.O_id)
        val wField = findViewById<EditText>(R.id.W_id)
        val aField = findViewById<EditText>(R.id.APid)
        val resultView = findViewById<TextView>(R.id.Result)
        val button = findViewById<Button>(R.id.Calculate)

        button.setOnClickListener {
            try {
                val H = hField.text.toString().toDoubleOrNull() ?: 0.0
                val C = cField.text.toString().toDoubleOrNull() ?: 0.0
                val S = sField.text.toString().toDoubleOrNull() ?: 0.0
                val N = nField.text.toString().toDoubleOrNull() ?: 0.0
                val O = oField.text.toString().toDoubleOrNull() ?: 0.0
                val W = wField.text.toString().toDoubleOrNull() ?: 0.0
                val A = aField.text.toString().toDoubleOrNull() ?: 0.0

                val comp = FuelComposition(H, C, S, N, O, W, A)
                val calc = FuelCalculator()

                val Qr = calc.calcQWorking(comp)
                val Qd = calc.calcQDry(Qr, W)
                val Qdaf = calc.calcQCombustible(Qr, W, A)

                resultView.text = """
                    Q (as received) = ${"%.3f".format(Qr)} MJ/kg
                    Q (dry)         = ${"%.3f".format(Qd)} MJ/kg
                    Q (combustible) = ${"%.3f".format(Qdaf)} MJ/kg
                """.trimIndent()

            } catch (e: Exception) {
                Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

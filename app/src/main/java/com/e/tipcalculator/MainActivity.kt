package com.e.tipcalculator

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private lateinit var tip: TextView
    private lateinit var tipCount: TextView
    private lateinit var totalCount: TextView
    private lateinit var seekBar: SeekBar

    var num: Double? = 0.00


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        display.setOnEditorActionListener(editorListener())

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                tip.text = "Tip (" + p1 +"%)"

                var tipValue: Double?
                tipValue = (num!! * p1.toDouble()) / 100
                roundOffDecimal(tipValue)
                tipCount.text = tipValue.toString()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


    }

    private fun editorListener(): TextView.OnEditorActionListener =
            object: TextView.OnEditorActionListener {
        override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
            when(p1){
                EditorInfo.IME_ACTION_DONE -> {

                    num = ParseDouble(display.text.toString())

                    val total: Double?
                    total = num
                    totalCount.text = total.toString()

                }
            }


            return false
        }
    }

    fun ParseDouble(strNumber: String?): Double {
        return if (strNumber != null && strNumber.length > 0) {
            try {
                strNumber.toDouble()
            } catch (e: Exception) {
                (-1).toDouble() // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else (0).toDouble()
    }

    fun roundOffDecimal(num: Double): Double{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR

        return df.format(num).toDouble()
    }

    fun init(){
        display = findViewById(R.id.ET_calc)
        tip = findViewById(R.id.TV_tip)
        tipCount = findViewById(R.id.TV_tip_count)
        totalCount = findViewById(R.id.TV_total_count)
        seekBar = findViewById(R.id.seekBar)
    }
}
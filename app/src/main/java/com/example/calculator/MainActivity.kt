package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var userInput = StringBuilder()
    var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeCalculator()
    }

    private fun initializeCalculator() {
        operationalButtons()
        numberButtons()
        functionButtons()
        initListener()
    }

    private fun initListener() {
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    // check if there is an oparrand attached
                    if (operation == null)
                        return
                    if (!s?.toString().contains(operation!!)) {
                        return
                    }
                    Log.e("--->", s.toString())
                    Log.e("--->", operation!!)

                    if (it.toString().split(operation!!)[1] == "") {
                        return
                    }

                    /* get opparand and values */
                    var firstInput = it.toString().split(operation!!)[0].toInt()
                    var secondInput = it.toString().split(operation!!)[1].toInt()


                    when (operation) {
                        "+" -> {
                            var r = firstInput + secondInput
                            results.text = r.toString()
                        }
                        "-" -> {
                            var minus = firstInput - secondInput
                            results.text = minus.toString()
                        }
                        "*" -> {
                            var multiplication = firstInput * secondInput
                            results.text = multiplication.toString()
                        }
                        "/" -> {
                            var division = firstInput / secondInput
                            results.text = division.toString()
                        }
                    }
                }
            }

        })
    }

    private fun numberButtons() {
        seven.setOnClickListener { displayOnScreen("7") }
        eight.setOnClickListener { displayOnScreen("8") }
        nine.setOnClickListener { displayOnScreen("9") }
        three.setOnClickListener { displayOnScreen("3") }
        four.setOnClickListener { displayOnScreen("4") }
        five.setOnClickListener { displayOnScreen("5") }
        six.setOnClickListener { displayOnScreen("6") }
        two.setOnClickListener { displayOnScreen("2") }
        one.setOnClickListener { displayOnScreen("1") }
        zero.setOnClickListener { displayOnScreen("0") }
        decimal.setOnClickListener { displayOnScreen(".") }


    }

    private fun operationalButtons() {
        multiplication.setOnClickListener {
            operation = "*"
            displayOnScreen("*")
        }
        division.setOnClickListener {
            operation = "/"
            displayOnScreen("/")
        }
        plus.setOnClickListener {
            operation = "+"
            displayOnScreen("+")
        }
        minus.setOnClickListener {
            operation = "-"
            displayOnScreen("-")
        }

    }

    private fun functionButtons() {
        ce.setOnClickListener {
            input.text = ""
            results.text = ""
            userInput.clear()
        }
        clear.setOnClickListener {
            input.text = ""
        }
        equal.setOnClickListener {
            input.text = results.text
            results.text = " "
        }
    }


    private fun displayOnScreen(digit: String) {
        userInput.append(digit)
        input.text = userInput.toString()
    }


}

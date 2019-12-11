package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val btnP: Button = findViewById(R.id.btnP)
        val btnE: Button = findViewById(R.id.btnE)

        val btnDel: Button = findViewById(R.id.btnDel)
        val btnDiv: Button = findViewById(R.id.btnDiv)
        val btnMul: Button = findViewById(R.id.btnMul)
        val btnMin: Button = findViewById(R.id.btnMin)
        val btnPlu: Button = findViewById(R.id.btnPlu)

        val exp: TextView = findViewById(R.id.line)
        val ans: TextView = findViewById(R.id.answ)

        btn0.setOnClickListener { addSymbol(exp, btn0) }
        btn1.setOnClickListener { addSymbol(exp, btn1) }
        btn2.setOnClickListener { addSymbol(exp, btn2) }
        btn3.setOnClickListener { addSymbol(exp, btn3) }
        btn4.setOnClickListener { addSymbol(exp, btn4) }
        btn5.setOnClickListener { addSymbol(exp, btn5) }
        btn6.setOnClickListener { addSymbol(exp, btn6) }
        btn7.setOnClickListener { addSymbol(exp, btn7) }
        btn8.setOnClickListener { addSymbol(exp, btn8) }
        btn9.setOnClickListener { addSymbol(exp, btn9) }

        btnP.setOnClickListener { addSymbol(exp, btnP) }

        btnDiv.setOnClickListener { addSymbol(exp, btnDiv) }
        btnMul.setOnClickListener { addSymbol(exp, btnMul) }
        btnMin.setOnClickListener { addSymbol(exp, btnMin) }
        btnPlu.setOnClickListener { addSymbol(exp, btnPlu) }

        btnDel.setOnClickListener {
            val expLength = exp.text.length
            val ansLength = answ.text.length

            if (expLength > 1){
                ans.text = ""
                val expText = exp.text.toString()
                exp.text = expText.substring(expLength - 1)
            }
            else{
                ans.text = ""
                exp.text = ""
            }
        }
        btnDel.setOnLongClickListener {
            exp.text = ""
            ans.text = ""
            return@setOnLongClickListener false
        }

        btnE.setOnClickListener {
            if(exp.text.isNotEmpty()) {
                try {
                    val expression = ExpressionBuilder(exp.text.toString())
                        .build()
                    val result: Double = expression.evaluate()
                    ans.text = result.toString()
                } catch (ex: ArithmeticException) {
                    Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG)
                        .show()
                }catch (iax: IllegalArgumentException){
                    Toast.makeText(this, iax.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun addSymbol(exp: TextView, btn: Button) {
        val text: String = exp.text.toString()
        exp.text = text + "${btn.text}"
    }
}

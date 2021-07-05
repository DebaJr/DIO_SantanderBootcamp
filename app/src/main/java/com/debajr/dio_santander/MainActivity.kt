package com.debajr.dio_santander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import kotlinx.android.synthetic.main.activity_main.*

const val MAX_FALTAS = 10
const val NOTA_PARA_APROVACAO = 6f

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sw :SwitchCompat? = switch1

        val btnCalc : Button? = btnCalcular
        val notaText : TextView? = nota_final_valor
        val resultadoFinal : TextView? = resultado_valor

        btnCalc?.setOnClickListener {
            val nota1 = nota01_input.text.toString().toFloat()
            val nota2 = nota2_input.text.toString().toFloat()
            val faltas : Int = faltas_input?.text.toString().toInt()
            var notaFinal = 0f

            if (sw != null) {
                if(sw.isChecked) {
                    val peso1 = peso01_input?.text.toString().toFloat()
                    val peso2 = peso2_input?.text.toString().toFloat()
                    notaFinal  = mediaPonderada(nota1,peso1,nota2,peso2)
                } else {
                    notaFinal = mediaAritmetica(nota1,nota2)
                }
            }

            when (avaliar(faltas, notaFinal)){
                R.string.reprovado_faltas -> {
                    notaText?.text = notaFinal.format(2)
                    resultadoFinal?.text = resources.getString(R.string.reprovado_faltas)
                    resultadoFinal?.setTextColor(resources.getColor(R.color.app_error_msg))
                    resultadoFinal?.textSize = 16f
                }
                R.string.reprovado -> {
                    notaText?.text = notaFinal.format(2)
                    resultadoFinal?.text = resources.getString(R.string.reprovado)
                    resultadoFinal?.setTextColor(resources.getColor(R.color.app_error_msg))
                    resultadoFinal?.textSize = 24f
                }
                R.string.aprovado -> {
                    notaText?.text = notaFinal.format(2)
                    resultadoFinal?.text = resources.getString(R.string.aprovado)
                    resultadoFinal?.setTextColor(resources.getColor(R.color.app_success_msg))
                    resultadoFinal?.textSize = 24f
                }
            }
        }
    }

    fun mediaAritmetica(n1:Float, n2:Float) : Float{
        return (n1 + n2)/2f
    }

    fun mediaPonderada(n1:Float, peso1:Float, n2:Float, peso2:Float): Float{
        return ((n1 * peso1) + (n2 * peso2))/(peso1 + peso2)
    }

    fun avaliar(faltas : Int, notaFinal : Float) : Int{
        if (faltas > MAX_FALTAS) return R.string.reprovado_faltas
        else if (notaFinal < NOTA_PARA_APROVACAO) return R.string.reprovado
        else return R.string.aprovado
    }

    fun Float.format(digits: Int) = "%.${digits}f".format(this)
}
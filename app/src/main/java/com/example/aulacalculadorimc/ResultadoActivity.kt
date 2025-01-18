package com.example.aulacalculadorimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aulacalculadorimc.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate do layout via View Binding
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera os valores passados pela MainActivity
        // Se não existirem, retorna 0.0 como padrão
        val peso = intent.getDoubleExtra("peso", 0.0)
        val altura = intent.getDoubleExtra("altura", 0.0)

        // Validação dos valores
        if (peso <= 0.0 || altura <= 0.0) {
            // Exibe um Toast para avisar o usuário
            Toast.makeText(this, "Dados inválidos ou não recebidos!", Toast.LENGTH_LONG).show()

            // Exibe mensagens de erro no layout
            binding.textPeso.text = "Peso inválido!"
            binding.textAltura.text = "Altura inválida!"
            binding.textResultado.text = "Não foi possível calcular o IMC."

            // Retorna para evitar continuar o cálculo
            return
        }

        // Exibe os valores recebidos nos TextViews
        binding.textPeso.text = "Peso informado: $peso kg"
        binding.textAltura.text = "Altura informada: $altura m"

        // Cálculo do IMC
        val imc = peso / (altura * altura)

        // Categorização do IMC (exemplo simples)
        val resultado = when {
            imc < 18.5  -> "Baixo"
            imc < 25.0  -> "Normal"
            imc < 30.0  -> "Sobrepeso"
            else        -> "Obeso"
        }

        // Exibe o resultado
        binding.textResultado.text = "Resultado: $resultado"
    }
}
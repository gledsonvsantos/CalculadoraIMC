package com.example.aulacalculadorimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aulacalculadorimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Usando View Binding para substituir findViewById
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflando o layout via View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o botão de calcular
        binding.btnCalcular.setOnClickListener {
            validarCamposEAvancar()
        }
    }

    private fun validarCamposEAvancar() {
        // Obtemos as strings diretamente dos EditText
        val pesoString = binding.editPeso.text.toString()
        val alturaString = binding.editAltura.text.toString()

        // Convertemos para Double usando toDoubleOrNull (evita NumberFormatException)
        val peso = pesoString.toDoubleOrNull()
        val altura = alturaString.toDoubleOrNull()

        // Verificação básica de preenchimento
        if (pesoString.isBlank()) {
            binding.editPeso.error = "Por favor, insira seu peso"
            return
        }
        if (alturaString.isBlank()) {
            binding.editAltura.error = "Por favor, insira sua altura"
            return
        }

        // Verificamos se são valores numéricos válidos
        if (peso == null || peso <= 0.0) {
            binding.editPeso.error = "Insira um valor de peso válido (maior que zero)"
            return
        }
        if (altura == null || altura <= 0.0) {
            binding.editAltura.error = "Insira um valor de altura válido (maior que zero)"
            return
        }

        // Se chegamos até aqui, os valores são válidos
        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra("peso", peso)
            putExtra("altura", altura)
        }

        startActivity(intent)
    }
}
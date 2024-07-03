package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDAO
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity(R.layout.activity_form_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.salvar)
        val dao = ProdutosDAO()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criarProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criarProduto(): Produto{
        val campoNome = findViewById<EditText>(R.id.activity_form_nome)
        val nome = campoNome.text.toString()

        val campoDesc = findViewById<EditText>(R.id.activity_form_descricao)
        val descricao = campoDesc.text.toString()

        val campoValor = findViewById<EditText>(R.id.activity_form_valor)
        val valorText = campoValor.text.toString()
        val valor = if (valorText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorText)
        }
        return Produto(nome,descricao,valor)
    }
}
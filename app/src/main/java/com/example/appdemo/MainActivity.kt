package com.example.appdemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var editTextNombre: EditText
    lateinit var editTextApellido: EditText
    lateinit var btnSalida: Button
    lateinit var btnClear: Button
    lateinit var textViewSaludo: TextView
    lateinit var btnBtn1 : Button
    lateinit var btnBtn2 : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextNombre = findViewById(R.id.nombre)
        editTextApellido = findViewById(R.id.apellido)
        btnSalida = findViewById(R.id.salida)
        btnClear = findViewById(R.id.limpiar)
        textViewSaludo = findViewById(R.id.saludo)
        btnBtn1 = findViewById(R.id.btn1)
        btnBtn2 = findViewById(R.id.btn2)

        btnSalida.isEnabled = false
        btnClear.isEnabled = false
        btnBtn1.isEnabled = btnBtn1.text == "On"
        btnBtn2.isEnabled = btnBtn2.text == "On"


        fun verificarCampos() {
            val nombre = editTextNombre.text.toString()
            val apellido = editTextApellido.text.toString()
            btnSalida.isEnabled = nombre.isNotEmpty() || apellido.isNotEmpty()
        }

        fun verificarSaludo(){
            val saludo = textViewSaludo.text.toString()
            btnClear.isEnabled = saludo.isNotEmpty()
        }

        fun cambiarTextBtn(btnBtn1:Button , btnBtn2:Button){

            val btnSave = btnBtn1.text
            this.btnBtn1.setText(btnBtn2.text)
            this.btnBtn2.setText(btnSave)

            val isBtn1Enabled = btnBtn1.isEnabled
            btnBtn1.isEnabled = btnBtn2.isEnabled
            btnBtn2.isEnabled = isBtn1Enabled

        }











        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verificarCampos()
            }

            override fun afterTextChanged(s: Editable?) {
                verificarSaludo()
            }
        }


        editTextNombre.addTextChangedListener(textWatcher)
        editTextApellido.addTextChangedListener(textWatcher)
        textViewSaludo.addTextChangedListener(textWatcher)

        btnBtn1.setOnClickListener(){
            cambiarTextBtn(btnBtn1, btnBtn2)
        }
        btnBtn2.setOnClickListener(){
            cambiarTextBtn(btnBtn1, btnBtn2)
        }

        btnSalida.setOnClickListener() {
            val nombre = editTextNombre.text.toString()
            val apellido = editTextApellido.text.toString()

            textViewSaludo.text = "Hola, $nombre $apellido"
        }

        // Acción del botón "Limpiar"
        btnClear.setOnClickListener {
            editTextNombre.text.clear()
            editTextApellido.text.clear()
            textViewSaludo.text = ""
        }
    }


}

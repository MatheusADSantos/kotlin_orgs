package MatheusADSantos.com.github.orgs

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem Vindo(a) ao Orgs!", Toast.LENGTH_LONG).show()

        val view = TextView(this)
        view.setText("Cesta de Frustas")
        setContentView(view)
    }

}
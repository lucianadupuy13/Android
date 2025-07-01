package com.example.viewsmodelparaproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.auth.LoginActivity
import com.example.viewsmodelparaproyecto.auth.RegisterActivity
import com.example.viewsmodelparaproyecto.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    /* ╔═ Simulación de sesión ═╗
       Cámbialo por tu propio sistema de autenticación
       (SharedPreferences, ViewModel, etc.) */
    private var loggedIn = false        // ← cámbialo a true tras login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*──────── Toolbar + Drawer ────────*/
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        /*──────── Botones de bienvenida ────────*/
        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        /*──────── Configurar menú lateral ────────*/
        setupNavigationDrawer(binding.navView)
    }

    /** Muestra/oculta ítems y define acciones del NavigationView */
    private fun setupNavigationDrawer(nav: NavigationView) {

        // 1) Cambiar visibilidad según sesión
        nav.menu.findGroup(R.id.group_logged_in).isVisible = loggedIn
        nav.menu.findItem(R.id.nav_login).isVisible = !loggedIn
        nav.menu.findItem(R.id.nav_register).isVisible = !loggedIn

        // 2) Cambiar saludo en header
        val header = nav.getHeaderView(0)
        header.findViewById<TextView>(R.id.tvDrawerUser).text =
            if (loggedIn) "Hola, Emilio" else "Hola, Invitado"

        // 3) Gestionar clics
        nav.setNavigationItemSelectedListener { item ->
            binding.drawerLayout.closeDrawers()
            when (item.itemId) {
                R.id.nav_inicio -> {
                    // Ejemplo: podrías mostrar un fragmento Home
                }
                R.id.nav_login -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_register -> startActivity(Intent(this, RegisterActivity::class.java))
                R.id.nav_logout -> {
                    loggedIn = false        // <— limpia tu sesión real
                    recreate()              // refresca la actividad para ocultar/mostrar menús
                }
                // Añade aquí navegación a tus otras actividades:
                // R.id.nav_seleccionar_paciente -> …
            }
            true
        }
    }

    /* Sincroniza icono ☰ cuando giras pantalla */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }
}
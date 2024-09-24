package com.example.petmaps

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.petmaps.databinding.ActivityTelainicialBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Telainicial : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityTelainicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener(this)

        // Bottom Navigation
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle Home action
                    true
                }
                R.id.navigation_notifications -> {
                    // Handle Notifications action
                    true
                }

                R.id.navigation_ongs -> {
                    true
                }

                R.id.navigation_chat -> {
                    true
                }
                R.id.navigation_perfil -> {
                    true
                }
                else -> false
            }
        }

        // Botão para abrir o menu lateral
        val botao = findViewById<ImageView>(R.id.menu)
        botao.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_perfil -> {
                // Handle Perfil action
            }
            R.id.nav_ongs -> {
                // Handle Ongs action
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

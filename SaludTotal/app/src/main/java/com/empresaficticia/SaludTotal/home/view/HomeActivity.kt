package com.empresaficticia.SaludTotal.home.view
import android.os.Bundle
import com.empresaficticia.SaludTotal.R
import com.empresaficticia.SaludTotal.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Inicio"
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_home
    }
}


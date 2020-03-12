package com.chat.bposeats.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.chat.bposeats.R

import kotlinx.android.synthetic.main.activity_main.*

/**
 * Displays the main screen
 */
class MainActivity : AppCompatActivity(), MainContract.MView {

    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mPresenter = MainPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showSignInView() {
        Toast.makeText(this, "Sign in", Toast.LENGTH_LONG).show()
    }

    override fun showSignOutView() {
        Toast.makeText(this, "Sign out", Toast.LENGTH_LONG).show()
    }
}

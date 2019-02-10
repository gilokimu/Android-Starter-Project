package me.gilo.starter.ui.home

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import me.gilo.starter.R
import me.gilo.starter.common.BaseActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class HomeActivity : BaseActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var selectedFragment: Fragment? = HomeFragment.newInstance()

        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = HomeFragment.newInstance()

            }
            R.id.navigation_deals -> {
                selectedFragment = ActivityFragment.newInstance()
            }
            R.id.navigation_account -> {
                selectedFragment = ProfileFragment.newInstance()
            }
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, selectedFragment!!)
        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, HomeFragment.newInstance())
        transaction.commit()

    }
}

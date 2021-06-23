package com.lenatopoleva.movies.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.mvp.presenter.MainPresenter
import com.lenatopoleva.movies.mvp.view.MainView
import com.lenatopoleva.movies.ui.App
import com.lenatopoleva.movies.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)


    val presenter by moxyPresenter {
        App.instance.appComponent.inject(this)

        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }
}
package com.lenatopoleva.movies.ui.fragment

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.di.DaggerTestAppComponent
import com.lenatopoleva.movies.di.modules.NavigationModule
import com.lenatopoleva.movies.di.modules.testModules.TestImageLoaderModule
import com.lenatopoleva.movies.di.modules.testModules.TestMoviesScreenModule
import com.lenatopoleva.movies.ui.App
import com.lenatopoleva.movies.ui.adapter.MoviesRvAdapter
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesFragmentRVTest {

    private lateinit var scenario: FragmentScenario<MoviesFragment>

    @Before
    fun setup() {

        val app = InstrumentationRegistry.getTargetContext().applicationContext as App

        val testAppComponent =  DaggerTestAppComponent.builder()
            .testImageLoaderModule(TestImageLoaderModule())
            .testMoviesScreenModule(TestMoviesScreenModule())
            .navigationModule(NavigationModule())
            .build()

        app.appComponent = testAppComponent

        scenario = launchFragmentInContainer<MoviesFragment>()

        // Inject imageLoader mock
        scenario.onFragment { fragment -> testAppComponent.inject(fragment) }
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun moviesList_scrollsToConcreteElement() {
        onView(withId(R.id.movies_recyclerview))
            .perform(
                RecyclerViewActions.scrollTo<MoviesRvAdapter.ViewHolder>(
                    hasDescendant(withText("Luca #12"))
                )
            )
    }

    @Test
    fun moviesList_performActionAtPosition() {
        onView(withId(R.id.movies_recyclerview))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MoviesRvAdapter.ViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun moviesList_performCustomClick() {
        onView(withId(R.id.movies_recyclerview))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MoviesRvAdapter.ViewHolder>(
                    0,
                    tapOnItemWithId(R.id.iv_image)
                )
            )
    }

    @Test
    fun moviesList_performClickAtConcreteItem() {
        // Cкроллим чуть ниже нужного элемента, т.к. промотка останавливается, когда искомый элемент
        // появляется на экране (в самом низу)
        onView(withId(R.id.movies_recyclerview))
            .perform(
                RecyclerViewActions.scrollTo<MoviesRvAdapter.ViewHolder>(
                    hasDescendant(withText("Luca #13"))
                )
            )

        onView(withId(R.id.movies_recyclerview))
            .perform(
                RecyclerViewActions.actionOnItem<MoviesRvAdapter.ViewHolder>(
                    hasDescendant(withText("Luca #12")),
                    click()
                )
            )

    }


    private fun tapOnItemWithId(id: Int) = object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Нажимаем на view с указанным id"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById(id) as View
            v.performClick()
        }
    }

    @After
    fun close() {}
}
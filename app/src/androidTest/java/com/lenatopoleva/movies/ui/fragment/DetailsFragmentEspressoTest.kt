package com.lenatopoleva.movies.ui.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.entity.OriginalLanguage
import com.lenatopoleva.movies.mvp.presenter.DetailsPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import moxy.MvpFacade
import moxy.MvpPresenter
import moxy.PresenterStore
import org.junit.Before
import org.junit.Test


class DetailsFragmentEspressoTest{

    private lateinit var scenario: FragmentScenario<DetailsFragment>

    private val testMovie = Movie(false,"/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
        listOf(16,35,10751,14),508943, OriginalLanguage.En,"Luca",
        "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. " +
                "But all the fun is threatened by a deeply-held secret: they are sea monsters from another world " +
                "just below the water’s surface.",
        5135.463,"/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg","2021-06-17",
        "Luca",false,8.3,969)

    private val presenter = mockk<DetailsPresenter>()
    val fragmentArgs = bundleOf("movie" to testMovie)



    @Before
    fun setup(){
        // Попытка заменить презентер пустышкой
//        MvpFacade.getInstance().presenterStore = object : PresenterStore() {
//            override fun get(tag: String?): MvpPresenter<*> {
//                return presenter
//            }
//        }
    }


    @Test
    fun fragment_testBundle(){
        scenario = launchFragmentInContainer<DetailsFragment>(fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)
        val assertion = matches(withText("Luca"))
        onView(withId(R.id.tv_name)).check(assertion)
    }

    @Test
    // Здесь надо замокировать презентер! - FAILED with exception
    fun funBackPressed_invokePresenterBackClickedMethod(){
        scenario = launchFragmentInContainer<DetailsFragment>(fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)
        every { presenter.backClick() } returns true

        scenario.onFragment { fragment -> fragment.backPressed() }
        verify(exactly = 1) { presenter.backClick()}
    }
}
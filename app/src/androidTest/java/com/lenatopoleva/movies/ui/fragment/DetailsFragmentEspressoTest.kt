package com.lenatopoleva.movies.ui.fragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.lifecycle.Lifecycle
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.di.DaggerTestAppComponent
import com.lenatopoleva.movies.di.modules.testModules.TestImageLoaderModule
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.entity.OriginalLanguage
import com.lenatopoleva.movies.mvp.presenter.DetailsPresenter
import com.lenatopoleva.movies.ui.App
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.fragment_details.*
import org.junit.Before
import org.junit.Test


// Наследуемся от DetailsFragment, чтобы переопределить метод provide(), предоставляющий презентер,
// чтобы заменить moxy presenter пустышкой
class DetailsFragmentEspressoTest: DetailsFragment() {

    private lateinit var scenario: FragmentScenario<DetailsFragmentEspressoTest>

    private val testMovie = Movie(false,"/uAQrHntCccFpvxp75XdQgqexlJd.jpg",
        listOf(16,35,10751,14),508943, OriginalLanguage.En,"Luca",
        "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. " +
                "But all the fun is threatened by a deeply-held secret: they are sea monsters from another world " +
                "just below the water’s surface.",
        5135.463,"/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg","2021-06-17",
        "Luca",false,8.3,969)

    private val testFragmentArgs = bundleOf("movie" to testMovie)

    private val testPresenter = mockk<DetailsPresenter>(relaxed = true)

    // Подменяем презентер на мок
    override fun provide(): DetailsPresenter = testPresenter

    @Before
    fun setup(){
        val app = InstrumentationRegistry.getTargetContext().applicationContext as App
        // Создаем тестовый компонент dagger (в котором создается мок imageLoader)
        val testAppComponent =  DaggerTestAppComponent.builder()
            .testImageLoaderModule(TestImageLoaderModule())
            .build()
        app.appComponent = testAppComponent

       // Запускаем фрагмент
        scenario = launchFragmentInContainer<DetailsFragmentEspressoTest>(testFragmentArgs)

        // Inject imageLoader mock
        scenario.onFragment { fragment -> testAppComponent.inject(fragment) }

        // Приводим фрагмент к состоянию RESUMED
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @Test
    fun fragment_testBundle(){
        scenario.withFragment {
            val movie = arguments?.getParcelable<Movie>("movie") as Movie
            setTitle(movie.originalTitle)
        }
        val assertion = matches(withText("Luca"))
        onView(withId(R.id.tv_name)).check(assertion)
    }

    @Test
    fun setTitle_changeTextViewTitleText(){
        scenario.onFragment { fragment -> fragment.setTitle("Film") }
        val assertion = matches(withText("Film"))
        onView(withId(R.id.tv_name)).check(assertion)
    }

    @Test
    fun setAbout_changeTextViewAboutText(){
        scenario.onFragment { fragment -> fragment.setAbout("New film") }
        val assertion = matches(withText("New film"))
        onView(withId(R.id.tv_about)).check(assertion)
    }

    @Test
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadImage_invokeImageLoaderMethod_loadWithRoundCornersInto(){
        val posterPath = "/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg"
        scenario.onFragment { fragment -> fragment.loadImage(posterPath) }
        scenario.withFragment {
            val imageContainer = iv_image
            verify(exactly = 1){imageLoader.loadWithRoundCornersInto(posterPath, imageContainer)}
        }
    }

    @Test
    fun funBackPressed_invokePresenterBackClickedMethod(){
        scenario.withFragment {
            backPressed()
            verify(exactly = 1) { presenter.backClick()}
        }
    }
}
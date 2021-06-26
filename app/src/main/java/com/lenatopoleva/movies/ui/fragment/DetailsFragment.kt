package com.lenatopoleva.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lenatopoleva.movies.R
import com.lenatopoleva.movies.mvp.model.entity.Movie
import com.lenatopoleva.movies.mvp.model.imageloader.IImageLoader
import com.lenatopoleva.movies.mvp.presenter.DetailsPresenter
import com.lenatopoleva.movies.mvp.view.DetailsView
import com.lenatopoleva.movies.ui.App
import com.lenatopoleva.movies.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

open class DetailsFragment: MvpAppCompatFragment(), DetailsView, BackButtonListener {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    open fun provide(): DetailsPresenter {
        val movie = arguments?.getParcelable<Movie>("movie") as Movie
        return DetailsPresenter(movie)
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    companion object {
        fun newInstance(movie: Movie) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("movie", movie)
            }
        }
    }

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_details, null)

    override fun setTitle (title: String) { tv_name.text = title }
    override fun setAbout(overview: String) { tv_about.text = overview }
    override fun loadImage(posterPath: String) = imageLoader.loadWithRoundCornersInto(posterPath, iv_image)
    override fun loadBackdropImage(backdropPath: String?) {
        imageLoader.loadInto(backdropPath, iv_backdrop)
    }

    override fun backPressed() = presenter.backClick()

}
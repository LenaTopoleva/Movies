package com.lenatopoleva.movies.mvp.presenter.list

import android.view.View
import com.lenatopoleva.movies.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
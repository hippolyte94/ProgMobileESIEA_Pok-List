package com.example.pokemonlistapp.Common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val itemOffSet:Int): RecyclerView.ItemDecoration() {
    constructor(context: Context,@DimenRes itemDimensId:Int):this(context.resources.getDimensionPixelSize(itemDimensId))

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(itemOffSet,itemOffSet,itemOffSet,itemOffSet)
    }
}
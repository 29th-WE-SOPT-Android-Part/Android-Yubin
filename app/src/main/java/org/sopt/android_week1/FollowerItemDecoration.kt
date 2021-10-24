package org.sopt.android_week1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FollowerItemDecoration (
    //그릴 divider의 높이와 색상을 받음
    private val dividerHeight: Int,
    private val dividerColor: Int
): RecyclerView.ItemDecoration() {
    private val paint = Paint()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        myDivider(c,parent,dividerColor)
    }

    private fun myDivider(c: Canvas, parent:RecyclerView, color:Int) {
        paint.color = color
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val param = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + param.bottomMargin
            val dividerBottom = dividerTop + dividerHeight

            c.drawRect(
                child.left.toFloat(),
                dividerTop.toFloat(),
                child.right.toFloat(),
                dividerBottom.toFloat(),
                paint
            )
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = dividerHeight
    }
}
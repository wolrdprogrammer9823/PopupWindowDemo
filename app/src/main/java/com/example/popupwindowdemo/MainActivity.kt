package com.example.popupwindowdemo
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popupwindow.view.*

class MainActivity : AppCompatActivity() {

    private var from = LEFT
    private var popupWindow: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onContentChanged() {

        super.onContentChanged()

        pop_left.setOnClickListener(onClickListener)
        pop_right.setOnClickListener(onClickListener)
        pop_bottom.setOnClickListener(onClickListener)
    }

    private fun initPopupWindow() {

        val rootView = layoutInflater.inflate(R.layout.activity_main, null)
        val popupWindowView = layoutInflater.inflate(R.layout.popupwindow, null)

        popupWindow = if (BOTTOM == from) {
            PopupWindow(
                popupWindowView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
        } else {
            PopupWindow(
                popupWindowView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                true
            )
        }

        when (from) {
            LEFT -> {
                popupWindow?.animationStyle = R.style.AnimationLeftFade
            }
            RIGHT -> {
                popupWindow?.animationStyle = R.style.AnimationRightFade
            }
            BOTTOM -> {
                popupWindow?.animationStyle = R.style.AnimationBottomFade
            }
            else -> {}
        }

        val colorDrawable = ColorDrawable(0xffffff)
        popupWindow?.setBackgroundDrawable(colorDrawable)
        popupWindow?.isOutsideTouchable = false

        when (from) {
            LEFT -> {
                popupWindow?.showAtLocation(rootView, Gravity.LEFT, 0, 500)
            }
            RIGHT -> {
                popupWindow?.showAtLocation(rootView, Gravity.RIGHT, 0, 500)
            }
            BOTTOM -> {
                popupWindow?.showAtLocation(
                    rootView,
                    Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL,
                    0,
                    0
                )
            }
            else -> {}
        }

        backgroundAlpha(0.5f)

        popupWindow?.setOnDismissListener {

            backgroundAlpha(1f)
        }

        popupWindowView.btn_open.setOnClickListener(onClickListener)
        popupWindowView.btn_close.setOnClickListener(onClickListener)
        popupWindowView.btn_save.setOnClickListener(onClickListener)

        //popupWindowView.setOnTouchListener { _, _ -> true }
    }

    private fun backgroundAlpha(alpha: Float) {
        val layoutParams: WindowManager.LayoutParams = window.attributes
        layoutParams.alpha = alpha
        window.attributes = layoutParams
    }

    private val onClickListener = View.OnClickListener{
        when (it.id) {
            R.id.pop_left -> {
                from = LEFT
                initPopupWindow()
            }
            R.id.pop_right -> {
                from = RIGHT
                initPopupWindow()
            }
            R.id.pop_bottom -> {
                from = BOTTOM
                initPopupWindow()
            }
            R.id.btn_open -> {
                popupWindow?.dismiss()
            }
            R.id.btn_close -> {
                popupWindow?.dismiss()
            }
            R.id.btn_save -> {
                popupWindow?.dismiss()
            }
            else -> {}
        }
    }

    companion object {
        private const val LEFT = 0x01
        private const val RIGHT = 0x02
        private const val BOTTOM = 0x03
    }

}
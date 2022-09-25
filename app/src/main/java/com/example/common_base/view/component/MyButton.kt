package com.example.common_base.view.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.common_base.R


class MyButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        setRadius(context, null) // radius
        getTextColorState(context, null) // state에 따라 칼라 변경

        minHeight = 0 // 버튼에 기본적으로 들어가있는 패딩 제거
        minimumHeight = 0 // 버튼에 기본적으로 들어가있는 패딩 제거

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setRadius(context, attrs) // radius
        getTextColorState(context, attrs) // state에 따라 칼라 변경

        minHeight = 0 // 버튼에 기본적으로 들어가있는 패딩 제거
        minimumHeight = 0 // 버튼에 기본적으로 들어가있는 패딩 제거
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setRadius(context, attrs) // radius
        getTextColorState(context, attrs) // state에 따라 칼라 변경

        minHeight = 0 // 버튼에 기본적으로 들어가있는 패딩 제거
        minimumHeight = 0 // 버튼에 기본적으로 들어가있는 패딩 제거
    }

    private fun setRadius(context: Context, attrs: AttributeSet?) {
        try {
            if (attrs != null) {
                var borderWidth =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getDimension(R.styleable.MyButton_borderWidth, 0F)
                var borderColor =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(R.styleable.MyButton_borderColor, -1)

                val shape = GradientDrawable()

                shape.shape = GradientDrawable.RECTANGLE
                shape.cornerRadius =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getDimension(R.styleable.MyButton_radius, 0f) // radius 추가

                // 기본 border color로 지정한 값이 있으면 그 값으로 셋팅
                if (borderColor != -1) shape.setStroke(borderWidth.toInt(), borderColor)
                // enable/disable border color로 지정한 값이 있으면 Color state list로 셋팅
                else shape.setStroke(borderWidth.toInt(), getBorderColorStateList(context, attrs))

                shape.color = getBackgroundColorStateList(context, attrs)

                var rippleColor =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(R.styleable.MyButton_rippleColor, 0) // ripple color 추가
                val rippleColorState =
                    ColorStateList.valueOf(rippleColor)

                this.background = RippleDrawable(
                    rippleColorState,
                    shape, null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getBackgroundColorStateList(
        context: Context,
        attrs: AttributeSet?
    ): ColorStateList {
        try {
            var enabledBackground = 0
            var disabledBackground = 0
            if (attrs != null) {
                disabledBackground =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(R.styleable.MyButton_disabledBackground, 0)
                enabledBackground =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(
                            R.styleable.MyButton_enabledBackground,
                            disabledBackground
                        )

            }

            val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf()
            )

            val colors = intArrayOf(
                enabledBackground,
                disabledBackground,
                disabledBackground
            )

            return ColorStateList(states, colors)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ColorStateList(null, null)
    }

    private fun getBorderColorStateList(context: Context, attrs: AttributeSet?): ColorStateList {
        try {
            var enabledBorderColor = 0
            var disabledBorderColor = 0
            if (attrs != null) {
                enabledBorderColor =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(R.styleable.MyButton_enabledBorderColor, 0)
                disabledBorderColor =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(R.styleable.MyButton_disabledBorderColor, 0)

            }

            val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf()
            )

            val colors = intArrayOf(
                enabledBorderColor,
                disabledBorderColor,
                disabledBorderColor
            )

            return ColorStateList(states, colors)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ColorStateList(null, null)
    }

    private fun getTextColorState(context: Context, attrs: AttributeSet?) {
        try {
            var enabledTextColor = 0
            var disabledTextColor = 0
            if (attrs != null) {
                enabledTextColor =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(
                            R.styleable.MyButton_enabledTextColor,
                            this.currentTextColor
                        )
                disabledTextColor =
                    context.obtainStyledAttributes(attrs, R.styleable.MyButton)
                        .getInt(
                            R.styleable.MyButton_disabledTextColor,
                            this.currentTextColor
                        )
            }

            val states = arrayOf(
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf()
            )
            val colors = intArrayOf(
                enabledTextColor,
                enabledTextColor,
                disabledTextColor
            )
            val list = ColorStateList(states, colors)
            this.setTextColor(list)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
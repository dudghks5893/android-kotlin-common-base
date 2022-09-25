package com.example.common_base.loading.internal

import android.content.Context
import android.os.Handler
import android.util.TypedValue
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toTimeUnit

@OptIn(ExperimentalTime::class)
internal fun runAfter(period: Long, unit: DurationUnit, code: () -> Unit): Boolean =
    Handler().postDelayed(code, unit.toTimeUnit().toMillis(period))

internal fun Context.dpToPixel(dimenDp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dimenDp.toFloat(),
    resources.displayMetrics
).toInt()
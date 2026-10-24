package com.gikoko.icon

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.Gravity
import android.widget.TextView

object Ui {
    const val GOLD_1 = 0xFFFFE9A3.toInt()
    const val GOLD_2 = 0xFFF3C14B.toInt()
    const val GOLD_3 = 0xFFD4A017.toInt()
    const val GOLD_4 = 0xFF8A5A00.toInt()
    const val CYAN = 0xFF35E0FF.toInt()
    const val INK = 0xFF0A0812.toInt()
    const val INK_2 = 0xFF1B1430.toInt()
    const val BG_CARD = 0xFF120E22.toInt()

    fun dp(ctx: Context, v: Int): Float {
        return v * ctx.resources.displayMetrics.density
    }

    fun dp(ctx: Context, v: Float): Float {
        return v * ctx.resources.displayMetrics.density
    }

    fun pref(ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences("icon_prefs", Context.MODE_PRIVATE)
    }

    fun goldButton(ctx: Context, text: String): TextView {
        val b = TextView(ctx)
        b.text = text
        b.gravity = Gravity.CENTER
        b.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
        b.setTextColor(INK)
        b.textSize = 13f
        b.letterSpacing = 0.12f
        val base = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(GOLD_1, GOLD_3))
        base.cornerRadius = dp(ctx, 22f)
        b.background = RippleDrawable(ColorStateList.valueOf(0x33FFFFFF), base, null)
        b.setPadding(dp(ctx, 26).toInt(), dp(ctx, 10).toInt(), dp(ctx, 26).toInt(), dp(ctx, 10).toInt())
        return b
    }

    fun ghostButton(ctx: Context, text: String, active: Boolean): TextView {
        val b = TextView(ctx)
        b.text = text
        b.gravity = Gravity.CENTER
        b.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
        b.textSize = 13f
        b.letterSpacing = 0.1f
        val base = GradientDrawable()
        base.cornerRadius = dp(ctx, 22f)
        base.setStroke(dp(ctx, if (active) 2 else 1).toInt(), if (active) GOLD_1 else 0x80D4A017.toInt())
        base.setColor(if (active) 0x33F3C14B.toInt() else 0x22120E22.toInt())
        b.setTextColor(if (active) GOLD_1 else 0xB3F3C14B.toInt())
        b.background = RippleDrawable(ColorStateList.valueOf(0x33F3C14B), base, null)
        b.setPadding(dp(ctx, 22).toInt(), dp(ctx, 10).toInt(), dp(ctx, 22).toInt(), dp(ctx, 10).toInt())
        return b
    }
}

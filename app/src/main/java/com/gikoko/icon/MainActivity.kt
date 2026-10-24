package com.gikoko.icon

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private var style = 0
    private val apps = ArrayList<AppInfo>()
    private lateinit var grid: GridLayout
    private lateinit var styleBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        style = Ui.pref(this).getInt("icon_style", 0)

        val pad = Ui.dp(this, 18).toInt()
        val root = FrameLayout(this)
        root.addView(CyberBgView(this), FrameLayout.LayoutParams(-1, -1))

        val scroll = ScrollView(this)
        scroll.isVerticalScrollBarEnabled = false
        val col = LinearLayout(this)
        col.orientation = LinearLayout.VERTICAL
        col.setPadding(pad, pad, pad, pad)

        val title = GoldTextView(this)
        title.text = "GIKOKO ICON"
        title.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
        title.textSize = 36f
        title.letterSpacing = 0.16f
        title.gravity = Gravity.CENTER
        col.addView(title, LinearLayout.LayoutParams(-2, -2).apply { gravity = Gravity.CENTER_HORIZONTAL })

        val sub = TextView(this)
        sub.text = "GOLD KANOT FRAMES FOR YOUR APP ICONS"
        sub.gravity = Gravity.CENTER
        sub.textSize = 11f
        sub.letterSpacing = 0.25f
        sub.setTextColor(0x9935E0FF.toInt())
        val subLp = LinearLayout.LayoutParams(-2, -2)
        subLp.gravity = Gravity.CENTER_HORIZONTAL
        subLp.topMargin = Ui.dp(this, 4).toInt()
        col.addView(sub, subLp)

        styleBtn = Ui.goldButton(this, styleName())
        styleBtn.setOnClickListener { toggleStyle() }
        val styleWrap = LinearLayout(this)
        styleWrap.orientation = LinearLayout.VERTICAL
        styleWrap.gravity = Gravity.CENTER_HORIZONTAL
        styleWrap.addView(styleBtn, LinearLayout.LayoutParams(-2, -2).apply { gravity = Gravity.CENTER_HORIZONTAL })
        val swLp = LinearLayout.LayoutParams(-2, -2)
        swLp.gravity = Gravity.CENTER_HORIZONTAL
        swLp.topMargin = Ui.dp(this, 18).toInt()
        col.addView(styleWrap, swLp)

        val hint = TextView(this)
        hint.text = "TAP AN APP - PIN ITS FRAMED ICON TO THE HOME SCREEN"
        hint.gravity = Gravity.CENTER
        hint.textSize = 11f
        hint.setTextColor(0x8CD4A017.toInt())
        val hintLp = LinearLayout.LayoutParams(-2, -2)
        hintLp.gravity = Gravity.CENTER_HORIZONTAL
        hintLp.topMargin = Ui.dp(this, 14).toInt()
        col.addView(hint, hintLp)

        grid = GridLayout(this)
        grid.columnCount = 4
        val gridLp = LinearLayout.LayoutParams(-1, -2)
        gridLp.topMargin = Ui.dp(this, 10).toInt()
        col.addView(grid, gridLp)

        scroll.addView(col, LinearLayout.LayoutParams(-1, -2))
        root.addView(scroll, FrameLayout.LayoutParams(-1, -1))
        setContentView(root)

        loadApps()
        fillGrid()
    }

    private fun styleName(): String {
        return if (style == 0) "STYLE: ORIGINAL ICON" else "STYLE: KANOT ART"
    }

    private fun toggleStyle() {
        style = if (style == 0) 1 else 0
        Ui.pref(this).edit().putInt("icon_style", style).apply()
        styleBtn.text = styleName()
        fillGrid()
    }

    private fun loadApps() {
        apps.clear()
        val pm = packageManager
        val main = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val list: List<ResolveInfo> = pm.queryIntentActivities(main, 0)
        for (ri in list) {
            val pkg = ri.activityInfo.packageName
            val launch = pm.getLaunchIntentForPackage(pkg)
            if (launch == null) continue
            val label: String = try {
                ri.loadLabel(pm).toString()
            } catch (e: Exception) {
                pkg
            }
            val icon: Drawable? = try {
                pm.getApplicationIcon(pkg)
            } catch (e: Exception) {
                null
            }
            apps.add(AppInfo(label, pkg, icon, launch))
        }
        apps.sortBy { it.label.lowercase() }
    }

    private fun fillGrid() {
        grid.removeAllViews()
        val w = resources.displayMetrics.widthPixels
        val gap = Ui.dp(this, 12).toInt()
        val cell = (w - Ui.dp(this, 36).toInt() - gap * 3) / 4
        for (a in apps) {
            val cellBox = LinearLayout(this)
            cellBox.orientation = LinearLayout.VERTICAL
            cellBox.gravity = Gravity.CENTER_HORIZONTAL
            val prev = IconPreview(this)
            prev.app = a
            prev.style = style
            cellBox.addView(prev, LinearLayout.LayoutParams(cell, cell))
            val name = TextView(this)
            name.text = a.label
            name.textSize = 10f
            name.maxLines = 1
            name.gravity = Gravity.CENTER
            name.setTextColor(0xFFE2E8F0.toInt())
            val nameLp = LinearLayout.LayoutParams(cell, -2)
            nameLp.topMargin = Ui.dp(this, 4).toInt()
            cellBox.addView(name, nameLp)
            cellBox.setOnClickListener { openDialog(a) }
            val lp = GridLayout.LayoutParams()
            lp.width = cell
            lp.height = -2
            lp.setMargins(gap / 2, gap / 2, gap / 2, gap / 2)
            grid.addView(cellBox, lp)
        }
    }

    private fun radioLook(b: TextView, active: Boolean) {
        val base = GradientDrawable()
        base.cornerRadius = Ui.dp(this, 22f)
        base.setStroke(Ui.dp(this, if (active) 2 else 1).toInt(), if (active) Ui.GOLD_1 else 0x80D4A017.toInt())
        base.setColor(if (active) 0x33F3C14B.toInt() else 0x22120E22.toInt())
        b.background = base
        b.setTextColor(if (active) Ui.GOLD_1 else 0xB3F3C14B.toInt())
    }

    private fun openDialog(app: AppInfo) {
        val panel = LinearLayout(this)
        panel.orientation = LinearLayout.VERTICAL
        panel.setBackgroundColor(Ui.INK)
        val pad = Ui.dp(this, 24).toInt()
        panel.setPadding(pad, pad, pad, pad)

        val prev = IconPreview(this)
        prev.app = app
        prev.style = style
        val ps = Ui.dp(this, 190).toInt()
        val prevLp = LinearLayout.LayoutParams(ps, ps)
        prevLp.gravity = Gravity.CENTER_HORIZONTAL
        panel.addView(prev, prevLp)

        val name = TextView(this)
        name.text = app.label
        name.gravity = Gravity.CENTER
        name.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
        name.textSize = 18f
        name.setTextColor(Ui.GOLD_1)
        val nameLp = LinearLayout.LayoutParams(-2, -2)
        nameLp.gravity = Gravity.CENTER_HORIZONTAL
        nameLp.topMargin = Ui.dp(this, 16).toInt()
        panel.addView(name, nameLp)

        val pkg = TextView(this)
        pkg.text = app.pkg
        pkg.gravity = Gravity.CENTER
        pkg.textSize = 10f
        pkg.setTextColor(0x66FFFFFF.toInt())
        val pkgLp = LinearLayout.LayoutParams(-2, -2)
        pkgLp.gravity = Gravity.CENTER_HORIZONTAL
        panel.addView(pkg, pkgLp)

        val row = LinearLayout(this)
        row.orientation = LinearLayout.HORIZONTAL
        row.gravity = Gravity.CENTER
        val bOriginal = Ui.ghostButton(this, "ORIGINAL", style == 0)
        val bKanot = Ui.ghostButton(this, "KANOT ART", style == 1)
        bOriginal.setOnClickListener {
            style = 0
            Ui.pref(this).edit().putInt("icon_style", 0).apply()
            radioLook(bOriginal, true)
            radioLook(bKanot, false)
            prev.style = 0
            prev.invalidate()
            styleBtn.text = styleName()
        }
        bKanot.setOnClickListener {
            style = 1
            Ui.pref(this).edit().putInt("icon_style", 1).apply()
            radioLook(bOriginal, false)
            radioLook(bKanot, true)
            prev.style = 1
            prev.invalidate()
            styleBtn.text = styleName()
        }
        row.addView(bOriginal, LinearLayout.LayoutParams(-2, -2).apply { rightMargin = Ui.dp(this@MainActivity, 10).toInt() })
        row.addView(bKanot, LinearLayout.LayoutParams(-2, -2))
        val rowLp = LinearLayout.LayoutParams(-2, -2)
        rowLp.gravity = Gravity.CENTER_HORIZONTAL
        rowLp.topMargin = Ui.dp(this, 16).toInt()
        panel.addView(row, rowLp)

        val pin = Ui.goldButton(this, "PIN TO HOME SCREEN")
        pin.setOnClickListener { doPin(app) }
        val pinLp = LinearLayout.LayoutParams(-1, -2)
        pinLp.topMargin = Ui.dp(this, 18).toInt()
        panel.addView(pin, pinLp)

        val close = TextView(this)
        close.text = "CLOSE"
        close.gravity = Gravity.CENTER
        close.textSize = 13f
        close.setTextColor(0x80FFFFFF.toInt())
        close.setPadding(0, Ui.dp(this, 12).toInt(), 0, Ui.dp(this, 2).toInt())
        val closeLp = LinearLayout.LayoutParams(-2, -2)
        closeLp.gravity = Gravity.CENTER_HORIZONTAL
        panel.addView(close, closeLp)

        val dlg = AlertDialog.Builder(this).create()
        close.setOnClickListener { dlg.dismiss() }
        dlg.setView(panel)
        dlg.show()
    }

    private fun doPin(app: AppInfo) {
        val bm = IconMaker.build(app, style, 432)
        val ok = ShortcutUtil.requestPin(this, app, bm)
        if (ok) {
            Toast.makeText(this, "SHORTCUT SENT - CHECK YOUR HOME SCREEN", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "THIS LAUNCHER DOES NOT SUPPORT PINNING", Toast.LENGTH_LONG).show()
        }
    }
}

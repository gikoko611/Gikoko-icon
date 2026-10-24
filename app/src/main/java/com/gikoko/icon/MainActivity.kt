package com.gikoko.icon

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val tLp = LinearLayout.LayoutParams(-2, -2)
        tLp.gravity = Gravity.CENTER_HORIZONTAL
        col.addView(title, tLp)

        val sub = TextView(this)
        sub.text = "GOLD KANOT ICON PACK - NO SHORTCUTS - NO DUPLICATES"
        sub.gravity = Gravity.CENTER
        sub.textSize = 11f
        sub.letterSpacing = 0.2f
        sub.setTextColor(0x9935E0FF.toInt())
        val sLp = LinearLayout.LayoutParams(-2, -2)
        sLp.gravity = Gravity.CENTER_HORIZONTAL
        sLp.topMargin = Ui.dp(this, 4).toInt()
        col.addView(sub, sLp)

        val apply = Ui.goldButton(this, "APPLY ICON PACK")
        apply.setOnClickListener { applyPack() }
        val aLp = LinearLayout.LayoutParams(-2, -2)
        aLp.gravity = Gravity.CENTER_HORIZONTAL
        aLp.topMargin = Ui.dp(this, 18).toInt()
        col.addView(apply, aLp)

        val status = TextView(this)
        status.gravity = Gravity.CENTER
        status.textSize = 11f
        status.setTextColor(0x8CD4A017.toInt())
        status.text = themedCount() + " OF YOUR APPS ARE THEMED - ONE TAP IN LAUNCHER SETTINGS CHANGES ALL ICONS AT ONCE"
        val stLp = LinearLayout.LayoutParams(-2, -2)
        stLp.gravity = Gravity.CENTER_HORIZONTAL
        stLp.topMargin = Ui.dp(this, 12).toInt()
        col.addView(status, stLp)

        val grid = GridLayout(this)
        grid.columnCount = 4
        val gLp = LinearLayout.LayoutParams(-1, -2)
        gLp.topMargin = Ui.dp(this, 14).toInt()
        col.addView(grid, gLp)

        val w = resources.displayMetrics.widthPixels
        val gap = Ui.dp(this, 12).toInt()
        val cell = (w - pad * 2 - gap * 3) / 4
        for (item in PackIcons.items) {
            val box = LinearLayout(this)
            box.orientation = LinearLayout.VERTICAL
            box.gravity = Gravity.CENTER_HORIZONTAL
            val iv = ImageView(this)
            iv.setImageResource(item.icon)
            iv.scaleType = ImageView.ScaleType.FIT_CENTER
            box.addView(iv, LinearLayout.LayoutParams(cell, cell))
            val name = TextView(this)
            name.text = item.label
            name.textSize = 10f
            name.maxLines = 1
            name.gravity = Gravity.CENTER
            name.setTextColor(0xFFE2E8F0.toInt())
            val nLp = LinearLayout.LayoutParams(cell, -2)
            nLp.topMargin = Ui.dp(this, 4).toInt()
            box.addView(name, nLp)
            val lp = GridLayout.LayoutParams()
            lp.width = cell
            lp.height = -2
            lp.setMargins(gap / 2, gap / 2, gap / 2, gap / 2)
            grid.addView(box, lp)
        }

        scroll.addView(col, LinearLayout.LayoutParams(-1, -2))
        root.addView(scroll, FrameLayout.LayoutParams(-1, -1))
        setContentView(root)
    }

    private fun themedCount(): String {
        return try {
            val pm = packageManager
            val main = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
            val list: List<ResolveInfo> = pm.queryIntentActivities(main, 0)
            val seen = HashSet<String>()
            for (ri in list) seen.add(ri.activityInfo.packageName)
            var n = 0
            for (item in PackIcons.items) {
                var found = false
                for (pkg in item.packs) {
                    if (seen.contains(pkg)) {
                        found = true
                        break
                    }
                }
                if (found) n++
            }
            n.toString()
        } catch (e: Exception) {
            "0"
        }
    }

    private fun applyPack() {
        val apex = Intent("com.anddoes.launcher.SET_THEME")
        apex.putExtra("theme", packageName)
        if (tryStart(apex)) return
        showInstructions()
    }

    private fun tryStart(i: Intent): Boolean {
        return try {
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun showInstructions() {
        val panel = LinearLayout(this)
        panel.orientation = LinearLayout.VERTICAL
        panel.setBackgroundColor(Ui.INK)
        val pad = Ui.dp(this, 24).toInt()
        panel.setPadding(pad, pad, pad, pad)
        val lines = arrayOf(
            "ICON PACKS APPLY INSIDE YOUR LAUNCHER",
            "",
            "NOVA - LAWNCHAIR - NIAGARA - SMART:",
            "OPEN LAUNCHER SETTINGS, CHOOSE ICON PACK,",
            "PICK GIKOKO ICON - EVERY ICON CHANGES AT ONCE",
            "",
            "NO DUPLICATES - NO SHORTCUTS -",
            "THE ORIGINAL HOME ICONS ARE REPLACED DIRECTLY",
            "",
            "STOCK SAMSUNG AND PIXEL LAUNCHERS DO NOT",
            "ALLOW ICON PACKS - THAT IS AN ANDROID RULE"
        )
        for (s in lines) {
            val tv = TextView(this)
            tv.text = s
            tv.textSize = 12f
            tv.setTextColor(if (s.length == 0) Ui.INK else 0xFFE2E8F0.toInt())
            panel.addView(tv, LinearLayout.LayoutParams(-2, -2))
        }
        val ok = Ui.goldButton(this, "GOT IT")
        val oLp = LinearLayout.LayoutParams(-1, -2)
        oLp.topMargin = Ui.dp(this, 16).toInt()
        panel.addView(ok, oLp)
        val dlg = AlertDialog.Builder(this).create()
        ok.setOnClickListener { dlg.dismiss() }
        dlg.setView(panel)
        dlg.show()
    }
}

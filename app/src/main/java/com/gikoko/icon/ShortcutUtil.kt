package com.gikoko.icon

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.os.Build

object ShortcutUtil {

    fun requestPin(context: Context, app: AppInfo, bitmap: Bitmap): Boolean {
        val intent = Intent(app.launch)
        if (intent.component == null) return false
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return pinModern(context, app, intent, bitmap)
        }
        val add = Intent("com.android.launcher.action.INSTALL_SHORTCUT")
        add.putExtra(Intent.EXTRA_SHORTCUT_NAME, app.label)
        add.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent)
        add.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap)
        context.sendBroadcast(add)
        return true
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun pinModern(context: Context, app: AppInfo, intent: Intent, bitmap: Bitmap): Boolean {
        val sm = context.getSystemService(ShortcutManager::class.java)
        if (sm == null) return false
        if (!sm.isRequestPinShortcutSupported) return false
        val id = "gikoko_" + app.pkg.replace('.', '_') + "_" + System.currentTimeMillis()
        var short = app.label.trim()
        if (short.length > 10) short = short.substring(0, 10)
        val info = ShortcutInfo.Builder(context, id)
            .setShortLabel(short)
            .setIcon(Icon.createWithBitmap(bitmap))
            .setIntent(intent)
            .build()
        return sm.requestPinShortcut(info, null)
    }
}

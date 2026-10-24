package com.gikoko.icon

object PackIcons {

    class Item(val label: String, val icon: Int, val packs: Array<String>)

    val items: Array<Item> = arrayOf(
        Item("Phone", R.drawable.gikoko_phone, arrayOf("com.android.dialer", "com.google.android.dialer", "com.samsung.android.dialer")),
        Item("Contacts", R.drawable.gikoko_contacts, arrayOf("com.android.contacts", "com.samsung.android.app.contacts", "com.google.android.contacts")),
        Item("Messages", R.drawable.gikoko_messages, arrayOf("com.google.android.apps.messaging", "com.android.mms", "com.samsung.android.messaging")),
        Item("WhatsApp", R.drawable.gikoko_whatsapp, arrayOf("com.whatsapp")),
        Item("Messenger", R.drawable.gikoko_messenger, arrayOf("com.facebook.orca")),
        Item("Instagram", R.drawable.gikoko_instagram, arrayOf("com.instagram.android")),
        Item("Facebook", R.drawable.gikoko_facebook, arrayOf("com.facebook.katana", "com.facebook.lite")),
        Item("Telegram", R.drawable.gikoko_telegram, arrayOf("org.telegram.messenger")),
        Item("X", R.drawable.gikoko_twitter, arrayOf("com.twitter.android")),
        Item("TikTok", R.drawable.gikoko_tiktok, arrayOf("com.zhiliaoapp.musically", "com.ss.android.ugc.aweme")),
        Item("Camera", R.drawable.gikoko_camera, arrayOf("com.android.camera2", "com.google.android.GoogleCamera", "com.sec.android.app.camera")),
        Item("Photos", R.drawable.gikoko_photos, arrayOf("com.google.android.apps.photos")),
        Item("Gallery", R.drawable.gikoko_gallery, arrayOf("com.sec.android.gallery3d", "com.android.gallery3d")),
        Item("Browser", R.drawable.gikoko_browser, arrayOf("com.android.chrome", "com.android.browser", "org.mozilla.firefox", "com.opera.browser")),
        Item("Play Store", R.drawable.gikoko_play, arrayOf("com.android.vending")),
        Item("YouTube", R.drawable.gikoko_youtube, arrayOf("com.google.android.youtube")),
        Item("Gmail", R.drawable.gikoko_gmail, arrayOf("com.google.android.gm")),
        Item("Maps", R.drawable.gikoko_maps, arrayOf("com.google.android.apps.maps")),
        Item("Clock", R.drawable.gikoko_clock, arrayOf("com.android.deskclock", "com.google.android.deskclock", "com.sec.android.app.clockpackage")),
        Item("Calculator", R.drawable.gikoko_calculator, arrayOf("com.android.calculator2", "com.google.android.calculator", "com.sec.android.app.popupcalculator")),
        Item("Calendar", R.drawable.gikoko_calendar, arrayOf("com.android.calendar", "com.google.android.calendar", "com.sec.android.app.scalendar")),
        Item("Settings", R.drawable.gikoko_settings, arrayOf("com.android.settings")),
        Item("Files", R.drawable.gikoko_files, arrayOf("com.google.android.apps.nbu.files", "com.sec.android.app.myfiles", "com.google.android.apps.docs")),
        Item("Music", R.drawable.gikoko_music, arrayOf("com.google.android.music", "com.google.android.apps.youtube.music", "com.spotify.music", "com.sec.android.app.music")),
        Item("Video", R.drawable.gikoko_video, arrayOf("org.videolan.vlc", "com.netflix.mediaclient")),
    )
}

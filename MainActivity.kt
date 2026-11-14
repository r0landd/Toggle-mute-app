package com.example.mediamute

import android.app.Activity
import android.os.Bundle
import android.service.quicksettings.TileService
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val layout = LinearLayout(this).apply {
      orientation = LinearLayout.VERTICAL
      setPadding(48, 64, 48, 48)
    }
    layout.addView(TextView(this).apply {
      text = "Media Mute\n\nAdd the Media mute tile to Quick Settings. It mutes media audio only and restores the previous media volume when tapped again. Ringtone, notifications and alarms are unchanged."
      textSize = 18f
    })
    layout.addView(Button(this).apply {
      text = "Add Quick Settings tile"
      setOnClickListener {
        if (android.os.Build.VERSION.SDK_INT >= 33) {
          TileService.requestAddTileService(
            this@MainActivity,
            android.content.ComponentName(this@MainActivity, MediaMuteTileService::class.java),
            "Media mute",
            android.graphics.drawable.Icon.createWithResource(this@MainActivity, R.drawable.ic_volume_up),
            mainExecutor
          ) { }
        }
      }
    })
    setContentView(layout)
  }
}

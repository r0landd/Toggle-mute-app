package com.example.mediamute

import android.media.AudioManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.graphics.drawable.Icon

class MediaMuteTileService : TileService() {
  private lateinit var audio: AudioManager
  private val prefs by lazy { getSharedPreferences("media_mute", MODE_PRIVATE) }

  override fun onCreate() {
    super.onCreate()
    audio = getSystemService(AUDIO_SERVICE) as AudioManager
  }

  override fun onStartListening() {
    super.onStartListening()
    updateTile()
  }

  override fun onClick() {
    super.onClick()
    val current = audio.getStreamVolume(AudioManager.STREAM_MUSIC)
    if (current > 0) {
      prefs.edit().putInt("previous_volume", current).putBoolean("muted", true).apply()
      audio.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
    } else {
      val max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
      val saved = prefs.getInt("previous_volume", max.coerceAtLeast(1) / 2)
      audio.setStreamVolume(AudioManager.STREAM_MUSIC, saved.coerceIn(1, max), 0)
      prefs.edit().putBoolean("muted", false).apply()
    }
    updateTile()
  }

  private fun updateTile() {
    val muted = audio.getStreamVolume(AudioManager.STREAM_MUSIC) == 0
    qsTile?.apply {
      state = if (muted) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
      label = "Media mute"
      subtitle = if (muted) "Muted" else "Sound on"
      icon = Icon.createWithResource(
        this@MediaMuteTileService,
        if (muted) R.drawable.ic_volume_off else R.drawable.ic_volume_up
      )
      updateTile()
    }
  }
}

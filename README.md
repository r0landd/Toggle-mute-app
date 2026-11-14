# Media Mute (Android)

A Quick Settings tile that mutes only `STREAM_MUSIC` and restores the last non-zero media volume when tapped again. Ringtone, notification and alarm streams are not changed.

## Build

Open this folder in Android Studio (Giraffe or newer), allow Gradle sync, then choose **Build > Build Bundle(s) / APK(s) > Build APK(s)**. The debug APK will be at `app/build/outputs/apk/debug/app-debug.apk`.

## Install and add the tile

1. Install the APK.
2. Open **Media Mute** and tap **Add Quick Settings tile** (Android 13+), or edit the Quick Settings panel and drag **Media mute** into the active tiles.
3. Tap the tile to mute media; tap again to restore its previous non-zero volume.

The Android build environment is not included in this source package, so the APK must be compiled with Android Studio or an installed Android SDK.

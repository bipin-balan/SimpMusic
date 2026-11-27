package com.maxrave.simpmusic.expect.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
actual fun PlatformRemoteDeviceButton(
    modifier: Modifier,
    tint: Color,
) {
    // No-op: the HEOS integration is built on Media3 and only exists on Android for now.
}

actual fun isPlatformRemoteDeviceAvailable(): Boolean = false

// Desktop has no HEOS/DLNA integration yet, so the list is always empty and the actions do nothing.
private val NoRemoteDevices = RemoteDevices(devices = emptyList(), isSearching = false, connect = {}, disconnect = {})

@Composable
actual fun rememberRemoteDevices(discover: Boolean): RemoteDevices = NoRemoteDevices

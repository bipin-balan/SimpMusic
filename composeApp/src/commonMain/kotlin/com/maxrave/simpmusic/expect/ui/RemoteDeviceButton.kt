package com.maxrave.simpmusic.expect.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * "Play on HEOS device" button (Denon / Marantz receivers, HEOS speakers). Tapping it opens a
 * picker that discovers HEOS players on the local network and hands the queue to the chosen
 * one; while a device is active the same sheet offers to bring playback back to this device.
 *
 * Renders nothing on platforms without the HEOS integration (Desktop, for now).
 *
 * [tint] colours the icon — callers flip it to signal an active remote session.
 */
@Composable
expect fun PlatformRemoteDeviceButton(
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
)

/**
 * Whether [PlatformRemoteDeviceButton] would actually render anything. Layouts that give the
 * button its own container must hide the container too when this is false.
 */
expect fun isPlatformRemoteDeviceAvailable(): Boolean

/** A HEOS or DLNA device on the network playback can be handed to. [isConnected] while playback is on it. */
@Immutable
data class RemoteOutputDevice(
    val id: String,
    val name: String,
    /** Protocol label for the status line: "HEOS" or "DLNA". */
    val protocol: String,
    val isConnected: Boolean,
)

/** The network devices found, plus the two things a picker does with them — the shape [CastReceivers] has. */
@Stable
class RemoteDevices(
    val devices: List<RemoteOutputDevice>,
    val isSearching: Boolean,
    val connect: (id: String) -> Unit,
    val disconnect: () -> Unit,
)

/**
 * HEOS / DLNA devices for the unified output sheet, scanned only while [discover] is true — i.e.
 * while the list is on screen. Always empty where the integration is not built (desktop).
 */
@Composable
expect fun rememberRemoteDevices(discover: Boolean): RemoteDevices

package org.company.app

import java.awt.Desktop
import java.net.URI

internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

internal actual fun makeCall(number: String?) {
    val uri = number?.let { URI.create("tel:$it") } ?: return
    Desktop.getDesktop().browse(uri)
}

internal actual fun openMap(coordinates: String?) {
    val uri = coordinates?.let {
        URI.create(
            "https://www.google.com/maps/search/?api=1&query=${
                coordinates.substringBefore(",")
            }%2C${coordinates.substringAfter(", ")}"
        )
    } ?: return
    Desktop.getDesktop().browse(uri)
}
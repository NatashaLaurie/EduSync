package org.company.app

import kotlinx.browser.window

internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}

internal actual fun makeCall(number: String?) {
}

internal actual fun openMap(coordinates: String?) {
}
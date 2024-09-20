package org.company.app

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

internal actual fun makeCall(number: String?) {
    val nsUrl = number?.let { NSURL.URLWithString("tel://$number") } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

internal actual fun openMap(coordinates: String?) {
    val nsUrl = coordinates?.let { NSURL.URLWithString("http://maps.apple.com/maps?saddr=$coordinates") } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

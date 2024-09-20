package org.company.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat.startActivity
import me.sujanpoudel.utils.contextProvider.applicationContext
import org.company.app.di.initKoin
import org.koin.android.ext.koin.androidContext

@SuppressLint("StaticFieldLeak")
val context: Context = applicationContext

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AndroidApp)
        }
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(context, intent, null)
}

internal actual fun makeCall(number: String?) {
    val uri = Uri.parse("tel:$number")
    val intent = Intent().apply {
        action = Intent.ACTION_DIAL
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(context, intent, null)
}

internal actual fun openMap(coordinates: String?) {
    val uri = Uri.parse("geo:$coordinates")
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(context, intent, null)
}
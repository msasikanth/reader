/*
 * Copyright 2023 Sasikanth Miriyampalli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.sasikanth.rss.reader

import MainView
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import dev.sasikanth.rss.reader.database.DriverFactory
import dev.sasikanth.rss.reader.di.AppComponent
import dev.sasikanth.rss.reader.di.create

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
    window.navigationBarColor = Color.TRANSPARENT

    val appComponent =
      AppComponent::class.create(
        componentContext = defaultComponentContext(),
        driverFactory = DriverFactory(this)
      )

    setContent {
      MainView(homeViewModelFactory = appComponent.homeViewModelFactory, openLink = ::openLink)
    }
  }

  private fun openLink(url: String) {
    val intent =
      Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
    startActivity(intent)
  }
}

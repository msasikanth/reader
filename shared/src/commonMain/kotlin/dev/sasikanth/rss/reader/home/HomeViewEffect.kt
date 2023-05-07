package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.database.Post

sealed interface HomeViewEffect {
  object NavigateToAddScreen : HomeViewEffect

  data class OpenPost(val post: Post) : HomeViewEffect
}

package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.database.Feed
import dev.sasikanth.rss.reader.database.Post

sealed interface HomeEffect {

  object LoadContent : HomeEffect

  object RefreshContent : HomeEffect

  object NavigateToAddScreen : HomeEffect

  data class LoadSelectedFeedPosts(val feed: Feed) : HomeEffect

  data class OpenPost(val post: Post) : HomeEffect
}

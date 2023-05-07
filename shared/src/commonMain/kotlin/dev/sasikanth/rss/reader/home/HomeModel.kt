package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.database.Feed
import dev.sasikanth.rss.reader.database.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed interface HomeLoadingState {
  object Idle : HomeLoadingState
  object Loading : HomeLoadingState
  data class Error(val message: String) : HomeLoadingState
}

data class HomeModel(
  val feeds: Flow<List<Feed>>,
  val posts: Flow<List<Post>>,
  val selectedFeed: Feed?,
  val loadingState: HomeLoadingState
) {

  companion object {

    val DEFAULT = HomeModel(
      feeds = emptyFlow(),
      posts = emptyFlow(),
      selectedFeed = null,
      loadingState = HomeLoadingState.Idle
    )
  }
}

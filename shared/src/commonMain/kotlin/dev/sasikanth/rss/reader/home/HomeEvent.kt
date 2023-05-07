package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.database.Feed
import dev.sasikanth.rss.reader.database.Post
import kotlinx.coroutines.flow.Flow

sealed interface HomeEvent {

  object OnSwipeToRefresh : HomeEvent

  object OnAddFeedClicked : HomeEvent

  data class OnFeedSelected(val feed: Feed) : HomeEvent

  data class OnPostClicked(val post: Post) : HomeEvent

  data class OnContentLoaded(
    val feeds: Flow<List<Feed>>,
    val posts: Flow<List<Post>>
  ) : HomeEvent

  data class OnSelectedFeedPostsLoaded(val posts: Flow<List<Post>>) : HomeEvent
}

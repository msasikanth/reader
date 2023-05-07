package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.home.HomeEffect.LoadSelectedFeedPosts
import dev.sasikanth.rss.reader.home.HomeEffect.NavigateToAddScreen
import dev.sasikanth.rss.reader.home.HomeEffect.OpenPost
import dev.sasikanth.rss.reader.home.HomeEffect.RefreshContent
import dev.sasikanth.rss.reader.home.HomeEvent.OnAddFeedClicked
import dev.sasikanth.rss.reader.home.HomeEvent.OnContentLoaded
import dev.sasikanth.rss.reader.home.HomeEvent.OnFeedSelected
import dev.sasikanth.rss.reader.home.HomeEvent.OnPostClicked
import dev.sasikanth.rss.reader.home.HomeEvent.OnSwipeToRefresh
import kt.mobius.Next
import kt.mobius.Next.Companion.dispatch
import kt.mobius.Next.Companion.next
import kt.mobius.Update

class HomeUpdate : Update<HomeModel, HomeEvent, HomeEffect> {

  override fun update(model: HomeModel, event: HomeEvent): Next<HomeModel, HomeEffect> {
    return when (event) {
      OnAddFeedClicked -> dispatch(setOf(NavigateToAddScreen))
      is OnFeedSelected -> dispatch(setOf(LoadSelectedFeedPosts(event.feed)))
      is OnPostClicked -> dispatch(setOf(OpenPost(event.post)))
      OnSwipeToRefresh -> dispatch(RefreshContent)
      is OnContentLoaded -> next(
        model.copy(
          feeds = event.feeds,
          posts = event.posts
        )
      )
    }
  }
}

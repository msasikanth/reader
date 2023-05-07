package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.home.HomeEffect.LoadContent
import dev.sasikanth.rss.reader.home.HomeEffect.LoadSelectedFeedPosts
import dev.sasikanth.rss.reader.home.HomeEffect.NavigateToAddScreen
import dev.sasikanth.rss.reader.home.HomeEffect.OpenPost
import dev.sasikanth.rss.reader.home.HomeEffect.RefreshContent
import dev.sasikanth.rss.reader.home.HomeEvent.OnContentLoaded
import dev.sasikanth.rss.reader.home.HomeEvent.OnSelectedFeedPostsLoaded
import dev.sasikanth.rss.reader.repository.RssRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kt.mobius.flow.FlowTransformer
import kt.mobius.flow.subtypeEffectHandler

@OptIn(ExperimentalCoroutinesApi::class)
class HomeEffectHandler(
  private val rssRepository: RssRepository,
  private val viewEffects: Channel<HomeViewEffect>
) {

  operator fun invoke(): FlowTransformer<HomeEffect, HomeEvent> {
    return subtypeEffectHandler {
      addTransformer<LoadContent>(::loadContent)
      addAction<RefreshContent> { rssRepository.updateFeeds() }
      addAction<NavigateToAddScreen> {
        viewEffects.send(HomeViewEffect.NavigateToAddScreen)
      }
      addTransformer<LoadSelectedFeedPosts>(::loadSelectedFeedPosts)
      addConsumer<OpenPost> {
        viewEffects.send(HomeViewEffect.OpenPost(it.post))
      }
    }
  }

  private fun loadContent(effects: Flow<LoadContent>): Flow<HomeEvent> {
    return effects
      .mapLatest {
        val feeds = rssRepository.allFeeds()
        val posts = rssRepository.allPosts()

        OnContentLoaded(feeds = feeds, posts = posts)
      }
  }

  private fun loadSelectedFeedPosts(effects: Flow<LoadSelectedFeedPosts>): Flow<HomeEvent> {
    return effects
      .mapLatest {
        val posts = rssRepository.postsOfFeed(feedLink = it.feed.link)

        OnSelectedFeedPostsLoaded(posts = posts)
      }
  }
}

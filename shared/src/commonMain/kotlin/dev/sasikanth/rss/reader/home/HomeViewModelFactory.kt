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
package dev.sasikanth.rss.reader.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dev.sasikanth.rss.reader.feeds.FeedsViewModelFactory
import dev.sasikanth.rss.reader.repository.RssRepository
import dev.sasikanth.rss.reader.utils.DispatchersProvider
import dev.sasikanth.rss.reader.utils.ObservableSelectedFeed
import me.tatarka.inject.annotations.Inject

@Inject
class HomeViewModelFactory(
  componentContext: ComponentContext,
  rssRepository: RssRepository,
  postsListTransformationUseCase: PostsListTransformationUseCase,
  observableSelectedFeed: ObservableSelectedFeed,
  feedsViewModelFactory: (ComponentContext) -> FeedsViewModelFactory,
  dispatchersProvider: DispatchersProvider
) : ComponentContext by componentContext {

  internal val viewModel =
    instanceKeeper.getOrCreate {
      HomeViewModel(
        lifecycle = lifecycle,
        backHandler = backHandler,
        rssRepository = rssRepository,
        postsListTransformationUseCase = postsListTransformationUseCase,
        observableSelectedFeed = observableSelectedFeed,
        dispatchersProvider = dispatchersProvider
      )
    }

  internal val feedsViewModel =
    feedsViewModelFactory(childContext("feeds_viewmodel_factory")).viewModel
}

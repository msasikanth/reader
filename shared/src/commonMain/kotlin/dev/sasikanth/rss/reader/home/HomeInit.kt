package dev.sasikanth.rss.reader.home

import dev.sasikanth.rss.reader.home.HomeEffect.LoadContent
import dev.sasikanth.rss.reader.home.HomeEffect.RefreshContent
import kt.mobius.First
import kt.mobius.First.Companion.first
import kt.mobius.Init

class HomeInit : Init<HomeModel, HomeEffect> {

  override fun init(model: HomeModel): First<HomeModel, HomeEffect> {
    return first(
      model, setOf(
        LoadContent,
        RefreshContent
      )
    )
  }
}

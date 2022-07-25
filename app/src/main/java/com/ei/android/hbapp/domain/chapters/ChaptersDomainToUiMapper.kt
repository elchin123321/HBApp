package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.core.ResourceProvider
import com.ei.android.hbapp.presentation.chapters.ChaptersUi

abstract class ChaptersDomainToUiMapper(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ChapterDomain>, ChaptersUi>(resourceProvider)
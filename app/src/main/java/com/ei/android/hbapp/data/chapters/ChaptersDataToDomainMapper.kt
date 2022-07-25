package com.ei.android.hbapp.data.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.domain.chapters.ChaptersDomain

abstract class ChaptersDataToDomainMapper:Abstract.Mapper.DataToDomain.Base<List<ChapterData>,ChaptersDomain>()
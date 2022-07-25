package com.ei.android.hbapp.domain.chapters

import com.ei.android.hbapp.core.Abstract
import com.ei.android.hbapp.data.chapters.ChapterId
import com.ei.android.hbapp.presentation.chapters.ChapterUi

interface ChapterDomainToUiMapper : Abstract.Mapper.Data<ChapterId, ChapterUi>
package com.ei.android.hbapp.presentation.chapters

import com.ei.android.hbapp.core.Communication

interface ChaptersCommunication : Communication<List<ChapterUi>> {

    class Base : Communication.Base<List<ChapterUi>>(), ChaptersCommunication
}
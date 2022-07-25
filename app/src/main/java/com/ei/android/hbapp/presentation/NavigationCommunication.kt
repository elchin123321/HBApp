package com.ei.android.hbapp.presentation

import com.ei.android.hbapp.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}
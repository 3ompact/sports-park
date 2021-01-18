package com.mda.common_ui_base.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.annotations.NotNull

/**
 * 在使用的地方
 *
 * getLifecycle().addObserver(mPresenter);//添加LifecycleObserver,当相应的生命周期执行时会调用相应的方法
 */
interface BaseLifecycle : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(@NotNull owner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(@NotNull owner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChanged(@NotNull owner: LifecycleOwner, @NotNull event: Lifecycle.Event)
}
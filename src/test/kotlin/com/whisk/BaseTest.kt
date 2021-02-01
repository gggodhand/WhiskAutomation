package com.whisk

import com.whisk.util.Settings
import com.whisk.util.selector.Block
import com.whisk.util.selector.ReflectionHelper
import com.whisk.util.properties.PropertyManager
import org.reflections.Reflections
import org.testng.annotations.BeforeClass

open class BaseTest {

    @BeforeClass
    fun init() {
        if(!isInit) {
            initRef()
        }
        isInit = true
    }

    companion object {
        var isInit = false

        fun initRef() {
            Reflections("com.whisk").getSubTypesOf(Block::class.java).forEach {
                if(ReflectionHelper.isObject(it)) {
                    ReflectionHelper.scanObject(it)
                }
            }

            PropertyManager.injectToObject(Settings::class.java)
        }
    }
}

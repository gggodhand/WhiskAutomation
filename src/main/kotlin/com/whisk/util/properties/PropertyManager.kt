package com.whisk.util.properties

import com.whisk.util.selector.ReflectionHelper

object PropertyManager {
    fun injectToObject(cls: Class<*>) {
        if(ReflectionHelper.isObject(cls)) {
            val props = cls.getAnnotation(Properties::class.java)

            val obj = ReflectionHelper.getObject(cls)
            val fields = ReflectionHelper.getFieldsFromObj(obj)

            val prop = java.util.Properties()
            prop.load(Thread.currentThread().contextClassLoader.getResourceAsStream(props.source))

            fields.forEach {
                val p = it.getAnnotation(Property::class.java).key
                it.isAccessible = true
                it.set(obj, prop[p])
            }
        }
    }
}

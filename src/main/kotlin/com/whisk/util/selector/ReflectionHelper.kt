package com.whisk.util.selector

import java.lang.reflect.Field
import kotlin.reflect.KClass

object ReflectionHelper {

    fun isObject(clazz: Class<*>): Boolean {
        for (f in clazz.declaredFields) {
            if (f.name == "INSTANCE") {
                return true
            }
        }

        return false
    }

    fun getObject(clazz: Class<*>): Any {
        return clazz.getField("INSTANCE").get(null)
    }

    fun getClassSelectors(obj: Any): ArrayList<Selector> {
        var res = ArrayList<Selector>()
        val fields = getFieldsFromObj(obj)

        for (f in fields) {
            f.isAccessible = true
            val member = f.get(obj)
            if (member is Selector) {
                member.name = f.name
                res.add(member)
            }
        }

        return res
    }

    fun getFieldsFromObj(obj: Any): List<out Field> {
        var fields = obj.javaClass.declaredFields.filter { !it.name.startsWith("$") }.toTypedArray()

        if (isObject(obj.javaClass)) {
            if (fields.size == 1 && fields.first().name == "INSTANCE") {
                fields = obj.javaClass.superclass.declaredFields
            }
        }

        return fields.filter { !it.name.contains("$") && it.name != "INSTANCE" }
    }

    fun getInnerClassSelectors(obj: Any, root: Any = obj): ArrayList<Selector> {
        var res = ArrayList<Selector>()

        val fields = getFieldsFromObj(obj)

        for (f in fields) {
            f.isAccessible = true
            val member = f.get(root)
            if (member is Selector) {
                if (f.type.simpleName != "Selector") {
                    res.add(member)
                }
            }
        }

        return res
    }

    fun scanObject(obj: Any) {
        val obj = getObject(obj as Class<*>)
        if(obj is Block) {
            val name = obj::class.simpleName.toString()
            val selectors = getClassSelectors(obj)
            selectors.forEach {
                it.name = "$name.${it.name}"
                it.base = obj.base
            }
        }
    }

    fun isNestedClassOf(sourceClass: KClass<*>, innerClass: KClass<*>): Boolean {
        val sname = sourceClass.qualifiedName!!
        val iname = innerClass.qualifiedName!!

        if (sname.endsWith(".Selector")) {
            return false
        }

        return iname.startsWith(sname) && sname.length < iname.length
    }
}

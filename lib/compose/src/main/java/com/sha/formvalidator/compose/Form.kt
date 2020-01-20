package com.sha.formvalidator.compose

class Form {
    internal var list: MutableList<ValidatableModel<*>> = mutableListOf()
    private val composeValidator = ComposeValidator(this)
    val isValid: Boolean
        get() = composeValidator.isValid

    val isEmpty
        get() = list.isEmpty()
    val size
        get() = list.size

    fun add(vararg models: ValidatableModel<*>) = list.addAll(models)
    fun remove(vararg models: ValidatableModel<*>) = list.removeAll(models)

    fun modelByTag(tag: String): ValidatableModel<*>? = list.firstOrNull { it.tag == tag }

    fun removeByTag(tag: String): ValidatableModel<*>? {
        val model = modelByTag(tag)
        model?.run { remove(this) }
        return model
    }

    operator fun <V> plus(model: ValidatableModel<V>): ValidatableModel<V> {
        add(model)
        return model
    }

    operator fun <V> minus(model: ValidatableModel<V>): ValidatableModel<V> {
        remove(model)
        return model
    }

    companion object {
        fun create(block: (Form.() -> Unit)?): Form {
            return Form().apply { block?.invoke(this) }
        }
    }
}

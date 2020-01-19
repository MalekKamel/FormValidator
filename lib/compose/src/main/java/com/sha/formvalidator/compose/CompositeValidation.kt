package com.sha.formvalidator.compose

class CompositeValidation<T: Validatable> {
    internal var list: MutableList<T> = mutableListOf()
    private val composeValidator = ComposeValidator(this)
    val isValid: Boolean
        get() = composeValidator.isValid
    fun  add(model: T) = list.add(model)
    fun addAll(models: List<T>) = list.addAll(models)
    fun remove(model: T)  = list.remove(model)
    fun removeAll(models: List<T>) = list.removeAll(models)
    fun isEmpty() = list.isEmpty()

    operator fun plus(validation: T): CompositeValidation<T> {
        add(validation)
        return this
    }

    operator fun minus(validation: T): CompositeValidation<T> {
        remove(validation)
        return this
    }

    companion object {
        fun <T: Validatable> create(block: (CompositeValidation<T>.() -> Unit)?): CompositeValidation<T> {
            return CompositeValidation<T>().apply { block?.invoke(this) }
        }
    }
}

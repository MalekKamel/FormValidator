package com.sha.formvalidator.compose

class FormValidation<T: Validatable> {
    internal var list: MutableList<T> = mutableListOf()
    private val composeValidator = ComposeValidator(this)
    val isValid: Boolean
        get() = composeValidator.isValid
    fun  add(model: T) = list.add(model)
    fun addAll(models: List<T>) = list.addAll(models)
    fun remove(model: T)  = list.remove(model)
    fun removeAll(models: List<T>) = list.removeAll(models)
    fun isEmpty() = list.isEmpty()

    operator fun plus(validation: T): T {
        add(validation)
        return validation
    }

    operator fun minus(validation: T): FormValidation<T> {
        remove(validation)
        return this
    }

    companion object {
        fun <T: Validatable> create(block: (FormValidation<T>.() -> Unit)?): FormValidation<T> {
            return FormValidation<T>().apply { block?.invoke(this) }
        }
    }
}

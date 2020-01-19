package com.sha.formvalidator.compose

class FormValidation {
    internal var list: MutableList<ValidatableModel<*>> = mutableListOf()
    private val composeValidator = ComposeValidator(this)
    val isValid: Boolean
        get() = composeValidator.isValid

    fun  add(model: ValidatableModel<*>) = list.add(model)
    fun addAll(models: List<ValidatableModel<*>>) = list.addAll(models)
    fun remove(model: ValidatableModel<*>)  = list.remove(model)
    fun removeAll(models: List<ValidatableModel<*>>) = list.removeAll(models)
    fun isEmpty() = list.isEmpty()

    fun modelByTag(tag: String): ValidatableModel<*>? = list.firstOrNull { it.tag == tag }

    operator fun <V> plus(model: ValidatableModel<V>): ValidatableModel<V> {
        add(model)
        return model
    }

    operator fun <V> minus(model: ValidatableModel<V>): ValidatableModel<V> {
        remove(model)
        return model
    }

    companion object {
        fun create(block: (FormValidation.() -> Unit)?): FormValidation {
            return FormValidation().apply { block?.invoke(this) }
        }
    }
}

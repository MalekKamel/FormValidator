package com.sha.formvalidator.compose

class CompositeValidation<T: ValidatableModel> {
    internal var list: MutableList<T> = mutableListOf()
    val isValid: Boolean = ComposeValidator(this).isValid
    fun  add(model: T) = list.add(model)
    fun addAll(models: List<T>) = list.addAll(models)
    fun remove(model: T)  = list.remove(model)
    fun removeAll(models: List<T>) = list.removeAll(models)
}

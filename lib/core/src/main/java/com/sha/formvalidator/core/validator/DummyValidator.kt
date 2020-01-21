package com.sha.formvalidator.core.validator

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
class DummyValidator<V> : AbsValidator<V>() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create("")
    override fun validate(): Boolean  = true
}

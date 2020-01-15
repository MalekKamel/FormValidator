package com.sha.formvalidator.compose

object Validation {

    fun floatRange(
            min: Float,
            max: Float,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (FloatRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Float> {
        return FloatRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun doubleRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (DoubleRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Double> {
        return DoubleRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun longRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (LongRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Long> {
        return LongRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun intRange(
            min: Int,
            max: Int,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (IntRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Int> {
        return IntRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun charRange(
            min: Char,
            max: Char,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (CharRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Char> {
        return CharRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun shortRange(
            min: Short,
            max: Short,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ShortRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Short> {
        return ShortRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun byteRange(
            min: Byte,
            max: Byte,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ByteRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Byte> {
        return ByteRangeValidation(min, max)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

    fun boolean(
            validation: Boolean,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (BooleanValidation.() -> Unit)? = null
    ): ValidatableModel<Boolean> {
        return BooleanValidation(validation)
                .apply {
                    block?.invoke(this)
                    compositeValidation?.add(this)
                }
    }

}
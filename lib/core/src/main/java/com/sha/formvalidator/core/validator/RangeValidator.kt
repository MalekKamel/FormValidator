package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors


class WrapValidator<IN, OUT>(
        private val validator: Validator<IN>,
        private val convertValue: (OUT?) -> IN?
): Validator<OUT> {
    override var errorMessage: String = ""
        set(value) {
            field = value
            validator.errorMessage = value
        }

    override var value: OUT? = null
        set(value) {
            field = value
            val v = convertValue(value)
            validator.value = v
        }
    override fun validate() = validator.isValid
}

/**
 * A validator that returns true only if the text is within the given range.
 */
class LengthRangeTextValidator(
        private val min: Long,
        private val max: Long
) : TextValidator() {
    override var errorMessage: String = DefaultErrors.rangeError

    override fun validate(): Boolean {
        return if (value != null) value!!.length in min..max else false
    }
}

class DoubleRangeValidator(
        private val min: Double,
        private val max: Double
): Validator<Double> {
    override var value: Double? = 0.0
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorMessage: String = DefaultErrors.rangeError
}

class FloatRangeValidator(
        private val min: Float,
        private val max: Float
): Validator<Float> {
    override var value: Float? = 0f
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorMessage: String = DefaultErrors.rangeError
}

class LongRangeValidator(
        private val min: Long,
        private val max:Long
): Validator<Long> {
    override var value: Long? = 0
    override fun validate() = value in min..max
    override var errorMessage: String = DefaultErrors.rangeError
}

class IntRangeValidator(
        private val min: Int,
        private val max:Int
): Validator<Int> {
    override var value: Int? = 0
    override fun validate() = value in min..max
    override var errorMessage: String = DefaultErrors.rangeError
}

class CharRangeValidator(
        private val min: Char,
        private val max: Char
): Validator<Char> {
    override var value: Char? = ' '
    override fun validate() = value in min..max
    override var errorMessage: String = DefaultErrors.rangeError
}

class ShortRangeValidator(
        private val min: Short,
        private val max: Short
): Validator<Short> {
    override var value: Short? = 0
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorMessage: String = DefaultErrors.rangeError
}

class ByteRangeValidator(
        private val min: Byte,
        private val max: Byte
): Validator<Byte> {
    override var value: Byte? = 0
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorMessage: String = DefaultErrors.rangeError
}
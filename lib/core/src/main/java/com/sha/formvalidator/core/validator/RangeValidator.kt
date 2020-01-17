package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 **/
class FloatRangeTextValidator(
        private val floatMin: Float,
        private val floatMax: Float,
        errorMessage: String
) : TextValidator(errorMessage) {
    override fun validate(): Boolean {
        return try { value.toDouble() in floatMin..floatMax } catch (e: NumberFormatException) { false }
    }
}

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 */
class LongRangeTextValidator(
        private val min: Long,
        private val max: Long,
        errorMessage: String) : TextValidator(errorMessage) {

    override fun validate(): Boolean {
        return try { value.toLong() in min..max } catch (e: NumberFormatException) { false }
    }
}

/**
 * A validator that returns true only if the text is within the given range.
 */
class LengthRangeTextValidator(
        errorMessage: String,
        private val min: Long,
        private val max: Long
) : TextValidator(errorMessage) {

    override fun validate(): Boolean {
        return value.length in min..max
    }
}

class DoubleRangeValidator(
        private val min: Double,
        private val max: Double
): Validator<Double> {
    override var value: Double = 0.0
    override fun validate() = value in min..max
}

class FloatRangeValidator(
        private val min: Float,
        private val max: Float
): Validator<Float> {
    override var value: Float = 0f
    override fun validate() = value in min..max
}

class LongRangeValidator(
        private val min: Long,
        private val max:Long
): Validator<Long> {
    override var value: Long = 0
    override fun validate() = value in min..max
}

class IntRangeValidator(
        private val min: Int,
        private val max:Int
): Validator<Int> {
    override var value: Int = 0
    override fun validate() = value in min..max
}

class CharRangeValidator(
        private val min: Char,
        private val max: Char
): Validator<Char> {
    override var value: Char = ' '
    override fun validate() = value in min..max
}

class ShortRangeValidator(
        private val min: Short,
        private val max: Short
): Validator<Short> {
    override var value: Short = 0
    override fun validate() = value in min..max
}

class ByteRangeValidator(
        private val min: Byte,
        private val max: Byte
): Validator<Byte> {
    override var value: Byte = 0
    override fun validate() = value in min..max
}
package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A validator that returns true only if the text is within the given range.
 */

class DoubleRangeValidator(
        private val min: Double,
        private val max: Double
): AbsValidator<Double>() {
    override var value: Double? = null
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}

class FloatRangeValidator(
        private val min: Float,
        private val max: Float
): AbsValidator<Float>() {
    override var value: Float? = null
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}

class LongRangeValidator(
        private val min: Long,
        private val max:Long
): AbsValidator<Long>() {
    override var value: Long? = null
    override fun validate() = value in min..max
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}

class IntRangeValidator(
        private val min: Int,
        private val max:Int
): AbsValidator<Int>() {
    override var value: Int? = null
    override fun validate() = value in min..max
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}

class CharRangeValidator(
        private val min: Char,
        private val max: Char
): AbsValidator<Char>() {
    override var value: Char? = null
    override fun validate() = value in min..max
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}

class ShortRangeValidator(
        private val min: Short,
        private val max: Short
): AbsValidator<Short>() {
    override var value: Short? = null
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}

class ByteRangeValidator(
        private val min: Byte,
        private val max: Byte
): AbsValidator<Byte>() {
    override var value: Byte? = null
    override fun validate() = if(value != null) { value!! in min..max } else false
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.range)
}
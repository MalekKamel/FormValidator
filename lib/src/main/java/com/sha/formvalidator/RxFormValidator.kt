package com.sha.formvalidator

import android.view.View

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor

/**
 * The class responsible for validating fields.
 */
class RxFormValidator<T : Validatable> : AbstractFormValidator<T> {

    /**
     * create an instance with list of fields to be validated.
     * @param fields the fields to be validated.
     */
    constructor(fields: List<T>) : super(fields)
    /**
     * create an instance with var args of fields to be
     * validated.
     * @param fields the fields to be validated.
     */
    @SafeVarargs
    constructor(vararg fields: T) : super(*fields)

    fun validate() = Single.just(isValid)

    /**
     * Set a listener to the view to validate on click.
     * @param view the view that triggers validation
     */
    fun validateOnClick(view: View): Flowable<Boolean> {
        val pp = PublishProcessor.create<Boolean>()
        view.setOnClickListener { pp.onNext(isValid) }
        return pp
    }


}

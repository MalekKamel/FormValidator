package com.sha.formvalidator

import android.view.View

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor

class RxFormValidator<T : Validatable> : BaseFormValidator<T> {

    constructor(fields: List<T>) : super(fields) {}

    @SafeVarargs
    constructor(vararg fields: T) : super(*fields)

    fun validate(): Single<Boolean> {
        return Single.just(isValid)
    }

    fun validateOnClick(view: View): Flowable<Boolean> {
        val pp = PublishProcessor.create<Boolean>()
        view.setOnClickListener { pp.onNext(isValid) }
        return pp
    }


}

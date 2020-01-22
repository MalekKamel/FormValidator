package com.sha.formvalidator

class TextViewAttrInfo {
    var errorMessage: String = ""
    var required = true
    var validateOnChange = false
    var validationType: XmlValidationType? = null
    var customValidationType: String = ""

    var regex: String = ""
    var dateFormat: String = ""

    var min: Int = 0
    var max: Int = 0

    var floatMin: Float = 0f
    var floatMax: Float = 0f
}

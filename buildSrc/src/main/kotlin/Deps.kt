
object Versions {
    const val test_junit = "4.12"
    const val androidx_junit = "1.1.1"
    const val androidx_espressoCore = "3.2.0"

    const val mockito_core = "3.0.0"
    const val hamcrest_all = "1.3"
    const val androidx_core_testing = "2.1.0"
    const val kotlinx_coroutine_test = "1.3.0"
    const val robolectric = "4.3-beta-1"
    const val espresso_contrib = "3.2.0"
    const val esspresso_intent = "3.2.0"
    const val truth = "0.44"
    const val androidx_test_core_ktx = "1.2.1-alpha02"
    const val androidx_test_ext_junit_ktx = "1.1.2-alpha02"
    const val androidx_test_rules = "1.3.0-alpha02"
    const val mockito_kotlin = "2.2.0"
    const val modelMapper = "0.1.0"
}

object Deps {
    const val test_junit = "junit:junit:${Versions.test_junit}"
    const val androidx_espressoCore = "androidx.test.espresso:espresso-core:${Versions.androidx_espressoCore}"
}

object TestDeps {

    const val test_junit = "junit:junit:${Versions.test_junit}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"

    // <editor-fold desc="mockito">
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    // </editor-fold>

    const val hamcrest_all = "org.hamcrest:hamcrest-all:${Versions.hamcrest_all}"
    const val kotlinx_coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutine_test}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_contrib}"
    const val esspresso_intent = "androidx.test.espresso:espresso-intents:${Versions.esspresso_intent}"
    const val truth = "com.google.truth:truth:${Versions.truth}"

    // <editor-fold desc="androidx">
    const val androidx_test_core_ktx = "androidx.test:core-ktx:${Versions.androidx_test_core_ktx}"
    const val androidx_test_ext_junit_ktx = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext_junit_ktx}"
    const val androidx_test_rules = "androidx.test:rules:${Versions.androidx_test_rules}"
    const val androidx_core_testing = "androidx.arch.core:core-testing:${Versions.androidx_core_testing}"
    // </editor-fold>

}
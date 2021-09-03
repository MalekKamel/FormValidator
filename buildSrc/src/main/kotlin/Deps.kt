
object Versions {
    const val test_junit = "4.12"
    const val androidx_junit = "1.1.1"
    const val androidx_espressoCore = "3.2.0"

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
}

object Deps {
    val compose = ComposeLibs
    val androidX = AndroidXLibs

    object AndroidXLibs {
        const val androidx_appCompat = "1.3.1"
        const val android_material = "1.4.0"
        const val androidx_core_ktx = "1.6.0"
        const val androidx_preference = "1.1.1"

        const val appCompat = "androidx.appcompat:appcompat:${androidx_appCompat}"
        const val material = "com.google.android.material:material:${android_material}"
        const val core_ktx = "androidx.core:core-ktx:${androidx_core_ktx}"
        const val preference = "androidx.preference:preference:${androidx_preference}"
    }

    object ComposeLibs {
        const val version = "1.0.1"
        const val constraintComposeVersion = "1.0.0-beta02"

        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val layout = "androidx.compose.foundation:foundation-layout:$version"
        const val material = "androidx.compose.material:material:$version"
        const val materialIconsExtended =
            "androidx.compose.material:material-icons-extended:$version"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val test = "androidx.compose.test:test-core:$version"
        const val uiTest = "androidx.compose.ui:ui-test:$version"
        const val constraintCompose =
            "androidx.constraintlayout:constraintlayout-compose:$constraintComposeVersion"

    }

}

object TestDeps {
    const val androidx_espressoCore = "androidx.test.espresso:espresso-core:${Versions.androidx_espressoCore}"
    const val junit = "junit:junit:${Versions.test_junit}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val androidx_test_core_ktx = "androidx.test:core-ktx:${Versions.androidx_test_core_ktx}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"

    const val hamcrest_all = "org.hamcrest:hamcrest-all:${Versions.hamcrest_all}"
    const val kotlinx_coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutine_test}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_contrib}"
    const val esspresso_intent = "androidx.test.espresso:espresso-intents:${Versions.esspresso_intent}"
    const val truth = "com.google.truth:truth:${Versions.truth}"

    // <editor-fold desc="androidx">
    const val androidx_test_ext_junit_ktx = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext_junit_ktx}"
    const val androidx_test_rules = "androidx.test:rules:${Versions.androidx_test_rules}"
    const val androidx_core_testing = "androidx.arch.core:core-testing:${Versions.androidx_core_testing}"
    // </editor-fold>
}
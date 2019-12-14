import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 14
    const val compileSdk = 29
    const val targetSdk = 29
    val javaVersion = JavaVersion.VERSION_1_8
    const val versionCode = 1
    const val versionName = "1.0.0"
}

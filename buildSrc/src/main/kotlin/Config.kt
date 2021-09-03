import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 30
    const val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    const val versionCode = 1
    const val versionName = "1.0.0"
}

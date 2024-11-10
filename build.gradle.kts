plugins {
    alias(kotlinLibs.plugins.kotlin.jvm) apply (false)
    alias(thirdPartyLibs.plugins.detekt) apply (false)
}

val appVersion: String by project

allprojects {
    version = appVersion
    group = "com.skramMaster"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

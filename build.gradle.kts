plugins {
    alias(kotlinLibs.plugins.kotlin.jvm) apply (false)
}

val appVersion: String by project

allprojects {
    version = appVersion
    group = "com.skramMaster"

    repositories {
        mavenCentral()
    }

    //TODO: Add test implementation

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

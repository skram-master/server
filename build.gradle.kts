val appVersion: String by project

plugins {
    alias(kotlinLibs.plugins.kotlin.jvm) apply (false)
}

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

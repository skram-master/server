rootProject.name = "com.skramMaster"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("kotlinLibs") {
            from(files("gradle/kotlin.versions.toml"))
        }
        create("ktorLibs") {
            from(files("gradle/ktor.versions.toml"))
        }
        create("thirdPartyLibs") {
            from(files("gradle/third-party.versions.toml"))
        }
    }
}

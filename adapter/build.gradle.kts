plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(kotlinLibs.plugins.kotlin.serialization)
}

dependencies {
    implementation(kotlinLibs.kotlin.serialization)

    implementation(project(":domain"))
}

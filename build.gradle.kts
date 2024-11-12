plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(thirdPartyLibs.plugins.detekt)
}

val appVersion: String by project

allprojects {
    version = appVersion
    group = "com.skramMaster"
}

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.html reports/detekt/merge.html"))
}

subprojects {
    repositories {
        mavenCentral()
    }

    beforeEvaluate {
        apply(plugin = kotlinLibs.plugins.kotlin.jvm.get().pluginId)
        apply(plugin = thirdPartyLibs.plugins.detekt.get().pluginId)
    }

    afterEvaluate {
        detekt {
            parallel = true

            config.from(files(project.rootDir.path + "/detekt/detekt.yml"))
            buildUponDefaultConfig = true

            basePath = rootDir.absolutePath
            ignoreFailures = true
            autoCorrect = true
        }

        dependencies {
            detektPlugins(thirdPartyLibs.detekt.formatting)
            testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
            testImplementation(thirdPartyLibs.kotest.assertions.core)
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "22"
        finalizedBy(reportMerge)
    }

    reportMerge {
        input.from(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().map { it.htmlReportFile })
    }
}

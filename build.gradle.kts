plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(thirdPartyLibs.plugins.detekt)
}

val appVersion: String by project

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(
        rootProject.layout.buildDirectory.file(
            "reports/detekt/merge.xml",
        ),
    )
}

allprojects {
    version = appVersion
    group = "com.skram_master"

    afterEvaluate {
        detekt {
            parallel = true

            config.from(files(project.rootDir.path + "/detekt/detekt.yml"))
            buildUponDefaultConfig = true
            basePath = rootDir.absolutePath
            ignoreFailures = false
        }

        dependencies {
            detektPlugins(thirdPartyLibs.detekt.formatting)
        }
    }
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
        dependencies {
            testImplementation(thirdPartyLibs.koin.test.junit5)
            testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
            testImplementation(thirdPartyLibs.kotest.assertions.core)
            testImplementation(thirdPartyLibs.kotest.extensions.koin)
            testImplementation(thirdPartyLibs.mockk)
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
        input.from(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().map { it.xmlReportFile })
    }
}

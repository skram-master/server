plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(thirdPartyLibs.plugins.detekt)
}

val appVersion: String by project

val sarifReportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(
        rootProject.layout.buildDirectory.file(
            "reports/detekt/merge.sarif",
        ),
    )
}

allprojects {
    version = appVersion
    group = "com.skramMaster"

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
            testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
            testImplementation(thirdPartyLibs.kotest.assertions.core)
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "22"
        finalizedBy(sarifReportMerge)
    }

    sarifReportMerge {
        input.from(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().map { it.sarifReportFile })
    }
}

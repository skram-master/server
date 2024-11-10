plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(thirdPartyLibs.plugins.detekt)
}

val appVersion: String by project

detekt {
    parallel = true

    config.from(files("detekt/detekt.yml"))
    buildUponDefaultConfig = true

    basePath = rootDir.absolutePath
}

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

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.html reports/detekt/merge.html"))
}

subprojects {
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        finalizedBy(reportMerge)
    }

    reportMerge {
        input.from(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().map { it.htmlReportFile })
    }
}
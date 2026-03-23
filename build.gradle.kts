import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.aboutlibraries) apply false
    alias(libs.plugins.aboutlibraries.multiplatform) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.sentry.gradle) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.build.config) apply false
    alias(libs.plugins.osdetector) apply false
}

tasks.register<Delete>("Clean") {
    delete(rootProject.layout.buildDirectory)
}

subprojects {
    // Configure Kotlin compilation to target JVM 21
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            if (project.findProperty("enableComposeCompilerReports") == "true") {
                arrayOf("reports", "metrics").forEach {
                    freeCompilerArgs.addAll(
                        listOf(
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:${it}Destination=${layout.buildDirectory.asFile.get().absolutePath}/compose_metrics",
                        ),
                    )
                }
            }
        }
    }
    
    // Configure Java compilation to target JVM 21
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
        options.release.set(21)
    }
    
    // Ensure Kotlin and Java JVM targets are consistent
    afterEvaluate {
        // For Android projects
        pluginManager.withPlugin("com.android.library") {
            extensions.findByType<com.android.build.gradle.LibraryExtension>()?.apply {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
            }
        }
        
        pluginManager.withPlugin("com.android.application") {
            extensions.findByType<com.android.build.gradle.AppExtension>()?.apply {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
            }
        }
    }
}

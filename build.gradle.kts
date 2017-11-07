import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("nebula.kotlin") version "1.1.51"
  id("nebula.dependency-lock") version "4.9.5"
  application
}

val kotlinVersion by project

repositories {
  jcenter()
}

dependencies {
  compile("org.funktionale:funktionale-partials:+")
}

configurations.all {
  resolutionStrategy {
    eachDependency {
      if (requested.group == "org.jetbrains.kotlin") {
        useVersion(kotlinVersion as String)
      }
    }
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    languageVersion = "1.1"
    jvmTarget = "1.8"
  }
}

application {
  applicationName = rootProject.name
  mainClassName = "spock4k.MainKt"
}
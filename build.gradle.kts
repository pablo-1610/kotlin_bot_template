import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.dokka") version "1.7.0"
}

group = "org.example.ktbot"
val compileVersion: String = "v1.0.0"
var mainClassName: String = "${group}.Main"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("io.github.cdimascio:java-dotenv:5.2.2")
    implementation("org.projectlombok:lombok:1.18.24")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.0")

    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.github.DV8FromTheWorld:JDA:v5.0.0-alpha.12")
    implementation("com.github.minndevelopment:jda-ktx:bf7cd96")
    implementation("io.github.reactivecircus.cache4k:cache4k:0.6.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.3.1")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    /*
     * Logger dependencies
     */
    implementation("ch.qos.logback", "logback-classic", "1.0.9")
    implementation("ch.qos.logback", "logback-core", "1.0.9")
    implementation("org.slf4j", "slf4j-api", "1.7.2")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.freeCompilerArgs = listOf(
        "-Xjvm-default=all",  // use default methods in interfaces
        "-Xlambdas=indy"      // use invoke-dynamic lambdas instead of synthetic classes
    )
}


tasks.withType<ShadowJar> {
    archiveBaseName.set("bot_template")
    archiveClassifier.set("")
    minimize()
}


tasks.withType<org.gradle.jvm.tasks.Jar> {
    manifest {
        attributes["Implementation-Title"] = "bot_template"
        attributes["Implementation-Version"] = compileVersion
        attributes["Main-Class"] = mainClassName
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokkaHtml"))
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(buildDir.resolve("dokkaJavadoc"))
}
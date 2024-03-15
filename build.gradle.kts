plugins {
    id("java")
    id("application")
}

group = "org.example"

repositories {
    mavenCentral()
}

application {
    mainClass = "org.example.Main"
}
tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "org.example.Main",
                "Implementation-Version" to archiveVersion
        )
    }
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
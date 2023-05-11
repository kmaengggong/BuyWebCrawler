plugins {
    id("java")
}

group = "org.kmaengggong"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jsoup:jsoup:1.16.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar>{
    manifest {
        attributes["Main-Class"] = "org.kmaengggong.Main"
    }
}
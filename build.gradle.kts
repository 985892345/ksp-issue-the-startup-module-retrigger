plugins {
  kotlin("jvm") version "1.9.22" apply false
  id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}

allprojects {
  repositories {
    mavenCentral()
    mavenLocal()
  }
}
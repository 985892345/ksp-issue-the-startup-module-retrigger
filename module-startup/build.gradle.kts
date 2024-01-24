plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp")
}

ksp {
  arg("fileName", "StartupKsp")
}

dependencies {
  implementation(project(":module-other"))
  ksp(project(":compile-ksp"))
}
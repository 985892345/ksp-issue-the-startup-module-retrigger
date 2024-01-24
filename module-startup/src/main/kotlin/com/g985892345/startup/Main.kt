package com.g985892345.startup

import com.g985892345.codegen.StartupKsp


/**
 * .
 *
 * @author 985892345
 * 2024/1/23 20:05
 */
fun main() {
  check(StartupKsp.getAllKClass().contains(TestClass::class))
  println("success")
}
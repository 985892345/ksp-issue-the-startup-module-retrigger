package com.g985892345.ksp

/**
 * .
 *
 * @author 985892345
 * 2023/12/4 21:29
 */
class Options(
  val fileName: String,
) {
  constructor(options: Map<String, String>) : this(
    options["fileName"]!!,
  )
}
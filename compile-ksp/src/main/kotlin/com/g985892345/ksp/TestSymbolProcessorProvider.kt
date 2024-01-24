package com.g985892345.ksp

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * .
 *
 * @author 985892345
 * 2024/1/23 20:30
 */
class TestSymbolProcessorProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return TestSymbolProcess(
      environment.logger,
      environment.codeGenerator,
      Options(environment.options)
    )
  }
}
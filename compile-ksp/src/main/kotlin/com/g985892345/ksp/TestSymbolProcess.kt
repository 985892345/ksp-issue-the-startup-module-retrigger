package com.g985892345.ksp

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration

/**
 * .
 *
 * @author 985892345
 * 2024/1/23 20:34
 */
class TestSymbolProcess(
  private val logger: KSPLogger,
  private val codeGenerator: CodeGenerator,
  private val options: Options
) : SymbolProcessor {

  private var finish = false

  override fun process(resolver: Resolver): List<KSAnnotated> {
    logger.warn("resolver.getNewFiles() = ${resolver.getNewFiles().map { it.fileName }.toList()}")
    if (finish) return emptyList()
    val classDeclarations = resolver.getSymbolsWithAnnotation("com.g985892345.startup.TestAnnotation")
      .filterIsInstance<KSClassDeclaration>()
      .toList()
    logger.warn("classDeclarations = $classDeclarations")
    codeGenerator.createNewFile(
      Dependencies(true, *classDeclarations.mapNotNull { it.containingFile }.toTypedArray()),
      "com.g985892345.codegen",
      options.fileName
    ).writer().use {
      val str = template(options.fileName, classDeclarations)
      logger.warn("\n=============Generated=============\n$str\n===================================")
      it.write(str)
    }
    finish = true
    return emptyList()
  }
}

private fun template(
  className: String,
  classDeclarations: List<KSClassDeclaration>,
) = """
  package com.g985892345.codegen
  
  import kotlin.reflect.KClass
  
  object $className {
    fun getAllKClass(): List<KClass<*>> = listOf<KClass<*>>(
      ${classDeclarations.joinToString { "${it.qualifiedName!!.asString()}::class" }}
    )
  }
""".trimIndent()
# ksp-issue-the-startup-module-retrigger
An issue regarding the re-triggering of KSP compilation for the startup module, 
but with an empty input file.

[issue](https://github.com/google/ksp/issues/1694)

## Requirement
Utilizing KSP to locate the annotation named `TestAnnotation` and 
generate the class named `StartupKsp` for collecting `KClass`.

I have implemented a startup module `module-startup` and 
a regular module `module-other`. The `module-startup` uses `compile-ksp` to 
process the `TestAnnotation`, and depends on the `module-other`.

## Steps
1. Initially, run the main function in the `module-startup` normally. The first run result is correct.
2. Then, make an unrelated change to a class named `OtherClass` in the `module-other` (just adding an empty line is sufficient).
3. Run the main function in the `module-startup` again. At this point, an exception is thrown, 
indicating that the `StartupKsp` has not been generated correctly.

## Log
The log for the first execution of the `kspKotlin` is as follows:
```
> Task :module-startup:kspKotlin
w: [ksp] resolver.getNewFiles() = [Main.kt, TestAnnotation.kt, TestClass.kt]
w: [ksp] classDeclarations = [TestClass]
w: [ksp] 
=============Generated=============
package com.g985892345.codegen

import kotlin.reflect.KClass

object StartupKsp {
  fun getAllKClass(): List<KClass<*>> = listOf<KClass<*>>(
    com.g985892345.startup.TestClass::class
  )
}
===================================
w: [ksp] resolver.getNewFiles() = [StartupKsp.kt]
```
The log for the second execution of the `kspKotlin` is as follows:
```
> Task :module-startup:kspKotlin
w: [ksp] resolver.getNewFiles() = []
w: [ksp] classDeclarations = []
w: [ksp] 
=============Generated=============
package com.g985892345.codegen

import kotlin.reflect.KClass

object StartupKsp {
  fun getAllKClass(): List<KClass<*>> = listOf<KClass<*>>(
    
  )
}
===================================
w: [ksp] resolver.getNewFiles() = [StartupKsp.kt]
```
It can be observed that after modifying `OtherClass` in `module-other`, 
the `kspKotlin` of `module-startup` is unexpectedly triggered. 

However, the result of `resolver.getNewFiles()` is empty, 
leading to the non-generation of `com.g985892345.startup.TestClass::class`.

I don't understand why modifying an unrelated module triggers KSP compilation for 
the startup module with an empty input file?
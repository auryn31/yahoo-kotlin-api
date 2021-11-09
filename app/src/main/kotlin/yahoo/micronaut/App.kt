/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package yahoo.micronaut

import io.micronaut.runtime.Micronaut
import mu.KotlinLogging

val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    Micronaut.build()
            .args(*args)
            .packages("example.micronaut")
            .start()
}

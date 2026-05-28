package md.utm.cloudapp.rest

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class MainController {

    private val logger = LoggerFactory.getLogger(MainController::class.java)

    @GetMapping("/")
    fun main(): String {

        logger.info("Main endpoint called")

        return "Hello World!"
    }

    @GetMapping("/logs")
    fun logs(): String {

        logger.info("INFO log example")

        logger.warn("WARNING log example")

        logger.error("ERROR log example")

        return "Logs generated"
    }

    @GetMapping("/slow")
    fun slow(): String {

        logger.info("Slow endpoint called")

        Thread.sleep(5000)

        return "Slow response completed"
    }

    @GetMapping("/cpu")
    fun cpu(): String {

        logger.info("CPU intensive endpoint called")

        val start = System.currentTimeMillis()

        while (System.currentTimeMillis() - start < 10000) {
            Math.sqrt(Random.nextDouble())
        }

        return "CPU load generated"
    }

    @GetMapping("/error")
    fun error(): String {

        logger.error("Simulated application error")

        throw RuntimeException("Simulated failure")
    }
}
package md.utm.cloudapp.rest

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import kotlin.random.Random

@RestController
class MainController {

    private val logger = LoggerFactory.getLogger(MainController::class.java)

    @GetMapping("/logs")
    fun logs(): Map<String, Any> {

        logger.info("INFO log example")
        logger.warn("WARNING log example")
        logger.error("ERROR log example")

        return mapOf(
            "status" to "success",
            "message" to "INFO, WARNING and ERROR logs generated successfully",
            "timestamp" to LocalDateTime.now().toString()
        )
    }

    @GetMapping("/slow")
    fun slow(): Map<String, Any> {

        logger.info("Slow endpoint called")

        val start = System.currentTimeMillis()

        Thread.sleep(5000)

        val duration = System.currentTimeMillis() - start

        return mapOf(
            "status" to "success",
            "message" to "Slow request completed",
            "duration_ms" to duration,
            "timestamp" to LocalDateTime.now().toString()
        )
    }

    @GetMapping("/cpu")
    fun cpu(): Map<String, Any> {

        logger.info("CPU intensive endpoint called")

        val start = System.currentTimeMillis()

        while (System.currentTimeMillis() - start < 10000) {
            Math.sqrt(Random.nextDouble())
        }

        return mapOf(
            "status" to "success",
            "message" to "CPU intensive task completed successfully",
            "duration_seconds" to 10,
            "timestamp" to LocalDateTime.now().toString()
        )
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun error(): Map<String, Any> {

        logger.error("Simulated application error")

        return mapOf(
            "status" to "error",
            "message" to "Simulated cloud-native failure",
            "timestamp" to LocalDateTime.now().toString()
        )
    }

    @GetMapping("/info")
    fun info(): Map<String, Any> {

        return mapOf(
            "application" to "Cloud Native Demo",
            "version" to "1.0",
            "environment" to "Kubernetes",
            "pod_name" to (System.getenv("HOSTNAME") ?: "unknown"),
            "timestamp" to LocalDateTime.now().toString()
        )
    }

    @Entity
    data class TestEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val name: String
    )   
}
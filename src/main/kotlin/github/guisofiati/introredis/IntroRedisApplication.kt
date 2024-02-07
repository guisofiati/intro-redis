package github.guisofiati.introredis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class IntroRedisApplication

fun main(args: Array<String>) {
	runApplication<IntroRedisApplication>(*args)
}

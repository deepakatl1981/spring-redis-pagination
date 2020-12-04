package com.elk.app

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ElkApplication

fun main(args: Array<String>) {
    runApplication<ElkApplication>(*args){
        setBannerMode(Banner.Mode.OFF)
    }
}

package com.elk.app.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

import com.elk.app.model.Product
import com.elk.app.service.PagebleProductService


private val logger = KotlinLogging.logger {}

/**
 * Product Creation and fetch Controller. Product fetch support pagination.
 */
@RestController
@RequestMapping("/api/v1/elk/products")
class ProductController (val productServive: PagebleProductService){

    @GetMapping("/")
    fun getProducts(@RequestParam  limit:Int, @RequestParam  pageNumber:Int): List<Product?>?{
        logger.info { " $limit, $pageNumber" }
        return productServive.getProducts(limit, pageNumber)
    }

    @PostMapping("/")
    fun createProduct(@RequestBody product:Product) : Product=productServive.createProduct(product)



}
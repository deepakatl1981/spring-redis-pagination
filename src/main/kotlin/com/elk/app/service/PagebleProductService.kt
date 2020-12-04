package com.elk.app.service

import com.elk.app.model.Product
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class PagebleProductService(val redisTemplate : RedisTemplate<String, Any>) {

    fun createProduct(product: Product): Product {
        val myKey = "ProductKey"
        redisTemplate.opsForZSet().add(myKey + "zset", product.code, System.currentTimeMillis().toDouble())
        redisTemplate.opsForHash<String, Product>().put(myKey + "hash", product.code, product)
        return product
    }

    fun getProducts(limit: Int, pageNumber: Int): List<Product?>? {
        val myKey = "ProductKey"

        // Get one page from the hash
        val pageHashFields: Set<Any>? = redisTemplate.opsForZSet().range(myKey + "zset", ((pageNumber - 1) * limit).toLong(), (pageNumber * limit - 1).toLong())
        val page: MutableList<Product?> = LinkedList()
        for (hashFieldKey in pageHashFields!!) {
            val hashFieldValue = redisTemplate.opsForHash<Any, Any>()[myKey + "hash", (hashFieldKey as String)] as Product?
            page.add(hashFieldValue)
        }
        return page
    }
}
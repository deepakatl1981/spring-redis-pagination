package com.elk.app.model

import org.springframework.data.redis.core.RedisHash
import lombok.Data
import java.io.Serializable


@RedisHash("Products")
@Data
class Product(val code:String, val name : String, val description : String, val category: String, val mrp: Double) :Serializable{

}
package com.mahima.animestreamingapp

import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.Product
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.model.UserModel
import com.mahima.animestreamingapp.repository.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class Test {
    @Test
//    test for login
    fun checkLogin() = runBlocking {
        val userRepository = UserRepository()
        val response = userRepository.userLogin("owner1@gmail.com","owner")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
//    failed test case for login
    fun checkLoginFailed() = runBlocking {
        val userRepository = UserRepository()
        val response = userRepository.userLogin("owner@gmail.com", "owner")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

//      test case for sign up
    @Test
    fun checkRegister() = runBlocking {
        val user = UserModel(fullName = "test",email = "test@test.com",password = "test")
        val userRepository = UserRepository()
        val response = userRepository.userRegister(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

//    failed test case for sign up
    @Test
    fun checkRegisterFailed() = runBlocking {
        val user = UserModel(fullName = "",email = "test@test.com",password = "test")
        val userRepository = UserRepository()
        val response = userRepository.userRegister(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

//    check add to cart
    @Test
    fun checkAddToCart() = runBlocking {
        val userRepository = UserRepository()
        val cartRepository = ShoppingCartRepository()

        ServiceBuilder.token = "Bearer "+ userRepository.userLogin("owner1@gmail.com","owner").token
        val expectedResult = true
        val actualResult = cartRepository.addProducts(
            userId = userRepository.userLogin("owner1@gmail.com","owner").userId!!,
            productId = "612b65a1183934106d04b3f7",
            1
        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }
//    remove from cart
    @Test
    fun deleteAddToCart() = runBlocking {
        val userRepository = UserRepository()
        val cartRepository = ShoppingCartRepository()

        ServiceBuilder.token = "Bearer "+ userRepository.userLogin("owner1@gmail.com","owner").token
        val expectedResult = true
        val actualResult = cartRepository.deleteItem(
            id = userRepository.userLogin("owner1@gmail.com","owner").userId!!,
            itemid = "612b65a1183934106d04b3f7"

        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }
//    add to favorites
    @Test
    fun checkAddToFavorites() = runBlocking {
        val userRepository = UserRepository()
        val favoritesRepository = FavoritesRepository()

        ServiceBuilder.token = "Bearer "+ userRepository.userLogin("owner1@gmail.com","owner").token
        val expectedResult = true
        val actualResult = favoritesRepository.addToFavorites(
            id = userRepository.userLogin("owner1@gmail.com","owner").userId!!,
            productId = "612c85ed97f1101dd56d7e3c"
        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }
//    delete item from favorites
    @Test
    fun deleteAddToFavorites() = runBlocking {
        val userRepository = UserRepository()
        val favoritesRepository = FavoritesRepository()

        ServiceBuilder.token = "Bearer "+ userRepository.userLogin("owner1@gmail.com","owner").token
        val expectedResult = true
        val actualResult = favoritesRepository.deleteFavorites(
            id = userRepository.userLogin("owner1@gmail.com","owner").userId!!,
            productId = "612c85ed97f1101dd56d7e3c"
        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }
//    test case for order
//    delete item from favorites
    @Test
    fun checkMyOrder() = runBlocking {
        val userRepository = UserRepository()
        val orderRepository = OrderRespository()

        ServiceBuilder.token = "Bearer "+ userRepository.userLogin("owner1@gmail.com","owner").token
        val expectedResult = true
        val actualResult = orderRepository.createOrder(
            id = userRepository.userLogin("owner1@gmail.com","owner").userId!!,
            payment = 123412341234,
            delivery_address = "home"
        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }

    //    show item from myorder
    @Test
    fun showMyOrder() = runBlocking {
        val userRepository = UserRepository()
        val orderRepository = OrderRespository()

        ServiceBuilder.token = "Bearer "+ userRepository.userLogin("owner1@gmail.com","owner").token
        val expectedResult = true
        val actualResult = orderRepository.showOrder(
            id = userRepository.userLogin("owner1@gmail.com","owner").userId!!

        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun showMyhires() = runBlocking {
        val orderRepository = HireRepository()
        val userRepository = UserRepository()

        val expectedResult = true
        val actualResult = orderRepository.shoeHires(
            id=userRepository.userLogin("owner1@gmail.com","owner").userId!!
        ).success
        Assert.assertEquals(expectedResult,actualResult)
    }
}



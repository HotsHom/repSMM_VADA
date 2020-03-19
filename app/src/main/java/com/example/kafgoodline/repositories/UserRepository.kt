package com.example.kafgoodline.repositories

class UserRepository {
    fun login(subscriber: (String) -> Unit, login: String, pass: String) {
        subscriber.invoke("$login : $pass")
    }
}
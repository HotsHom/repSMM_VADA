package com.example.kafgoodline.repositories

class UserRepository {
    fun login(subscriber: (String) -> Unit, login: String, pass: String) {
        subscriber.invoke("$login : $pass")
    }
    fun registration(subscriber: (String) -> Unit, login: String, pass1: String, pass2: String) {
        subscriber.invoke("$login : $pass1 : $pass2")
    }
}
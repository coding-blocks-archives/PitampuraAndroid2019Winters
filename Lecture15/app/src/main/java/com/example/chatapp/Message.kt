package com.example.chatapp

data class Message(val name: String, val msg: String) {
    constructor() : this("", "")
    constructor(name: String):this(name,"")
}
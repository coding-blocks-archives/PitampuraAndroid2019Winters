package com.puldroid.chatapp

data class Messages(
    val from:String,
    val time:Long,
    val msg:String
){
    constructor():this("",0L,"")
}
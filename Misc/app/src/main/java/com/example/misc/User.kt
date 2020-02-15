package com.example.misc

object UserObject {
    val friends = listOf<String>("Rahul","Rahul","Rahul","Rahul","Rahul","Rahul","Rahul")

    fun userPosts():Int{
        return 10*friends.size
    }
}

class User {
    val friends = listOf<String>("Rahul","Rahul","Rahul","Rahul","Rahul","Rahul","Rahul")

    companion object{

         fun userPosts():Int{
            return 10
        }
         val e = Event("Pulkit")
    }


}


data class Event @JvmOverloads constructor(val name:String,val date:Long = 0L)
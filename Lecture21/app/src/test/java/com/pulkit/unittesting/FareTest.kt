package com.pulkit.unittesting

import junit.framework.Assert.assertEquals
import org.junit.Test

class FareTest {

    @Test
    fun calFare_0Km_0Min(){
        assertEquals(0,MainActivity.calcFare(0,0))
    }

    @Test
    fun calFare_5Km_25Min(){
        assertEquals(80,MainActivity.calcFare(5,25))
    }


    @Test
    fun calFare_50Km_125Min(){
        assertEquals(505,MainActivity.calcFare(50,125))
    }


    @Test
    fun calFare_125Km_1025Min(){
        assertEquals(2680,MainActivity.calcFare(125,1025))
    }
}
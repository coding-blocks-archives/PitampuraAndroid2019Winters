package com.codingblocks.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var player = false
    override fun onClick(view: View) {
        Log.i("CLICK", "clicked id : ${view.id} ")
        player = !player
        when (view.id) {
            R.id.button -> {
                updateValue(row = 0, col = 0)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1)

            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2)

            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0)

            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1)

            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2)

            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0)

            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1)

            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2)
            }
        }
        checkWinner()

    }

    private fun checkWinner() {
        //For First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if(boardStatus[0][0] == 1){
                //X is the winner
            }else if(boardStatus[0][0] == 0){
                //0 is the winner
            }
        }

        //for Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if(boardStatus[0][2] == 1){
                //X is the winner
            }else if(boardStatus[0][2] == 0){
                //0 is the winner
            }
        }

        //for vertical column
        for(i  in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if(boardStatus[0][i] == 1){
                    //X is the winner
                }else if(boardStatus[0][i] == 0){
                    //0 is the winner
                }
            }
        }

        //for horizontal rows
        for(i  in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if(boardStatus[i][0] == 1){
                    //X is the winner
                }else if(boardStatus[i][0] == 0){
                    //0 is the winner
                }
            }
        }
    }

    private fun updateValue(row: Int, col: Int) {

        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        baord[row][col].text = text
        baord[row][col].isEnabled = false
        boardStatus[row][col] = value

    }

    var boardStatus = Array(3) { IntArray(3) }
    val baord by lazy {
        arrayOf(
            arrayOf(button, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for (i in baord) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        intializeBoard()
    }

    private fun intializeBoard() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] == -1
            }
        }

        for(i in baord){
            for(button in i){
                button.apply {
                    text = ""
                    isEnabled = true
                }
            }
        }
    }
}

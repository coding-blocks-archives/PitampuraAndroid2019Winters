package com.puldroid.listviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val movies = arrayOf("Iron Man","Thor","Captain America","Doctor Strange","Captain Marvel","Black Panther")
    val year = arrayOf("2008","2010","2011","2016","2019","2018")
    val actors = arrayOf("RDJ","Chris","Chris Evans","Benedict","Brie","Chadwick")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,movies)

        textView.text = movies[0] + "( " + year[0] +" )"
        textView2.text = actors[0]
    }
}

package com.puldroid.listviews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val movies = arrayOf("Iron Man", "Thor", "Captain America", "Doctor Strange", "Captain Marvel", "Black Panther")
    val year = arrayOf("2008", "2010", "2011", "2016", "2019", "2018")
    val actors = arrayOf("RDJ", "Chris", "Chris Evans", "Benedict", "Brie", "Chadwick")
    val images = arrayOf(R.drawable.ironman,
            R.drawable.thor,
            R.drawable.captain,
            R.drawable.strange,
            R.drawable.marvel,
            R.drawable.panther
    )
    val list = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..150) {
            val random = Random().nextInt(5)
            val movie = Movie(
                    movies[random],
                    year[random],
                    actors[random],
                    images[random]
            )
            list.add(movie)
            Log.i("Movie Info", "${movie.toString()}")
        }
        listView.adapter = MovieAdapter(list)

        textView.text = movies[0] + "( " + year[0] + " )"
        textView2.text = actors[0]
    }
}

data class Movie(
        val name: String,
        val year: String,
        val actor: String,
        val image: Int
)

class MovieAdapter(val movies: ArrayList<Movie>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        view.text1.text = movies[position].name + "( " + movies[position].year + " )"
        view.textView2.text = movies[position].year
        view.imageView.setImageResource(movies[position].image)

        return view
    }

    override fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount() = movies.size

}

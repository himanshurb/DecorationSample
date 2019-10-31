package com.example.decorationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = getItems()

        val adapter = ListAdapter(items)

        recyclerView.addItemDecoration(iOSDecoration(this.baseContext, items))
        recyclerView.adapter = adapter
    }

    private fun getItems(): List<ListItem> {
        val list = ArrayList<ListItem>()

        list.add(ListItem("Title 1", "Subtitle 1", "Section 1"))
        list.add(ListItem("Title 2", "Subtitle 2"))
        list.add(ListItem("Title 3", "Subtitle 3"))
        list.add(ListItem("Title 4", "Subtitle 4"))

        list.add(ListItem("Title 5", "Subtitle 5", "Section 2"))
        list.add(ListItem("Title 6", "Subtitle 6"))
        list.add(ListItem("Title 7", "Subtitle 7"))
        list.add(ListItem("Title 8", "Subtitle 8"))

        list.add(ListItem("Title 9", "Subtitle 9", "Section 3"))
        list.add(ListItem("Title 10", "Subtitle 10"))
        list.add(ListItem("Title 11", "Subtitle 11"))
        list.add(ListItem("Title 12", "Subtitle 12"))

        return list
    }
}

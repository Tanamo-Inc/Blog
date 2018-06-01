package com.tanamo.blog.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.tanamo.blog.R
import com.tanamo.blog.mod.Blog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_item.view.*

class MainActivity : AppCompatActivity() {


    private lateinit var dref: DatabaseReference
    private lateinit var adapter: FirebaseRecyclerAdapter<Blog, MainActivity.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {

        dref = FirebaseDatabase.getInstance().reference.child(getString(R.string.Blog))
        dref.keepSynced(true)
        val quer = dref.orderByKey()

        recyclero.hasFixedSize()
        recyclero.layoutManager = LinearLayoutManager(this)

        val fro = FirebaseRecyclerOptions.Builder<Blog>().setQuery(quer, Blog::class.java).build()

        adapter = object : FirebaseRecyclerAdapter<Blog, MainActivity.ViewHolder>(fro) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivity.ViewHolder {

                val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)

                return MainActivity.ViewHolder(view)
            }

            override fun onBindViewHolder(holder: MainActivity.ViewHolder, position: Int, bog: Blog) {

                holder.setTitle(bog.title)
                holder.setInfo(bog.info)
                holder.setImage(baseContext, bog.image)

                holder.iView.setOnClickListener {
                    val url = bog.url
                    val intent = Intent(applicationContext, Details::class.java)
                    intent.putExtra(getString(R.string.tan_key), url)
                    startActivity(intent)
                }

                anima.cancelAnimation()
                anima.visibility = View.GONE


            }


        }

        recyclero.adapter = adapter

    }

    public override fun onStart() {
        super.onStart()
        adapter.startListening()


        if (anima != null)
            anima.visibility = View.VISIBLE

    }

    public override fun onStop() {
        super.onStop()
        adapter.stopListening()


    }

    class ViewHolder(val iView: View) : RecyclerView.ViewHolder(iView) {

        fun setTitle(title: String) {
            iView.title.text = title
        }

        fun setInfo(info: String) {
            iView.info.text = info
        }

        fun setImage(ctx: Context, image: String) {
            Picasso.with(ctx).load(image).placeholder(R.drawable.ic_launcher).into(iView.image)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.settings) {
            About(this@MainActivity).show()
        }


        return super.onOptionsItemSelected(item)
    }


}

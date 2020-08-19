package com.example.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.listDemo.ViewProducer
import com.example.listDemo.ViewProducer.DefaultEmptyViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: MutableList<SampleGroupBean> = ArrayList(10)
        for (i in 0..19) {
            val childList: MutableList<SampleChildBean> = ArrayList(i)
            for (j in 0 until i) {
                childList.add(SampleChildBean("child $i"))
            }
            list.add(SampleGroupBean(childList, "group $i"))
        }
        recList.layoutManager = LinearLayoutManager(this)
        val adapter = SampleAdapter(list)
        adapter.setEmptyViewProducer(object : ViewProducer {
            override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
                return DefaultEmptyViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.empty, parent, false)
                )
            }

            override fun onBindViewHolder(holder: ViewHolder) {}
        })
//        adapter.setHeaderViewProducer(object : ViewProducer {
//            override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
//                return DefaultEmptyViewHolder(
//                    LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
//                )
//            }
//
//            override fun onBindViewHolder(holder: ViewHolder) {}
//        }, false)
        recList.adapter = adapter
    }
}
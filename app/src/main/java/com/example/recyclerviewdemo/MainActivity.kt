package com.example.recyclerviewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerviewdemo.data.Item
import java.util.*
import kotlin.collections.ArrayList


val mAdapter = MyAdapter()
private lateinit var myDate: ArrayList<Item>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycleView = findViewById(R.id.recycler_view)as RecyclerView
        myDate = getItemList()
        mAdapter.updateList(myDate)     //傳入資料
        recycleView.adapter = mAdapter

        //實做　ｓｉｍｐｌｅ　ｃａｌｌｂａｃｋ

//        val mIth = ItemTouchHelper(
//            object : ItemTouchHelper.SimpleCallback(
//                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
//                ItemTouchHelper.LEFT
//            ) {
//                override fun onMove(
//                    recyclerView: RecyclerView,
//                    viewHolder: ViewHolder, target: ViewHolder
//                ): Boolean {
//                    val fromPos = viewHolder.adapterPosition
//                    val toPos = target.adapterPosition
//                    // move item in `fromPos` to `toPos` in adapter.
//                    return true // true if moved, false otherwise
//                }
//
//                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
//                    // remove from adapter
//                }
//            })


        val mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder,
                    target: ViewHolder
                ): Boolean {
                    val oldPosition = viewHolder.adapterPosition
                    val newPosition = target.adapterPosition
                    Collections.swap(myDate,oldPosition,newPosition)
                    mAdapter.notifyItemMoved(oldPosition,newPosition)
                    return true
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                   myDate.removeAt(position)
                    mAdapter.notifyItemRemoved(position)
                }

            }
        )
        mIth.attachToRecyclerView(recycleView)
    }

    fun getItemList (): ArrayList<Item> {
        val myItemList = arrayListOf<Item>()
        //生成資料
        for(i in 0..20) {
            myItemList.add(Item("test"+i.toString(), R.drawable.bylobster_logo))
        }
            return myItemList;
    }
}
package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.data.Item
import com.example.recyclerviewdemo.touch.ITHelperInterface
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter : RecyclerView.Adapter<MyAdapter.mViewHolder>() {

    lateinit var unAssignList :ArrayList<Item>

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class mViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        //把layout檔的元件們拉進來，指派給當地變數
        val icon = itemView.findViewById<ImageView>(R.id.imgv)
        val name = itemView.findViewById<TextView>(R.id.tv_name)

        fun bind(item: Item){
            //綁定當地變數與dataModel中的每個值
            icon.setImageResource(item.img)
            name.text = item.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        //載入項目模板
        val inflater = LayoutInflater.from(parent.context)
        val example = inflater.inflate(R.layout.item_example, parent, false)
        return mViewHolder(example)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        //呼叫上面的bind方法來綁定資料
        holder.bind(unAssignList[position])
    }

    override fun getItemCount() =  unAssignList.size
    //更新資料用
    fun updateList( list:ArrayList<Item>){
        unAssignList = list
    }

}
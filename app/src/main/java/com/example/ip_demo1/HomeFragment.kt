package com.example.ip_demo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

   private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var titleList:ArrayList<String>
    lateinit var dateList:ArrayList<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       /* //val view = inflater.inflate(R.layout.fragment_home, container, false)
        titleList= arrayListOf(
            "asd",
            "wer",
            "rty"
        )

        dateList= arrayListOf(
            "02/04/2024",
            "02/04/2024",
            "02/04/2024"
        )

        recyclerView.findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<DataClass>()
        getData()*/

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getData(){
        for(i in titleList.indices){
            val dataClass = DataClass(titleList[i],dateList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter=AdapterClass(dataList)
    }

}
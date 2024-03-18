package com.example.ip_demo1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import androidx.cardview.widget.CardView
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val expandDoctorButton=view.findViewById<ImageView>(R.id.imageView_btExpand)
        val doctorInfoRow1a = view.findViewById<TableRow>(R.id.doctor_info_row1a)
        val doctorInfoRow1b = view.findViewById<TableRow>(R.id.doctor_info_row1b)
        val doctorInfoRow2a = view.findViewById<TableRow>(R.id.doctor_info_row2a)
        val doctorInfoRow2b = view.findViewById<TableRow>(R.id.doctor_info_row2b)
        val doctorInfoRow3a = view.findViewById<TableRow>(R.id.doctor_info_row3a)
        val doctorInfoRow3b = view.findViewById<TableRow>(R.id.doctor_info_row3b)

        expandDoctorButton.setOnClickListener {
            if (doctorInfoRow1a.visibility == View.GONE) {
                doctorInfoRow1a.visibility = View.VISIBLE
                doctorInfoRow1b.visibility = View.VISIBLE
                doctorInfoRow2a.visibility = View.VISIBLE
                doctorInfoRow2b.visibility = View.VISIBLE
                doctorInfoRow3a.visibility = View.VISIBLE
                doctorInfoRow3b.visibility = View.VISIBLE
                expandDoctorButton.setImageResource(R.drawable.minimize)
            } else {
                doctorInfoRow1a.visibility = View.GONE
                doctorInfoRow1b.visibility = View.GONE
                doctorInfoRow2a.visibility = View.GONE
                doctorInfoRow2b.visibility = View.GONE
                doctorInfoRow3a.visibility = View.GONE
                doctorInfoRow3b.visibility = View.GONE
                expandDoctorButton.setImageResource(R.drawable.expand)
            }
        }

        val to_fisa_medicala = view.findViewById<CardView>(R.id.cardView_fisa_medicala)
        val to_alarm = view.findViewById<CardView>(R.id.cardView_alarm)

        to_fisa_medicala.setOnClickListener {

            val intent = Intent(requireContext(), FisaMedicalaActivity::class.java)
            startActivity(intent)
        }

        to_alarm.setOnClickListener {

            val intent = Intent(requireContext(), AlarmsActivity::class.java)
            startActivity(intent)
        }


        return view
    }

    private fun getData(){
        for(i in titleList.indices){
            val dataClass = DataClass(titleList[i],dateList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter=AdapterClass(dataList)
    }

}
package com.mahima.animestreamingapp.ui.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForMyOrders
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.OrderEntity
import com.mahima.animestreamingapp.entity.ProductOrder
import com.mahima.animestreamingapp.repository.OrderRespository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderDetailActivity : AppCompatActivity() {
    private var data: ArrayList<OrderEntity> ?= null
    private lateinit var recylerviewmyorders: RecyclerView
    companion object{
        var orderListForRecyclerView:ArrayList<OrderEntity> = ArrayList<OrderEntity>()
        var ordertList : List<ProductOrder> ?= null

    }
    private lateinit var showitem : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

//        initializing variables
        recylerviewmyorders = findViewById(R.id.recylerviewmyorder)

        showMyOrder()
//        recyler view
        val adapter= adapterForMyOrders(this,orderListForRecyclerView)
        recylerviewmyorders.layoutManager= LinearLayoutManager(this)
        recylerviewmyorders.adapter = adapter
        orderListForRecyclerView.clear()
//        adapter.notifyDataSetChanged()

//        show item text view
        showitem = findViewById(R.id.showitem)

    }

    private fun showMyOrder(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val repository = OrderRespository()
                val response = repository.showOrder(ServiceBuilder.userId!!)
                data = response.order!!
                val length = data!!.size
//                ordertList =data!!.product

                if(response.success ==true){
                    if(data!=null){
                        for(i in data!!) {
                            ordertList = i.product
                            orderListForRecyclerView.add(
                                OrderEntity(
                                    userId = i.userId,
                                    delivery_address = i.delivery_address,
                                    bill = i.bill,
                                    product = i.product,
                                    date_added = i.date_added
                                )
                            )
                        }
                    }
                    withContext(Dispatchers.Main){
                        showitem.text = "Showing items : " + " " + length + "of " + " " + length
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    showitem.text = "No items to show"
//                    Toast.makeText(requireContext(),ex.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
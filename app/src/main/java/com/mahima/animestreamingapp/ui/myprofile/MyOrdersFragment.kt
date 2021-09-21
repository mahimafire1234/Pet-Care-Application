package com.mahima.animestreamingapp.ui.myprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.adapter.adapterForMyOrders
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.entity.OrderEntity
import com.mahima.animestreamingapp.entity.Product
import com.mahima.animestreamingapp.entity.ProductOrder
import com.mahima.animestreamingapp.entity.ShoppingCartEntity
import com.mahima.animestreamingapp.repository.OrderRespository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyOrdersFragment : Fragment() {
    private var data: ArrayList<OrderEntity> ?= null
    private lateinit var recylerviewmyorders:RecyclerView
    companion object{
        var orderListForRecyclerView:ArrayList<OrderEntity> = ArrayList<OrderEntity>()
        var ordertList : List<ProductOrder> ?= null

    }
    private lateinit var showitem : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_orders, container, false)

//        initializing variables
        recylerviewmyorders = view.findViewById(R.id.recylerviewmyorders)

        showMyOrder()
//        recyler view
//        val adapter= adapterForMyOrders(requireContext(),orderListForRecyclerView)
//        recylerviewmyorders.layoutManager= LinearLayoutManager(requireContext())
//        recylerviewmyorders.adapter = adapter
//        orderListForRecyclerView.clear()
//        adapter.notifyDataSetChanged()

//        show item text view
        showitem = view.findViewById(R.id.showitem)


        return view
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
                        val adapter= adapterForMyOrders(requireContext(),orderListForRecyclerView)
                        recylerviewmyorders.layoutManager= LinearLayoutManager(requireContext())
                        recylerviewmyorders.adapter = adapter
                        orderListForRecyclerView.clear()
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
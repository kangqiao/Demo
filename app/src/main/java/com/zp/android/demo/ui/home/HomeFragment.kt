package com.zp.android.demo.ui.home

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.zp.android.arch.BaseLazyFragment
import com.zp.android.demo.AIDLService
import com.zp.android.demo.IPerson
import com.zp.android.demo.R
import com.zp.android.demo.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseLazyFragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var iPerson: IPerson? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun initView(view: View) {
        tvBindService.setOnClickListener {
            val intent = Intent(context, AIDLService::class.java)
            activity?.bindService(intent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName, service: IBinder) {
                    LogUtil.d("onServiceConnected")
                    iPerson = IPerson.Stub.asInterface(service)
                }

                override fun onServiceDisconnected(name: ComponentName) {
                    LogUtil.d("onServiceDisconnected")
                }
            }, BIND_AUTO_CREATE)
        }

        tvSetName.setOnClickListener {
            try {
                iPerson?.name = etName.text.toString().trim()
            } catch (e: RemoteException) {
                e.printStackTrace()
                Toast.makeText(context, "setName error=$e", Toast.LENGTH_SHORT).show()
            }
        }

        tvGetName.setOnClickListener {
            try {
                val name = iPerson?.name ?: "null"
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
                LogUtil.d("getName =>$name")
            } catch (e: RemoteException) {
                e.printStackTrace()
                Toast.makeText(context, "getName error=$e", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun lazyInit() {
    }

}
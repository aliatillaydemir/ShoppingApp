package com.ayd.shoppingapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.shoppingapp.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()               //job açıyorum main'in üzerinde. splash screenim için.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launch {
            delay(100)                      //2 saniye splash screen ekranda duracak.
            withContext(Dispatchers.Main){
                if(onBoardingFinished()){             //eğer onbard ekranlarını bitirdiysek burası çalışır ve bir daha gözükmezler.
                    //findNavController().navigate(R.id.action_splashFragment_to_productsFragment)
                    findNavController().navigate(R.id.action_splashFragment_to_authPagerFragment)
                }else{                                // uygulama ilk kez yüklendiyse ya da onbard ekranlar tamamlanmadıysa her açılışta viewpager açılır.
                    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                }
            }
        }

    }

    private fun onBoardingFinished(): Boolean{  //onboard'ı bitirdik mi kontrolünü cihazda lokal olarak shared preference ile tutuyorum.
        val sharedPref = requireActivity().getSharedPreferences("ONBOARD", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("FINISHED", false)
    }




}
package pl.lenarczyk.rafal.getmobiledataenableddemo

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log

const val TAG = "MobileDataStatus"

class MobileDataStatusProvider(appContext: Context) {

    private val telephonyManager = appContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    private val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun getMobileDataStatus(): String {
        val mobileDataEnabled =
                when {
                    Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP -> "CM#getMobileDataEnabled=${connectivityManager.getMobileDataEnabledReflection()}"
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> "TM#isDataEnabled=${telephonyManager.isDataEnabled}"
                    else -> "TM#getDataEnabled=${telephonyManager.getDataEnabledReflection()}"
                }
        Log.d(TAG, mobileDataEnabled)
        return mobileDataEnabled
        /*
        val dataEnabled = "TM#getDataEnabled ${telephonyManager.getDataEnabledReflection()}"
        val dataActivity = "TM#getDataActivity ${telephonyManager.dataActivity}"
        val dataState = "TM#getDataState ${telephonyManager.dataState}"
        val dataConnectivityPossible = "TM#isDataConnectivityPossible ${telephonyManager.isDataConnectivityPossibleExt()}"

        Log.d(TAG, "$dataActivity - $dataState - $dataConnectivityPossible")

        val logCM = ConnectivityManager::class.java.declaredMethods.fold("Connectivity Manager") {
            total, next -> total + " ${next.name}"
        }
        Log.d(TAG, logCM)

        val mobileDataEnabled = "CM#getMobileDataEnabled=${connectivityManager.getMobileDataEnabledReflection()}"

        Log.d(TAG, "$mobileDataEnabled")

        val logTM = TelephonyManager::class.java.declaredMethods.fold("Telephony Manager ") {
            total, next -> total + " ${next.name}"
        }
        Log.d(TAG, logTM)*/


    }

    fun TelephonyManager.getDataEnabledReflection(): Boolean {
        val name = "getDataEnabled"
        val method = javaClass.getMethod(name)
        return method.invoke(this) as Boolean
    }

    fun ConnectivityManager.getMobileDataEnabledReflection(): Boolean {
        val name = "getMobileDataEnabled"
        val method = javaClass.getMethod(name)
        return method.invoke(this) as Boolean
    }
}
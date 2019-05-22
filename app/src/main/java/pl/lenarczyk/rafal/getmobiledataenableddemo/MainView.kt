package pl.lenarczyk.rafal.getmobiledataenableddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MainView(inflater: LayoutInflater, root: ViewGroup?) {
    val rootView: View = inflater.inflate(R.layout.activity_main, root, false)
    private val listeners = mutableListOf<Listener>()
    private val button: Button = rootView.findViewById(R.id.button)
    private val statusTv: TextView = rootView.findViewById(R.id.status)

    init {
        button.setOnClickListener { listeners.forEach { it.getMobileData() } }
    }

    interface Listener {
        fun getMobileData()
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun setStatus(status: String) {
        statusTv.text = status
    }
}
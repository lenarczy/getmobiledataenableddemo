package pl.lenarczyk.rafal.getmobiledataenableddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), MainView.Listener {

    private var view: MainView by Delegates.notNull()
    private var provider: MobileDataStatusProvider by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MainView(LayoutInflater.from(this), null)
        view.registerListener(this)
        provider = MobileDataStatusProvider(this.applicationContext)
        setContentView(view.rootView)
    }

    override fun onDestroy() {
        view.unregisterListener(this)
        super.onDestroy()
    }

    override fun getMobileData() {
        view.setStatus(provider.getMobileDataStatus())
    }
}

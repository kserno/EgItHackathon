package pandas.com.egithackathon.ar

import android.os.Bundle
import android.view.View
import com.google.ar.sceneform.ux.ArFragment
import uk.co.appoly.arcorelocation.LocationScene
import android.location.Location
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.google.ar.sceneform.rendering.ModelRenderable
import androidx.core.view.accessibility.AccessibilityRecordCompat.setSource
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ViewRenderable
import pandas.com.egithackathon.BaseApplication
import pandas.com.egithackathon.R
import pandas.com.egithackathon.di.ar.ArNavModule
import pandas.com.egithackathon.di.ar.ArNavViewModelFactory
import pandas.com.egithackathon.di.ar.DaggerArNavComponent
import pandas.com.egithackathon.location.LocationProvider
import uk.co.appoly.arcorelocation.LocationMarker
import java.util.concurrent.CompletableFuture
import javax.inject.Inject


/**
 *  Created by filipsollar on 20.10.18
 */
class ArNavFragment: ArFragment(), ArNavView {

    var locationScene: LocationScene? = null

    var renderables: MutableList<ViewRenderable> = ArrayList()

    @Inject
    lateinit var factory: ArNavViewModelFactory

    @Inject
    lateinit var locationProvider: LocationProvider


    lateinit var viewModel: ArNavViewModel

    override fun onResume() {
        super.onResume()
        locationScene?.resume()
    }

    override fun onPause() {
        super.onPause()
        locationScene?.pause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerArNavComponent.builder()
                .arNavModule(ArNavModule(this))
                .mainComponent((activity!!.application as BaseApplication).component)
                .build()
                .inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(ArNavViewModel::class.java)



        for (i in 0 until viewModel.atmList.value!!.size) {
            ViewRenderable.builder()
                    .setView(context, R.layout.renderable_test)
                    .build()
                    .thenAccept { renderables.add(it) }
        }

        locationProvider.location.observe(this, Observer{

            if (renderables.size == viewModel.atmList.value!!.size) {
                viewModel.atmList.value!!.forEachIndexed { index, atmModel ->


                    val tvDistance = renderables[index].view.findViewById<TextView>(R.id.tvDistance)

                    val destLoc = Location("").apply {
                        latitude = atmModel.location.latitude
                        longitude = atmModel.location.longitude
                    }


                    val distance = destLoc.distanceTo(it).toInt()

                    val txDistance = "$distance m"
                    tvDistance.text = txDistance
                }
            }
        })


        arSceneView.scene.addOnUpdateListener {

            if (locationScene == null) {
                if (renderables.size == viewModel.atmList.value!!.size) {
                    locationScene = LocationScene(context, activity, arSceneView)

                    viewModel.atmList.value!!.forEachIndexed { index, atmModel ->
                        val marker = LocationMarker(atmModel.location.longitude, atmModel.location.latitude, getNode(renderables[index]))

                        val tvName = renderables[index].view.findViewById<TextView>(R.id.tvName)

                        tvName.text = atmModel.address

                        locationScene?.mLocationMarkers?.add(marker)
                    }

                }
            } else {
                locationScene!!.processFrame(arSceneView.arFrame)
            }

        }
    }

    fun getNode(renderable: ViewRenderable) : Node {
        val base = Node()
        base.renderable = renderable
        base.setOnTapListener { hitTestResult, motionEvent ->
            Toast.makeText(context, "Touched", Toast.LENGTH_SHORT).show()
        }
        return base
    }


}
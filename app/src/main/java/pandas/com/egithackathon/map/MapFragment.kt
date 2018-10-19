package pandas.com.egithackathon.map

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.*
import pandas.com.egithackathon.BaseFragment
import pandas.com.egithackathon.R
import pandas.com.egithackathon.di.map.DaggerMapComponent
import pandas.com.egithackathon.di.map.MapModule
import pandas.com.egithackathon.di.map.MapViewModelFactory
import javax.inject.Inject
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil


/**
 *  Created by filipsollar on 19.10.18
 */
class MapFragment: BaseFragment(), MapView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    companion object {
        const val MAP_VIEW_BUNDLE_KEY = "MAP_VIEW_BUNDLE_KEY"
    }

    @Inject
    lateinit var factory: MapViewModelFactory

    lateinit var viewModel: MapViewModel

    override val layoutId: Int = R.layout.fragment_map

    var googleMap: GoogleMap? = null


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun bindData(view: View, savedInstanceState: Bundle?) {

        DaggerMapComponent.builder()
                .mainComponent(application!!.component)
                .mapModule(MapModule(this))
                .build()
                .inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MapViewModel::class.java)

        viewModel.location.observe(this, Observer {
            if (mapView != null && it != null) {
                val myLocation = LatLng(it.latitude, it.longitude)

                googleMap?.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
            }
        })

        viewModel.atms.observe(this, Observer { atms ->

            googleMap?.clear()

            atms.forEach {
                val options = MarkerOptions()
                        .position(LatLng(it.location.latitude, it.location.longitude))
                        .title(it.objectId)

                googleMap?.addMarker(options)
            }

        })
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        googleMap?.setMinZoomPreference(12f)
        googleMap?.setOnMarkerClickListener(this)

        val myLocation = LatLng(viewModel.location.value!!.latitude, viewModel.location.value!!.longitude)

        googleMap?.isMyLocationEnabled = true
        googleMap?.clear()
        viewModel.atms.value?.forEach {
            val options = MarkerOptions()
                    .position(LatLng(it.location.latitude, it.location.longitude))
                    .title(it.objectId)

            googleMap?.addMarker(options)
        }


        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
    }

    override fun showPolyLine(polyline: String) {
        googleMap?.addPolyline(PolylineOptions().addAll(PolyUtil.decode(polyline)))
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(marker.position))
        viewModel.onMarkerClick(marker.position.latitude, marker.position.longitude)

        return false
    }
}
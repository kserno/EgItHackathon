package pandas.com.egithackathon

import android.Manifest
import android.location.Location
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter

import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener





/**
 *  Created by filipsollar on 19.10.18
 */
class MainActivity: BaseActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var lastLocation: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val dialogMultiplePermissionsListener = DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(this)
                .withTitle("Camera & location permission")
                .withMessage("Both camera and location permission are needed.")
                .withButtonText("ok")
                .build()



        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                .withListener(dialogMultiplePermissionsListener)
                .check()





    }



}
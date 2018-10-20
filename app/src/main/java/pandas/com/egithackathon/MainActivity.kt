package pandas.com.egithackathon

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import pandas.com.egithackathon.banking.MoneyPicker
import pandas.com.egithackathon.beacons.BeaconManager


/**
 *  Created by filipsollar on 19.10.18
 */
class MainActivity : BaseActivity() {

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

    override fun onStart() {
        super.onStart()

        // Subsribe for beacons
        BeaconManager.start(this)

        BeaconManager.registerOnBankomatFoundListener {
            // We are at a bankomat
            Log.d("TAG", "Sme pri bankomate $it! :D")
            Toast.makeText(this, "Sme pri bankomate $it!", Toast.LENGTH_SHORT).show()

            openMoneyPickerDialog(it)
        }

        BeaconManager.registerOnBankomatLeftListener {
            // We are at a bankomat
            Log.d("TAG", "Odisli sme od bankomatu $it :(")
            Toast.makeText(this, "Odisli sme od bankomatu $it :(", Toast.LENGTH_SHORT).show()

            closeMoneyPickerDialog()
        }
    }

    override fun onStop() {
        // Unsubscribe the beacons
        BeaconManager.stop(this)

        super.onStop()
    }

    lateinit var dialog: MoneyPicker

    private fun openMoneyPickerDialog(bankomatId: String) {
        dialog = MoneyPicker()
        val args = Bundle()
        args.putString("bankomatId", bankomatId)
        dialog.arguments = args
        dialog.show(supportFragmentManager, "NoticeDialogFragment")
    }

    private fun closeMoneyPickerDialog() {
        dialog.dismiss()
    }

}
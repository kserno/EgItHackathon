package pandas.com.egithackathon

import android.Manifest
import android.os.Bundle
import com.karumi.dexter.Dexter
import com.google.android.material.snackbar.Snackbar
import com.google.ar.core.ArCoreApk
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener



/**
 *  Created by filipsollar on 19.10.18
 */
class MainActivity: BaseActivity() {


    override fun onResume() {
        super.onResume()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val snackbarPermissionListener = SnackbarOnDeniedPermissionListener.Builder
                .with(window.decorView, "Camera access is needed to take pictures of your dog")
                .withOpenSettingsButton("Settings")
                .withCallback(object : Snackbar.Callback() {
                    override fun onShown(snackbar: Snackbar?) {
                        // Event handler for when the given Snackbar is visible
                    }

                    override fun onDismissed(snackbar: Snackbar?, event: Int) {
                        // Event handler for when the given Snackbar has been dismissed
                    }
                }).build()

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(snackbarPermissionListener)
                .check()


    }


}
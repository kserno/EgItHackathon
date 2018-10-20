package pandas.com.egithackathon.beacons

import android.app.Activity
import android.util.Log
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.*


object BeaconManager {

    const val TAG = "beaconTAG"

    const val THRESHOLD_METERS = 1
    const val LEAVE_MARGIN_METERS = 2

    private val nearbyBankomatsSet = mutableSetOf<Int>()

    private val onBankomatFoundListeners: MutableList<(Int) -> Unit> = mutableListOf()
    private val onBankomatLeftListeners: MutableList<(Int) -> Unit> = mutableListOf()

    private val messageListener: MessageListener = object : MessageListener() {
        override fun onDistanceChanged(message: Message, distance: Distance) {
            val bankomatId = String(message.content).toInt()

            Log.i(TAG, "Bankomat $bankomatId distance is ${distance.meters}m with ${distance.accuracy}m acc")

            // If distance to beacon is bellow threshold, invoke onBankomatFound
            if (distance.meters < THRESHOLD_METERS && !nearbyBankomatsSet.contains(bankomatId)) {
                Log.i(TAG, "Entering bankomat $bankomatId threshold")

                // Add to nearby list so we won't retrigger
                nearbyBankomatsSet.add(bankomatId)

                // Notify all listeners that a bankomat has been found
                onBankomatFoundListeners.forEach { it.invoke(bankomatId) }
            }

            // Remove bankomat from nearby list if user left
            if (distance.meters > THRESHOLD_METERS + LEAVE_MARGIN_METERS && nearbyBankomatsSet.contains(bankomatId)) {
                Log.i(TAG, "Leaving bankomat $bankomatId threshold")

                nearbyBankomatsSet.remove(bankomatId)

                // Notify all listeners that bankomat left
                onBankomatLeftListeners.forEach { it.invoke(bankomatId) }
            }
        }

        override fun onLost(message: Message) {
            val bankomatId = String(message.content).toInt()

            Log.i(TAG, "Bankomat $bankomatId lost")
        }

        override fun onBleSignalChanged(message: Message, signal: BleSignal) {
            val bankomatId = String(message.content).toInt()

            Log.i(TAG, "Bankomat $bankomatId signal is ${signal.rssi} rssi with ${signal.txPower} tx power")
        }

        override fun onFound(message: Message) {
            // Do something with the message here.
            val bankomatId = String(message.content).toInt()

            Log.i(TAG, "Message found: $message")
            Log.i(TAG, "Message string: $bankomatId")
            Log.i(TAG, "Message namespaced type: ${message.namespace}/${message.type}")

            // Invoke the onBankomatFound with the bankomatId
//            onBankomatFound(bankomatId)
        }
    }


//    val messageFilter = MessageFilter.Builder()
//            .build()

    val options = SubscribeOptions.Builder()
            .setStrategy(Strategy.BLE_ONLY)
//            .setFilter(messageFilter)
            .build()


    fun start(activity: Activity) {
        Log.i(TAG, "Subscribing/Starting")
        Nearby.getMessagesClient(activity).subscribe(messageListener, options)
    }


    fun stop(activity: Activity) {
        Log.i(TAG, "Unsubscribing/Stopping")
        Nearby.getMessagesClient(activity).unsubscribe(messageListener)
    }

    fun registerOnBankomatFoundListener(onBankomatFound: (Int) -> Unit) {
        Log.i(TAG, "Bankomat found listener registered")

        onBankomatFoundListeners.add(onBankomatFound)
    }

    fun registerOnBankomatLeftListener(onBankomatLeft: (Int) -> Unit) {
        Log.i(TAG, "Bankomat left listener registered")

        onBankomatLeftListeners.add(onBankomatLeft)
    }
}
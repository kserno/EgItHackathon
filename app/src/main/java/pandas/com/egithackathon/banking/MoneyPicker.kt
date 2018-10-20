package pandas.com.egithackathon.banking

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.money_picker.view.*
import pandas.com.egithackathon.R

class MoneyPicker : DialogFragment() {

    private val bankomatId: String get() = arguments!!["bankomatId"] as String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Build the dialog and set up the button click handlers
            val builder = AlertDialog.Builder(it)

            val view = LayoutInflater.from(it).inflate(R.layout.money_picker, null)
            view.tvInfo.setText("Choose a value to withdraw from $bankomatId")

            builder.setMessage("ATM Withdrawal")
                    .setView(view)
                    .setNegativeButton("Cancel",
                            DialogInterface.OnClickListener { dialog, id ->
                                // Send the negative button event back to the host activity
                                dismiss()
                            })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}
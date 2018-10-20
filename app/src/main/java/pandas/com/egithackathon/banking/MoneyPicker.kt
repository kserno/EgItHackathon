package pandas.com.egithackathon.banking

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
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

            view.bt10.setOnClickListener {
                onValuePicked(10)
            }
            view.bt20.setOnClickListener {
                onValuePicked(20)
            }
            view.bt30.setOnClickListener {
                onValuePicked(30)
            }
            view.bt50.setOnClickListener {
                onValuePicked(50)
            }
            view.bt100.setOnClickListener {
                onValuePicked(100)
            }
            view.bt200.setOnClickListener {
                onValuePicked(200)
            }
            view.bt500.setOnClickListener {
                onValuePicked(500)
            }


            builder.setMessage("ATM Withdrawal")
                    .setView(view)
//                    .setPositiveButton("Withdraw") { dialog, id ->
//                        Toast.makeText(it, "Succesfully withdrawn ")
//                        dismiss()
//                    }
                    .setNegativeButton("Cancel") { dialog, id ->
                        // Send the negative button event back to the host activity
                        dismiss()
                    }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun onValuePicked(value: Int) {
//        Toast.makeText(activity, "Succesfully withdrawn $value", Toast.LENGTH_LONG).show()

        val successDialog = MoneyWithdrawnSuccesAlert()
        val args = Bundle()
        args.putInt("value", value)
        successDialog.arguments = args
        successDialog.show(fragmentManager, "SuccessWithdrawnFragment")

        dismiss()
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        try {
            val ft = manager?.beginTransaction()
            ft?.add(this, tag)
            ft?.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {

        }

    }
}
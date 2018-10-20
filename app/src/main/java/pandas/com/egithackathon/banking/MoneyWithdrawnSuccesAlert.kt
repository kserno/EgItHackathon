package pandas.com.egithackathon.banking

import android.animation.Animator
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.money_withdrawn_success.view.*
import pandas.com.egithackathon.R

class MoneyWithdrawnSuccesAlert : DialogFragment() {

    private val value: Int get() = arguments!!["value"] as Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Build the dialog and set up the button click handlers
            val builder = AlertDialog.Builder(it)

            val view = LayoutInflater.from(it).inflate(R.layout.money_withdrawn_success, null)
            view.tvTitle.setText("Succesfully withdrawn $value€")
            view.check.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Thread.sleep(3000)
                    dismiss()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            })

            builder
//                    .setMessage("ATM Withdrawal")
                    .setView(view)
//                    .setPositiveButton("Withdraw") { dialog, id ->
//                        Toast.makeText(it, "Succesfully withdrawn ")
//                        dismiss()
//                    }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
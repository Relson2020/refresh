package com.example.refresh

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class Dialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            AlertDialog.Builder(it).setMessage("hey its Alert dialog box")
                .setPositiveButton("ok", DialogInterface.OnClickListener { _, _ ->
                    Toast.makeText(context, "pressed ok", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton("cancel",DialogInterface.OnClickListener{ _, _ ->
                    Toast.makeText(context, "pressed cancel", Toast.LENGTH_SHORT).show()
                }).create()
        }
    }
}
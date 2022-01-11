package com.example.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.refresh.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var mAction : ActionMode? = null

    private lateinit var binding: ActivityMainBinding
    private var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.swipeRefresh.setOnRefreshListener {
            number++
            binding.textView.text = number.toString()
            binding.swipeRefresh.isRefreshing = false

            Snackbar.make(
                this,
                binding.textView,
                "Refreshing the Text $number th time",
                Snackbar.LENGTH_SHORT
            ).apply {
                setAction("Action") {
                    Toast.makeText(
                        applicationContext,
                        "SnackBar action is pressed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.show()

        }

        binding.textView.setOnClickListener {
            Dialog().show(supportFragmentManager,"dialog")
        }
        binding.textView2.setOnLongClickListener (object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
               if(mAction != null){
                   return false
               }
                mAction = startActionMode(actionMode)
                return true
            }
        })
    }
    private val actionMode =  object :ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
           menuInflater.inflate(R.menu.menu,menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when(item?.itemId){
                R.id.item1 -> {Toast.makeText(applicationContext,"delete",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item2 -> {Toast.makeText(applicationContext,"Share",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            mAction = null
        }

    }

    fun pop(view: android.view.View) {
        val pop = PopupMenu(applicationContext,view).apply {  }
        pop.menuInflater.inflate(R.menu.menu2,pop.menu)
        pop.show()
    }
}
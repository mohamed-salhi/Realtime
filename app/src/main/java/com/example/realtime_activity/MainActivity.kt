package com.example.realtime_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtime_activity.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var count=0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = Firebase.database
        val myRef = database.getReference()
        binding.button.setOnClickListener {
            val namee = binding.PersonName.text.toString()
            val id = binding.PersonID.text.toString()
            val age = binding.Personage.text.toString()
            val parson= hashMapOf(
                "name" to namee,
                "id" to id,
                "age" to age,
            )
            myRef.child("parson").child("$count").setValue(parson)
        count++
            Toast.makeText(applicationContext,"true",Toast.LENGTH_LONG).show()
        }
        binding.button2.setOnClickListener {
            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue()
                    Toast.makeText(applicationContext,"true",Toast.LENGTH_LONG).show()
                    binding.textView.text =value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"false",Toast.LENGTH_LONG).show()

                }

            })
        }


    }
}
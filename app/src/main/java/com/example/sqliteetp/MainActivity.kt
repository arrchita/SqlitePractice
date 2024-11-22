package com.example.sqliteetp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.release.gfg1.DBHelper

class MainActivity : AppCompatActivity() {

    // Declare variables for views
    private lateinit var enterName: EditText
    private lateinit var enterAge: EditText
    private lateinit var addName: Button
    private lateinit var printName: Button
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views using findViewById
        enterName = findViewById(R.id.enterName)
        enterAge = findViewById(R.id.enterAge)
        addName = findViewById(R.id.addName)
        printName = findViewById(R.id.printName)
        nameTextView = findViewById(R.id.Name)
        ageTextView = findViewById(R.id.Age)

        addName.setOnClickListener {
            // Create DBHelper instance
            val db = DBHelper(this, null)

            // Get input data
            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                // Add data to database
                db.addName(name, age)

                // Show success message
                Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()

                // Clear input fields
                enterName.text.clear()
                enterAge.text.clear()
            } else {
                // Show error if fields are empty
                Toast.makeText(this, "Please enter both name and age", Toast.LENGTH_SHORT).show()
            }
        }

        printName.setOnClickListener {
            // Create DBHelper instance
            val db = DBHelper(this, null)

            // Get data from database
            val cursor = db.getName()

            // Clear TextViews before appending new data
            nameTextView.text = "Name\n\n"
            ageTextView.text = "Age\n\n"

            // Append data to TextViews
            cursor?.let {
                if (it.moveToFirst()) {
                    do {
                        nameTextView.append(it.getString(it.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                        ageTextView.append(it.getString(it.getColumnIndex(DBHelper.AGE_COL)) + "\n")
                    } while (it.moveToNext())
                }
                it.close() // Close cursor to prevent leaks
            }
        }
    }
}

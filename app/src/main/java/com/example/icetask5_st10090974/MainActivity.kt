package com.example.icetask5_st10090974

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.icetask5_st10090974.DBHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize UI elements
        val addDiary = findViewById<Button>(R.id.addDiary)
        val printDiary = findViewById<Button>(R.id.printDiary)
        val enterDiary = findViewById<EditText>(R.id.enterDiary)
        val Diary = findViewById<TextView>(R.id.Dairy)

        // below code is to add on click
        // listener to our add name button
        addDiary.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val diary = enterDiary.text.toString()

            // calling method to add
            // name to our database
            db.addDiary(diary)

            // Toast to message on the screen
            Toast.makeText(this, diary + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterDiary.text.clear()
        }

        // below code is to add on click
        // listener to our print name button
        printDiary.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getDiary()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Diary.append(cursor.getString(cursor.getColumnIndex(DBHelper.DIARY_COl)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Diary.append(cursor.getString(cursor.getColumnIndex(DBHelper.DIARY_COl)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}


**SQLite Database in Android**

**Overview**

SQLite is a lightweight, serverless, self-contained, high-performance, embedded SQL database engine. It's ideal for mobile and embedded applications, including Android apps.

**Getting Started**

1. **Project Setup:**
   - Create a new Android Studio project.
   - Add the necessary permission to access storage in your `AndroidManifest.xml` file:
     ```xml
     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
     ```

2. **Database Helper Class:**
   - Create a `DBHelper` class that extends `SQLiteOpenHelper`:
     ```kotlin
     class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
         // ... (implementation details)
     }
     ```
   - Implement the `onCreate()` and `onUpgrade()` methods to create and update the database schema.

3. **Database Operations:**
   - **Create a Table:**
     ```kotlin
     val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
             KEY_ID + " INTEGER PRIMARY KEY," +
             KEY_NAME + " TEXT," +
             KEY_AGE + " INTEGER" + ")"
     ```
   - **Insert Data:**
     ```kotlin
     val values = ContentValues()
     values.put(KEY_NAME, name)
     values.put(KEY_AGE, age)
     val success = db.insert(TABLE_NAME, null, values)
     ```
   - **Retrieve Data:**
     ```kotlin
     val cursor: Cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
     // ... (iterate over cursor to extract data)
     ```

4. **Activity Implementation:**
   - Design your UI with necessary elements like EditText, Button, etc.
   - Implement click listeners for the buttons.
   - Use the `DBHelper` class to perform database operations.
   - Display the results in a suitable format, such as a TextView or a RecyclerView.

```

package com.example.deepakrattan.roomdemo;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave, btnView, btnDelete, btnUpdate;
    private EditText edtName, edtPasswd;
    private String name, password;
    private User user;
    private UserDB userDB;
    private UserDao userDao;
    public static final String Database_Name = "UserDB";
    public static final String TAG = "test";
    private long id;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById
        edtName = findViewById(R.id.edtName);
        edtPasswd = findViewById(R.id.edtPasswd);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);

        userDB = Room.databaseBuilder(getApplicationContext(), UserDB.class, Database_Name).build();
        userDao = userDB.getUserDao();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                Toast.makeText(this, "Save clicked", Toast.LENGTH_SHORT).show();
                name = edtName.getText().toString().trim();
                password = edtPasswd.getText().toString().trim();
                new InsertTask().execute(name, password);
                break;
            case R.id.btnView:
                Toast.makeText(this, "View clicked", Toast.LENGTH_SHORT).show();
                new ViewUserTask().execute();
                break;
            case R.id.btnDelete:
                Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
                new DeleteUserTask().execute();

                break;
            case R.id.btnUpdate:
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show();
                new UpdateUserTask().execute();
                break;
        }
    }


    public class InsertTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            name = params[0];
            password = params[1];
            user = new User(name, password);
            id = userDao.insert(user);
            Log.d(TAG, "doInBackground: " + id);

            //Updating UI from background
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (id == -1)
                        Toast.makeText(MainActivity.this, "Insertion unsuccessful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Insertion Successful.Row Id is " + id, Toast.LENGTH_SHORT).show();

                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    public class ViewUserTask extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {
            userList = userDao.getusers();
            return userList;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < users.size(); i++) {
                user = users.get(i);
                int id = user.getUid();
                name = user.getUserName();
                password = user.getPassword();
                buffer.append(id + " " + name + " " + password + "\n");
            }
            Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    public class DeleteUserTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            user = new User("abc", "123");
            user.setUid(1);
            int d = userDao.delete(user);
            return d;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Log.d(TAG, "onPostExecute: " + integer.toString());
            Toast.makeText(MainActivity.this, "deleted" + integer, Toast.LENGTH_SHORT).show();
        }
    }

    public class UpdateUserTask extends AsyncTask<Void, Void, Integer> {


        @Override
        protected Integer doInBackground(Void... voids) {
            user = new User("Deepak", "123445");
            user.setUid(2);
            int u = userDao.update(user);
            return u;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d(TAG, "onPostExecute: " + integer.toString());
            Toast.makeText(MainActivity.this, "Updated" + integer, Toast.LENGTH_SHORT).show();

        }
    }
}

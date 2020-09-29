package com.codelabs.userinterface.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.codelabs.userinterface.R;
import com.codelabs.userinterface.UserAdapter;
import com.codelabs.userinterface.database.AppDatabase;
import com.codelabs.userinterface.entities.User;
import com.codelabs.userinterface.utility.AppExecutors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {
    private AppDatabase appDatabase;
    private List<User> userList;
    //    private ListView listView;
//    private ArrayAdapter<String> arrayAdapter;
    private RecyclerView recyclerView;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_user);
        //Initialising the database
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        initializeRecyclerview();
        retrieveUserTable();
    }

    public void initializeRecyclerview() {
        recyclerView = findViewById(R.id.rvUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
    }


    public void retrieveUserTable() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                userList = appDatabase.userDao().loadAllUsers();
//                for (int i = 0; i < userList.size(); i++) {
//                    Log.i("TABLE", "Person Table values " + userList.get(i).getArea());
//
//                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ListView
//                        Log.d("TABLE", "ListView ");
//                        String[] userArr = new String[userList.size()];
//                        for (int i = 0; i < userList.size(); i++) {
//                            Log.i("TABLE", "Person Table values " + userList.get(i).getName());
//                            userArr[i] = userList.get(i).toString();
//                        }
                        //List view
//                        listView = findViewById(R.id.listview_id);
//                        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, userArr);
//                        listView.setAdapter(arrayAdapter);

                        //Recycler view
                        userAdapter.setUserList(userList);
                    }
                });
            }
        });
    }
}
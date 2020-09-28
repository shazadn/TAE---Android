package com.codelabs.userinterface.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.codelabs.userinterface.R;
import com.codelabs.userinterface.database.AppDatabase;
import com.codelabs.userinterface.entities.User;
import com.codelabs.userinterface.utility.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {
    private AppDatabase appDatabase;
    private List<User> userList;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        retrieveUserTable();


    }

    public void retrieveUserTable() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                userList = appDatabase.userDao().loadAllUsers();
                for(int i=0; i< userList.size(); i++){
                    Log.i("TABLE", "Person Table values " + userList.get(i).getName());

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ListView
                        Log.d("TABLE", "ListView ");
                        String[] userArr = new String[userList.size()];
                        for(int i=0; i< userList.size(); i++){
                            Log.i("TABLE", "Person Table values " + userList.get(i).getName());
                            userArr[i] = userList.get(i).getName();
                        }
                        listView = findViewById(R.id.listview_id);
                        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, userArr);
                        listView.setAdapter(arrayAdapter);

                    }
                });
            }
        });
    }
}
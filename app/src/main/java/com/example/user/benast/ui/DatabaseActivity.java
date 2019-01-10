package com.example.user.benast.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.benast.R;
import com.example.user.benast.db.MyDbAdapter;
import com.example.user.benast.helpers.Message;

public class DatabaseActivity extends AppCompatActivity {
    EditText etName, etPassword, etUpdateOld, etUpdateNew, etDelete;
    MyDbAdapter myDbAdapter;
    Button btnAddUser, btnDelUser, btnUpdateUser, btnViewData;

    public static void start(Context context) {
        Intent intent = new Intent(context, DatabaseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_example);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        etUpdateOld = findViewById(R.id.et_current_user);
        etUpdateNew = findViewById(R.id.et_new_user);
        etDelete = findViewById(R.id.et_del_user);
        myDbAdapter = new MyDbAdapter(this);
        btnAddUser = findViewById(R.id.btn_add_user);
        btnUpdateUser = findViewById(R.id.btn_update);
        btnDelUser = findViewById(R.id.btn_del);
        btnViewData = findViewById(R.id.btn_view_data);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData();
            }
        });
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
        btnDelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delUser();
            }

            private void delUser() {
                String userName = etDelete.getText().toString();
                if (userName.isEmpty()) {
                    Message.message(getApplicationContext(), "No Data");
                } else {
                    int a = myDbAdapter.deleteUser(userName);
                    if (a <= 0) {
                        Message.message(getApplicationContext(), "Enter Data");
                        etDelete.setText("");
                    } else {
                        Message.message(getApplicationContext(), "DELETED");
                        etDelete.setText("");
                    }
                }
            }
        });
    }

    private void updateUser() {
        String updateOld = etUpdateOld.getText().toString();
        String updateNew = etUpdateNew.getText().toString();
        if (updateOld.isEmpty() || updateNew.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = myDbAdapter.updateName(updateOld, updateNew);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                etUpdateOld.setText("");
                etUpdateNew.setText("");
            } else {
                Message.message(getApplicationContext(), "Updated");
                etUpdateOld.setText("");
                etUpdateNew.setText("");
            }
        }
    }

    private void viewData() {
        String data = myDbAdapter.getData();
        if (!data.isEmpty()) {
            Message.message(this, data);
        } else {
            data = "No Data Found";
            Message.message(this, data);
        }
    }

    private void addUser() {
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();
        if (name.isEmpty() || password.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Both Name and Password");
        } else {
            long id = myDbAdapter.insertData(name, password);
            if (id <= 0) {
                Message.message(getApplicationContext(), "Insertion Unsuccessful");
                etName.setText("");
                etPassword.setText("");
            } else {
                Message.message(getApplicationContext(), "Insertion Successful");
                etName.setText("");
                etPassword.setText("");
            }
        }
    }

}

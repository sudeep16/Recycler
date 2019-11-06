package com.user.dynamicrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.user.dynamicrecycler.adapter.PersonDetailAdapter;
import com.user.dynamicrecycler.model.PersonDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName,etAge;
    RadioButton rbMale, rbFemale, rbOthers;
    Spinner spinner;
    Button btnSave;
    RadioGroup rdoGrp;
    RecyclerView recyclerView;
    String gen;
    int img;
    List<PersonDetail> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.name);
        etAge=findViewById(R.id.age);
        rbMale=findViewById(R.id.rdoMale);
        rbFemale=findViewById(R.id.rdoFemale);
        rbOthers=findViewById(R.id.rdoOther);
        btnSave=findViewById(R.id.save);
        spinner=findViewById(R.id.spinimage);
        rdoGrp=findViewById(R.id.grpGender);


        btnSave.setOnClickListener(this);


        // Image images[]={image1,image2};

        String images []={
                "Select Image",
                "No Image",
                "Joker",
                "One",
                "Two"};
        ArrayAdapter adapter= new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,images

        );
        spinner.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        if(TextUtils.equals(etName.getText().toString(),"")){
            return;
        }
        if(TextUtils.equals(etAge.getText().toString(),"")){
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);

        if(rbMale.isChecked()){
            gen="Male";
        }
        if(rbFemale.isChecked()){
            gen="Female";
        }
        if(rbOthers.isChecked()){
            gen="Others";
        }
        String image=spinner.getSelectedItem().toString();
        if(image=="image") {
            img = R.drawable.noimage;
        }
        if(image=="one") {
            img = R.drawable.one;
        }
        if(image=="two") {
            img = R.drawable.two;
        }
        if(image=="joker") {
            img = R.drawable.joker;
        }

        int age=Integer.parseInt(etAge.getText().toString());

        userList.add(new PersonDetail(etName.getText().toString(), age, gen, img));
        PersonDetailAdapter personDetailAdapter = new PersonDetailAdapter(this, userList);
        recyclerView.setAdapter(personDetailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        etName.setText("");
        etAge.setText("");
        rbFemale.clearFocus();
        rbOthers.clearFocus();
        rbMale.clearFocus();
        spinner.clearFocus();

    }
}
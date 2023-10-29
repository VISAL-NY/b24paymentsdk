package com.example.bill24paymentonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.bill24.onlinepaymentsdk.model.main.Bill24OnlinePayment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(v->{
            Bill24OnlinePayment.init(getSupportFragmentManager(),
                    "D249813B02B3","123",false, SuccessActivity.class
                    );
        });


    }
}
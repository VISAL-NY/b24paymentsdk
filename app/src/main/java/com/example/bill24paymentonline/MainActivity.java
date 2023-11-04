package com.example.bill24paymentonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.bill24.b24paymentsdk.main.B24PaymentSdk;
import com.example.bill24paymentonline.model.Customer;
import com.example.bill24paymentonline.model.RequestModel;
import com.example.bill24paymentonline.model.ResponseModel;
import com.example.bill24paymentonline.model.RetrofitClientApp;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private String currency="KHR";
    private String amount;
    private String userRef;
    ResponseModel responseModel;
    boolean isLightMode=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatEditText txtAmount=findViewById(R.id.txt_amount);
        AppCompatEditText txtUserRef=findViewById(R.id.txt_user_ref);
        RadioGroup radioGroup=findViewById(R.id.radio_group);
        RadioGroup radioAppearance=findViewById(R.id.radio_appearance);
        Button button=findViewById(R.id.button_init_transaction);

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            RadioButton choice=findViewById(i);
            currency=choice.getText().toString();
        });
        radioAppearance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=findViewById(i);
                //Toast.makeText(MainActivity.this, ""+radioButton., Toast.LENGTH_SHORT).show();
                if(radioButton.getText().toString().equals("Light Mode")){
                    //Toast.makeText(MainActivity.this, ""+radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    isLightMode=true;
                }
                if(radioButton.getText().toString().equals("Dark Mode")){
                   isLightMode=false;
                }
            }
        });



        button.setOnClickListener(v->{

            //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

           // Bill24OnlinePayment.init(getSupportFragmentManager(),"D249813B02B3","123",true);

            RequestModel requestModel=new RequestModel();
            Customer customer=new Customer();
            List<Customer> customerList=new ArrayList<>();

            amount=txtAmount.getText().toString();
            userRef=txtUserRef.getText().toString();

            UUID  uuid=UUID.randomUUID();
            String identityCode=uuid.toString().substring(0,15);
            String purponseOfTran=uuid.toString().substring(0,10);
            String deviceCode="devicode";
            String desc="decription";
            String cancelUrl="";
            String redirect="https://bill24.com.kh/";
            String ChannelCode="CH1";
            String branchCode="01";
            String branchName="BBB";
            String customerCode="C01";
            String customerName="Mithona";
            String customerLatin="Mithona";
            String customerbillNo="";


            customer.setBranchCode(branchCode);
            customer.setBranchName(branchName);
            customer.setCustomerCode(customerCode);
            customer.setCustomerName(customerName);
            customer.setCustomerNameLatin(customerLatin);
            customer.setBillNo(customerbillNo);
            customer.setAmount(Double.parseDouble(amount));
            customerList.add(customer);

            requestModel.setIdentityCode(identityCode);
            requestModel.setPurposeOfTransaction(purponseOfTran);
            requestModel.setDeviceCode(deviceCode);
            requestModel.setDescription(desc);
            requestModel.setCurrency(currency);
            requestModel.setAmount(Double.parseDouble(amount));
            requestModel.setLanguage("");
            requestModel.setCancelURL(cancelUrl);
            requestModel.setRedirectURL(redirect);
            requestModel.setChannelCode(ChannelCode);
            requestModel.setUserRef(userRef);
            requestModel.setCustomers(customerList);



            initTranV2(requestModel);

        });
    }

    private void initTranV2(RequestModel model){
        Call<ResponseModel> call= RetrofitClientApp.getInstance().
                getApiClientApp().initTranV2(
                        "application/json",
                        "1f78ef77601c4ca7a66f7392ac4f9d1d",//5537
                       // "b05da7819de348668cf1d1acee7a6364",//3655
                        model

                );

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                   responseModel=response.body();
                   B24PaymentSdk.initSdk(
                           getSupportFragmentManager(),
                           responseModel.getData().getTranID(),
                           "123X",
                           "km",
                           isLightMode,
                           "en"

                   );

                   Log.d("tranNo", "onResponse: "+response.body().getData().getTranID());
               }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("tranError", "onFailure: "+t.getMessage());

            }
        });
    }
}
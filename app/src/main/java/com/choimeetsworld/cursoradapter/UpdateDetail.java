package com.choimeetsworld.cursoradapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.choimeetsworld.cursoradapter.Database.DbHelper;

/**
 * Created by hychoi on 9/14/2015.
 */
public class UpdateDetail extends AppCompatActivity {

    private EditText updateCustomer;
    private EditText updateOrder;
    private EditText updateAddress;
    private EditText updateNumber;
    private EditText updatePrice;
    private EditText updateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        updateCustomer = (EditText) findViewById(R.id.editText_customer_update);
        updateOrder = (EditText) findViewById(R.id.editText_order_update);
        updateAddress = (EditText) findViewById(R.id.editText_address_update);
        updateNumber = (EditText) findViewById(R.id.editText_number_update);
        updatePrice = (EditText) findViewById(R.id.editText_price_update);
        updateTime = (EditText) findViewById(R.id.editText_time_update);

        String customerText = bundle.getString(DbHelper.COL_CUSTOMER);
        String orderText = bundle.getString(DbHelper.COL_ORDER);
        String addressText = bundle.getString(DbHelper.COL_ADDRESS);
        String numberText = bundle.getString(DbHelper.COL_NUMBER);
        String priceText = bundle.getString(DbHelper.COL_PRICE);
        String timeText = bundle.getString(DbHelper.COL_TIME);

        updateCustomer.setText(customerText);
        updateOrder.setText(orderText);
        updateAddress.setText(addressText);
        updateNumber.setText(numberText);
        updatePrice.setText(priceText);
        updateTime.setText(timeText);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                Intent updatedIntent = new Intent();
                updatedIntent.putExtra(DbHelper.COL_CUSTOMER, updateCustomer.getText().toString());
                updatedIntent.putExtra(DbHelper.COL_ORDER, updateOrder.getText().toString());
                updatedIntent.putExtra(DbHelper.COL_ADDRESS, updateAddress.getText().toString());
                updatedIntent.putExtra(DbHelper.COL_NUMBER, updateNumber.getText().toString());
                updatedIntent.putExtra(DbHelper.COL_PRICE, updatePrice.getText().toString());
                updatedIntent.putExtra(DbHelper.COL_TIME, updateTime.getText().toString());
                setResult(RESULT_OK, updatedIntent);
                finish();
        }
    }
}

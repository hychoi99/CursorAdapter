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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        updateCustomer = (EditText) findViewById(R.id.editText_customer_update);
        updateOrder = (EditText) findViewById(R.id.editText_order_update);

        String customerText = bundle.getString(DbHelper.COL_CUSTOMER);
        String orderText = bundle.getString(DbHelper.COL_ORDER);

        updateCustomer.setText(customerText);
        updateOrder.setText(orderText);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                Intent updatedIntent = new Intent();
                updatedIntent.putExtra(DbHelper.COL_CUSTOMER, updateCustomer.getText().toString());
                updatedIntent.putExtra(DbHelper.COL_ORDER, updateOrder.getText().toString());
                setResult(RESULT_OK, updatedIntent);
                finish();
        }
    }
}

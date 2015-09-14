package com.choimeetsworld.cursoradapter;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.choimeetsworld.cursoradapter.Database.DataAccessObject;
import com.choimeetsworld.cursoradapter.Database.DbHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

/**
 * Created by hychoi on 9/6/2015.
 */
public class OrderDetail extends AppCompatActivity implements OnMapReadyCallback {

    private TextView textViewCustomer;
    private TextView textViewOrder;
    private Button buttonDelete;
    private Button buttonUpdate;
    private String order_id;
    private DataAccessObject dao;
    private UiSettings mUiSettings;
    private GoogleMap mMap;
    private String destString;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        textViewCustomer = (TextView) findViewById(R.id.textView_customer);
        textViewOrder = (TextView) findViewById(R.id.textView_order);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonUpdate = (Button) findViewById(R.id.button_update);

        dao = new DataAccessObject(this);
        dao.openDb();

        textViewCustomer.setText(bundle.getString(DbHelper.COL_CUSTOMER));
        textViewOrder.setText(bundle.getString(DbHelper.COL_ORDER));
        order_id = bundle.getString(DbHelper.COL_ID);
        destString = bundle.getString(DbHelper.COL_ORDER);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //delete row with the known order id, using the dataAccessor
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteRow(order_id);
                finish();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(OrderDetail.this, UpdateDetail.class);
                updateIntent.putExtras(bundle);
                startActivityForResult(updateIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String updatedCustomerText = data.getStringExtra(DbHelper.COL_CUSTOMER);
                String updatedOrderText = data.getStringExtra(DbHelper.COL_ORDER);
                textViewCustomer.setText(updatedCustomerText);
                textViewOrder.setText(updatedOrderText);
                dao.updateRow(order_id, updatedCustomerText, updatedOrderText);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        mMap.setMyLocationEnabled(true);
        new MapLoadTask().execute(destString);
    }

    private class MapLoadTask extends AsyncTask<String, Void, LatLng> {

        @Override
        protected LatLng doInBackground(String... params) {
            Geocoder coder = new Geocoder(getApplicationContext(), Locale.getDefault());
            //vs getApplication vs this
            List<Address> address;
            LatLng p1 = null;


            try {
                address = coder.getFromLocationName(params[0], 5);
                System.out.println(address);
                if (address != null && address.size() > 0) {
                    double lat = address.get(0).getLatitude();
                    double lng = address.get(0).getLongitude();
                    System.out.println(lat + lng);
                }
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();

                p1 = new LatLng(location.getLatitude(), location.getLongitude() );

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            return p1;
        }

        @Override
        protected void onPostExecute(LatLng destination) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 13));
            mMap.addMarker(new MarkerOptions()
                    .title("Your Delivery Address")
                    .snippet("The most populous city in Australia.")
                    .position(destination));
        }

    }
}

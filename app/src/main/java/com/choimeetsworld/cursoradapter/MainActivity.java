package com.choimeetsworld.cursoradapter;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.choimeetsworld.cursoradapter.Database.DataAccessObject;
import com.choimeetsworld.cursoradapter.Database.DbHelper;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    private LoaderCallbacks<Cursor> mCallbacks;
    private SimpleCursorAdapter mCursorAdapter;
    //private CustomCursorAdapter mCustomCursorAdapter;
    private ListView ordersList;
    private Button addButton;
    private EditText customerInput;
    private EditText orderInput;
    private DataAccessObject dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordersList = (ListView) findViewById(R.id.listView_orders);
        addButton = (Button) findViewById(R.id.button_add);
        customerInput = (EditText) findViewById(R.id.editText_customer);
        orderInput = (EditText) findViewById(R.id.editText_order);
        dao = new DataAccessObject(this);

        dao.openDb(); //only one instance of dataAccessor created

        /*
        mCustomCursorAdapter = new CustomCursorAdapter(this);
        getLoaderManager().initLoader(0, null, this).forceLoad();
        ordersList.setAdapter(mCustomCursorAdapter);
        */

        mCursorAdapter = new SimpleCursorAdapter(this, R.layout.orders_row, null,
                new String[]{DbHelper.COL_CUSTOMER, DbHelper.COL_ORDER},
                new int[]{R.id.textViewRow_customer, R.id.textViewRow_order}, 0);
        ordersList.setAdapter(mCursorAdapter);


        //getLoaderManager().initLoader(0, null, this).forceLoad();

        /*addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.createRow(customerInput.getText().toString(), orderInput.getText().toString());
                onQueryTextChanged();
            }
        });*/

        //Start new activity showing order details, pass all important info
        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) mCursorAdapter.getItem(position);
                Intent intentOrderDetail = new Intent(MainActivity.this, OrderDetail.class);
                Bundle bundle_order = new Bundle();
                bundle_order.putString(DbHelper.COL_CUSTOMER, cursor.getString(cursor.getColumnIndex(DbHelper.COL_CUSTOMER)));
                bundle_order.putString(DbHelper.COL_ORDER, cursor.getString(cursor.getColumnIndex(DbHelper.COL_ORDER)));
                bundle_order.putString(DbHelper.COL_ID, cursor.getString(cursor.getColumnIndex(DbHelper.COL_ID)));

                intentOrderDetail.putExtras(bundle_order);
                startActivity(intentOrderDetail);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //on button click events, add creates new row into database using dataAccessor
    public void onClick(View view) {
        Log.i("in", "clicked");
        switch (view.getId()) {
            case R.id.button_add:
                dao.createRow(customerInput.getText().toString(), orderInput.getText().toString());
                onQueryTextChanged();
                break;
            case R.id.button_clear:
                dao.clearDb();
                onQueryTextChanged();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onQueryTextChanged();
    }

    //restarts loader when data has changed
    public boolean onQueryTextChanged() {
        // Called when the action bar search text has changed.  Update
        // the search filter, and restart the loader to do a new query
        // with this filter.
        getLoaderManager().restartLoader(0, null, this).forceLoad();
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DataLoader(this, dao);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub

    }
}

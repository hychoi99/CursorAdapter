package com.choimeetsworld.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.choimeetsworld.cursoradapter.Database.DbHelper;

/**
 * Created by hychoi on 9/8/2015.
 */
public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater cursorInflater;

    public CustomCursorAdapter(Context context) {
        super(context, null, false);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.orders_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String strCustomer = cursor.getString(cursor.getColumnIndex(DbHelper.COL_CUSTOMER));
        String strOrder = cursor.getString(cursor.getColumnIndex(DbHelper.COL_ORDER));
        String strID = cursor.getString(cursor.getColumnIndex(DbHelper.COL_ID));
        TextView textViewCustomer = (TextView) view.findViewById(R.id.textView_customer);
        TextView textViewOrder = (TextView) view.findViewById(R.id.textView_order);

        if (!strCustomer.isEmpty() && !strOrder.isEmpty()) {
            textViewCustomer.setText(strCustomer);
            textViewOrder.setText(strOrder);
        }
    }
}

package com.choimeetsworld.cursoradapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.choimeetsworld.cursoradapter.Database.DbHelper;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.SimpleCursorSwipeAdapter;

/**
 * Created by hychoi on 9/8/2015.
 */
public class CustomCursorAdapter extends SimpleCursorSwipeAdapter {

    Context mContext;

    public CustomCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        mContext = context;
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {

        return R.id.swipe;
    }

    @Override
    public void closeAllItems() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.orders_row_test, parent, false);
        }


        TextView trash = (TextView) view.findViewById(R.id.trash);
        TextView info = (TextView) view.findViewById(R.id.position);
        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("View CLicked");
                System.out.println(v.getId());
            }
        });

        /*
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOrderDetail = new Intent(mContext, OrderDetail.class);
                Bundle bundle_order = new Bundle();
                bundle_order.putString(DbHelper.COL_CUSTOMER,
                        cursor.getString(cursor.getColumnIndex(DbHelper.COL_CUSTOMER)));
                bundle_order.putString(DbHelper.COL_ORDER,
                        cursor.getString(cursor.getColumnIndex(DbHelper.COL_ORDER)));
                bundle_order.putString(DbHelper.COL_ID,
                        cursor.getString(cursor.getColumnIndex(DbHelper.COL_ID)));
                intentOrderDetail.putExtras(bundle_order);
                mContext.startActivity(intentOrderDetail);
            }
        });*/

        return super.getView(position, convertView, parent);
    }
}

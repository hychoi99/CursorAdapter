package com.choimeetsworld.cursoradapter;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import com.choimeetsworld.cursoradapter.Database.DataAccessObject;

/**
 * Created by hychoi on 9/6/2015.
 */
public class DataLoader extends AsyncTaskLoader<Cursor> {


    Context mContext;
    DataAccessObject dao;

    public DataLoader(Context context, DataAccessObject dao) {
        super(context);
        mContext = context;
        this.dao = dao;
    }

    @Override
    public Cursor loadInBackground() {
        return dao.getAll();
    }
}

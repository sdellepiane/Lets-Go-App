package com.letsgo.appletsgo.data.store.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.letsgo.appletsgo.domain.model.entity.Actividades;

import java.sql.SQLException;

/**
 * Created by sergio on 7/7/16.
 *
 * Clase que crea y modifica la base de datos
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "com.projects.looptech.paygo";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Actividades.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Actividades.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearDatabase(){
        try {
            TableUtils.clearTable(this.connectionSource, Actividades.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() {
        super.close();
    }
}

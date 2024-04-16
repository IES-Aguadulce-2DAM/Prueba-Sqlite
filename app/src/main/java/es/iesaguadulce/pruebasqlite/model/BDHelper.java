package es.iesaguadulce.pruebasqlite.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    private static BDHelper INSTANCE;

    public static synchronized BDHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new BDHelper(context);
        }

        return INSTANCE;
    }

    private BDHelper(Context context) {
        super(context, "coches.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE coches (id INTEGER PRIMARY KEY AUTOINCREMENT, marca TEXT, modelo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS coches");
        onCreate(db);
    }
}

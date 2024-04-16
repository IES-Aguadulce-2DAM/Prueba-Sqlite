package es.iesaguadulce.pruebasqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class CochesRepositorio {
    private final BDHelper helper;
    private SQLiteDatabase db;

    public CochesRepositorio(Context context) {
        helper = BDHelper.getInstance(context);
    }

    public List<Coche> obtenerCoches() {
        db = helper.getReadableDatabase();
        ArrayList<Coche> coches = new ArrayList<>();
        Cursor cur = db.rawQuery("SELECT * FROM coches", null);
        if (cur.moveToFirst()) {
            do {
                Coche coche = new Coche(cur.getInt(0), cur.getString(1), cur.getString(2));
                coches.add(coche);
            } while (cur.moveToNext());
        }

        db.close();
        return coches;
    }

    public Coche obtenerCoche(int id) {
        db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM coches WHERE id = ?", new String[]{String.valueOf(id)});
        Coche coche = null;
        if (cur.moveToFirst()) {
            coche = new Coche(cur.getInt(0), cur.getString(1), cur.getString(2));
        }

        db.close();
        return coche;
    }

    public List<Coche> obtenerCochesPorMarca(String marca) {
        db = helper.getReadableDatabase();
        ArrayList<Coche> coches = new ArrayList<>();
        Cursor cur = db.rawQuery("SELECT * FROM coches WHERE marca = ?", new String[]{marca});
        if (cur.moveToFirst()) {
            do {
                Coche coche = new Coche(cur.getInt(0), cur.getString(1), cur.getString(2));
                coches.add(coche);
            } while (cur.moveToNext());
        }

        db.close();
        return coches;
    }

    public void insertarCoche(Coche coche) {
        db = helper.getWritableDatabase();
        db.insert("coches", null, coche.toContentValues());
        db.close();
    }

    public void actualizarCoche(Coche coche) {
        db = helper.getWritableDatabase();
        db.update("coches", coche.toContentValues(), "id = ?", new String[]{String.valueOf(coche.getId())});
        db.close();
    }

    public void borrarCoche(Coche coche) {
        db = helper.getWritableDatabase();
        db.delete("coches", "id = ?", new String[]{String.valueOf(coche.getId())});
        db.close();
    }
}

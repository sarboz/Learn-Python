package tj.python.DataServer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tj.python.Models.Book;
import tj.python.Models.DataSubMavzu;
import tj.python.Models.Masala;
import tj.python.Models.Mavzu;
import tj.python.Models.SubMavzu;
import tj.python.Models.Test;
import tj.python.Utils.MyDatabase;

public class DataServer {


    public static List<Mavzu> getMavzu(Context mctx) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from mavzu", null);
        List<Mavzu> list = new ArrayList<>();

        while (c.moveToNext()) {
            Mavzu m = new Mavzu();
            m.setId(c.getString(0));
            m.setName(c.getString(1));
            m.setImg(c.getString(2));

            list.add(m);
        }
        return list;
    }

    public static List<SubMavzu> getSubMavzu(Context mctx, String id) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from sub_mavzu where id_mavzu=" + id, null);
        List<SubMavzu> list = new ArrayList<>();

        while (c.moveToNext()) {
            SubMavzu m = new SubMavzu();
            m.setId(c.getString(0));
            m.setIdMavzu(c.getString(1));
            m.setName(c.getString(2));
            m.setImg(c.getString(3));
            m.setFav(c.getString(4));

            list.add(m);
        }
        return list;
    }


    public static List<SubMavzu> getAllSubMavzu(Context mctx) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from sub_mavzu ", null);
        List<SubMavzu> list = new ArrayList<>();

        while (c.moveToNext()) {
            SubMavzu m = new SubMavzu();
            m.setId(c.getString(0));
            m.setIdMavzu(c.getString(1));
            m.setName(c.getString(2));
            m.setImg(c.getString(3));
            m.setFav(c.getString(4));

            list.add(m);
        }
        return list;
    }

    public static List<SubMavzu> getAllSubMavzu(Context mctx,String s) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select id, id_mavzu, lower(name),img,fav from sub_mavzu where name like '%"+s+"%'", null);
        List<SubMavzu> list = new ArrayList<>();

        while (c.moveToNext()) {
            SubMavzu m = new SubMavzu();
            m.setId(c.getString(0));
            m.setIdMavzu(c.getString(1));
            m.setName(c.getString(2));
            m.setImg(c.getString(3));
            m.setFav(c.getString(4));

            list.add(m);
        }
        return list;
    }


    public static List<SubMavzu> getSubFavs(Context mctx) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from sub_mavzu where fav=1", null);
        List<SubMavzu> list = new ArrayList<>();

        while (c.moveToNext()) {
            SubMavzu m = new SubMavzu();
            m.setId(c.getString(0));
            m.setIdMavzu(c.getString(1));
            m.setName(c.getString(2));
            m.setImg(c.getString(3));
            m.setFav(c.getString(4));

            list.add(m);
        }
        return list;
    }


    public static List<DataSubMavzu> getDataSubMavzu(Context mctx, String id) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from data_sub_mavzu where id_sub_mavzu=" + id, null);
        List<DataSubMavzu> list = new ArrayList<>();

        while (c.moveToNext()) {
            DataSubMavzu m = new DataSubMavzu();
            m.setId(c.getString(0));
            m.setIdSubMavzu(c.getString(1));
            m.setText(c.getString(2));
            m.setImg(c.getString(3));
            list.add(m);
        }
        return list;
    }


    public static void setFav(Context mctx, int status, String id) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("fav", status);
        int i = data.update("sub_mavzu", cv, "id=" + id, null);

    }


    public static List<Book> getBooks(Context mctx) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from kitob", null);
        List<Book> list = new ArrayList<>();

        while (c.moveToNext()) {
            Book m = new Book();
            m.setId(c.getString(0));
            m.setFile_name(c.getString(1));
            m.setTitle(c.getString(2));
            m.setAuthor(c.getString(3));
            m.setImg(c.getString(4));
            list.add(m);
        }
        return list;
    }

    public static List<Masala> getMasala(Context mctx) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from masala", null);
        List<Masala> list = new ArrayList<>();

        while (c.moveToNext()) {
            Masala m = new Masala();
            m.setId(c.getString(0));
            m.setShart(c.getString(1));
            m.setKod(c.getString(2));
            m.setNatijaImg(c.getString(3));
            m.setTitle(c.getString(4));
            list.add(m);
        }
        return list;
    }

    public static List<Test> getTests(Context mctx) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from test", null);
        List<Test> list = new ArrayList<>();
        int j = 0;
        while (c.moveToNext()) {
            Test t = new Test();
            List<String> s = new ArrayList<>();

            t.setId(c.getString(0));
            t.setSavol(c.getString(1));
            t.setJavob(c.getString(2));
            for (int i = 5; i >= 2; i--) {
                s.add(c.getString(i));
            }
            t.setVariants(s);

            list.add(t);
            Collections.shuffle(list.get(j).getVariants());
            j++;
        }
        return list;
    }


    public static List<Test> getTestsForSub(Context mctx, String id) {
        MyDatabase db = new MyDatabase(mctx);
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor c = data.rawQuery("select * from sub_mavzu_test where id_sub_mavzu=" + id, null);
        List<Test> list = new ArrayList<>();
        int j = 0;
        while (c.moveToNext()) {
            Test t = new Test();
            List<String> s = new ArrayList<>();
            t.setId_sub_mavzu(c.getString(6));
            t.setId(c.getString(0));
            t.setSavol(c.getString(1));
            t.setJavob(c.getString(2));
            for (int i = 5; i >= 2; i--) {
                s.add(c.getString(i));
            }
            t.setVariants(s);

            list.add(t);
            Collections.shuffle(list.get(j).getVariants());
            j++;
        }
        return list;
    }


}

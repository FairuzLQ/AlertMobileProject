package com.example.alertmobileproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SESSION(ID INTEGER PRIMARY KEY, LOGIN TEXT)");
        db.execSQL(Constants.CREATE_TABLE_CONTACT);
        db.execSQL("CREATE TABLE USER(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("INSERT INTO SESSION(ID, LOGIN) VALUES(1,'KOSONG')");
        db.execSQL("CREATE TABLE USER_DATA(ID_PROFIL INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, EMAIL TEXT, NOMOR TEXT, PESAN TEXT, USER_ID INTEGER, FOREIGN KEY(USER_ID) REFERENCES USER(ID)) ");
        db.execSQL("CREATE TABLE REFERENCE(ID_REF INTEGER PRIMARY KEY AUTOINCREMENT, REF_USER TEXT)");
        db.execSQL("CREATE TABLE RIWAYAT(ID_RIWAYAT INTEGER PRIMARY KEY AUTOINCREMENT, NOMOR_RIWAYAT TEXT, WAKTU TEXT, USER_ID_RP INTEGER, FOREIGN KEY(USER_ID_RP) REFERENCES USER(ID))");
        db.execSQL("CREATE TABLE RIWAYAT_PENYAKIT(ID_R_PENYAKIT INTEGER PRIMARY KEY AUTOINCREMENT, TGL_LAHIR TEXT, GOL_DARAH TEXT, R_PENYAKIT TEXT, USER_ID_PENYAKIT INTEGER, FOREIGN KEY(USER_ID_PENYAKIT) REFERENCES USER(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SESSION");
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS USER_DATA");
        db.execSQL("DROP TABLE IF EXISTS REFERENCE");
        db.execSQL("DROP TABLE IF EXISTS RIWAYAT");
        db.execSQL("DROP TABLE IF EXISTS R_PENYAKIT");
        onCreate(db);
    }

    //CHECK SESSION
    public Boolean checkSession(String sessionValues){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SESSION WHERE LOGIN = ?", new String[]{sessionValues});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LOGIN", sessionValues);
        long update  = db.update("SESSION", contentValues, "ID = "+id, null);
        if(update == -1){
            return false;
        }
        else {
            return true;
        }

    }

    //INSERT USER
    public Boolean insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("USERNAME",username);
        contentValues.put("PASSWORD",password);

        long insert = db.insert("USER", null, contentValues);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    //LOGIN
    public Boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public long referData(String refer_user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("REF_USER",refer_user);


        long rid = db.insert("REFERENCE",null,contentValues);

        db.close();

        return rid;
    }

    public String[] ambilRefAkhir(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT REF_USER FROM REFERENCE ORDER BY ID_REF DESC LIMIT 1;", null);
        cursor.moveToFirst();
        ArrayList<String> ref_id_akhir = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            ref_id_akhir.add(cursor.getString(cursor.getColumnIndexOrThrow("REF_USER")));
            cursor.moveToNext();
        }
        cursor.close();
        return ref_id_akhir.toArray(new String[ref_id_akhir.size()]);
    }

    public String[] transferReference(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID FROM USER WHERE USERNAME = ? AND PASSWORD = ?", new String[]{username,password});
        cursor.moveToFirst();
        ArrayList<String> user_id_ref_sess = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            user_id_ref_sess.add(cursor.getString(cursor.getColumnIndexOrThrow("ID")));
            cursor.moveToNext();
        }
        cursor.close();
        return user_id_ref_sess.toArray(new String[user_id_ref_sess.size()]);

    }

    public String[] getUserData(String id_ref){
        int id_int = Integer.parseInt(id_ref);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_DATA WHERE USER_ID = "+id_int, null);
        cursor.moveToFirst();
        ArrayList<String> user_id_get = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            user_id_get.add(cursor.getString(cursor.getColumnIndexOrThrow("ID_PROFIL")));
            user_id_get.add(cursor.getString(cursor.getColumnIndexOrThrow("NAMA")));
            user_id_get.add(cursor.getString(cursor.getColumnIndexOrThrow("EMAIL")));
            user_id_get.add(cursor.getString(cursor.getColumnIndexOrThrow("NOMOR")));
            user_id_get.add(cursor.getString(cursor.getColumnIndexOrThrow("PESAN")));
            cursor.moveToNext();
        }
        cursor.close();
        return user_id_get.toArray(new String[user_id_get.size()]);
    }

    public String[] getUserRiwayat(String id_ref){
        int id_int = Integer.parseInt(id_ref);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RIWAYAT WHERE USER_ID_RP = "+id_int+" ORDER BY ID_RIWAYAT DESC LIMIT 5;", null);
        cursor.moveToFirst();
        ArrayList<String> user_id_rp = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            user_id_rp.add(cursor.getString(cursor.getColumnIndexOrThrow("NOMOR_RIWAYAT")));
            user_id_rp.add(cursor.getString(cursor.getColumnIndexOrThrow("WAKTU")));

            cursor.moveToNext();
        }
        cursor.close();
        return user_id_rp.toArray(new String[user_id_rp.size()]);
    }

    public long insertMap(String latitude, String longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME2);
        db.execSQL(Constants.CREATE_TABLE_MAPS);

        ContentValues contentValuesMap = new ContentValues();
        contentValuesMap.put(Constants.C_MAPS_LAT, latitude);
        contentValuesMap.put(Constants.C_MAPS_LONG, longitude);

        long id_map = db.insert(Constants.TABLE_NAME2, null,contentValuesMap);

        db.close();

        return id_map;
    }

    public long insertRiwayat(String nomor_rp, String waktu, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMOR_RIWAYAT",nomor_rp);
        contentValues.put("WAKTU",waktu);
        contentValues.put("USER_ID_RP",user_id);

        long rp = db.insert("RIWAYAT",null,contentValues);

        db.close();

        return rp;
    }

    public long insertR_Penyakit(String tgl_lahir, String gol_darah, String r_penyakit, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TGL_LAHIR",tgl_lahir);
        contentValues.put("GOL_DARAH",gol_darah);
        contentValues.put("R_PENYAKIT",r_penyakit);
        contentValues.put("USER_ID_PENYAKIT",user_id);

        long rp2 = db.insert("RIWAYAT_PENYAKIT",null,contentValues);

        db.close();

        return rp2;
    }

    public String[] getUserRiwayatPenyakit(String id_ref){
        int id_int = Integer.parseInt(id_ref);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RIWAYAT_PENYAKIT WHERE USER_ID_PENYAKIT = "+id_int, null);
        cursor.moveToFirst();
        ArrayList<String> user_id_riwayat = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            user_id_riwayat.add(cursor.getString(cursor.getColumnIndexOrThrow("ID_R_PENYAKIT")));
            user_id_riwayat.add(cursor.getString(cursor.getColumnIndexOrThrow("TGL_LAHIR")));
            user_id_riwayat.add(cursor.getString(cursor.getColumnIndexOrThrow("GOL_DARAH")));
            user_id_riwayat.add(cursor.getString(cursor.getColumnIndexOrThrow("R_PENYAKIT")));

            cursor.moveToNext();
        }
        cursor.close();
        return user_id_riwayat.toArray(new String[user_id_riwayat.size()]);
    }

    public long insertContact(String image, String name, String phone, String addedTime, String updatedTime){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.C_IMAGE, image);
        contentValues.put(Constants.C_NAMA, name);
        contentValues.put(Constants.C_TELEPON, phone);
        contentValues.put(Constants.C_ADDED_TIME, addedTime);
        contentValues.put(Constants.C_UPDATED_TIME, updatedTime);

        long id = db.insert(Constants.TABLE_NAME1,null,contentValues);

        db.close();

        return id;
    }

    public long userData(String nama, String email, String nomor, String pesan, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("NAMA",nama);
        contentValues.put("EMAIL",email);
        contentValues.put("NOMOR",nomor);
        contentValues.put("PESAN",pesan);
        contentValues.put("USER_ID",user_id);

        long iud = db.insert("USER_DATA",null,contentValues);

        db.close();

        return iud;
    }

    public String[] ambilDataIdUser(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT ID FROM USER ORDER BY ID DESC LIMIT 1;", null);
        cursor.moveToFirst();
        ArrayList<String> user_id = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            user_id.add(cursor.getString(cursor.getColumnIndexOrThrow("ID")));
            cursor.moveToNext();
        }
        cursor.close();
        return user_id.toArray(new String[user_id.size()]);
    }

    public void updateUserData(String id, String nama, String email, String nomor, String pesan, String user_ref){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA",nama);
        contentValues.put("EMAIL",email);
        contentValues.put("NOMOR",nomor);
        contentValues.put("PESAN",pesan);
        contentValues.put("USER_ID",user_ref);

        db.update("USER_DATA",contentValues,"ID_PROFIL =?",new String[]{id});

        db.close();

    }

    public void updateUserPenyakit(String id, String tgl_lahir, String gol_darah, String r_penyakit, String user_ref){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TGL_LAHIR",tgl_lahir);
        contentValues.put("GOL_DARAH",gol_darah);
        contentValues.put("R_PENYAKIT",r_penyakit);
        contentValues.put("USER_ID_PENYAKIT",user_ref);


        db.update("RIWAYAT_PENYAKIT",contentValues,"ID_R_PENYAKIT =?",new String[]{id});

        db.close();

    }
    public void updateContact(String id, String image, String name, String phone, String addedTime, String updatedTime){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.C_IMAGE, image);
        contentValues.put(Constants.C_NAMA, name);
        contentValues.put(Constants.C_TELEPON, phone);
        contentValues.put(Constants.C_ADDED_TIME, addedTime);
        contentValues.put(Constants.C_UPDATED_TIME, updatedTime);

        db.update(Constants.TABLE_NAME1,contentValues,Constants.C_ID+" =? ", new String[]{id});

        db.close();
    }

    public void deleteContact(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.TABLE_NAME1,"ID=?",new String[]{id});

        db.close();
    }

    public void deleteAllContact(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM "+Constants.TABLE_NAME1);
        db.close();
    }


    public ArrayList<ModelContact> getAllData(){
        ArrayList<ModelContact> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME1;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ModelContact modelContact = new ModelContact(
                    ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAMA)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_TELEPON)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME))
                );
                arrayList.add(modelContact);
            }while(cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public String[] ambilData(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM "+Constants.TABLE_NAME1, null);
        cursor.moveToFirst();
        ArrayList<String> nomor = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            nomor.add(cursor.getString(cursor.getColumnIndexOrThrow("TELEPON")));
            cursor.moveToNext();
        }
        cursor.close();
        return nomor.toArray(new String[nomor.size()]);

    }

    public String[] ambilDataMapLat(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM "+Constants.TABLE_NAME2, null);
        cursor.moveToFirst();
        ArrayList<String> map = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            map.add(cursor.getString(cursor.getColumnIndexOrThrow("MAPS_LATITUDE")));
            cursor.moveToNext();
        }
        cursor.close();
        return map.toArray(new String[map.size()]);
    }

    public String[] ambilDataMapLong(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM "+Constants.TABLE_NAME2, null);
        cursor.moveToFirst();
        ArrayList<String> map2 = new ArrayList<String>();
        while (!cursor.isAfterLast()){
            map2.add(cursor.getString(cursor.getColumnIndexOrThrow("MAPS_LONGITUDE")));
            cursor.moveToNext();
        }
        cursor.close();
        return map2.toArray(new String[map2.size()]);
    }
}

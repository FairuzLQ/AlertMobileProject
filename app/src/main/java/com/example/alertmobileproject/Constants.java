package com.example.alertmobileproject;

public class Constants {

    public static final String DATABASE_NAME = "ALERT_DB";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME1 = "TABEL_KONTAK";

    public static final String C_ID = "ID";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_NAMA = "NAMA";
    public static final String C_TELEPON = "TELEPON";
    public static final String C_ADDED_TIME = "ADDED_TIME";
    public static final String C_UPDATED_TIME= "UPDATED_TIME";

    //MAPS TABLE
    public static final String TABLE_NAME2 = "TABEL_MAPS";
    public static final String C_ID_MAPS = "ID_MAPS";
    public static final String C_MAPS_LAT = "MAPS_LATITUDE";
    public static final String C_MAPS_LONG = "MAPS_LONGITUDE";

    public static final String CREATE_TABLE_CONTACT = "CREATE TABLE "+TABLE_NAME1+
            "( "+ C_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_IMAGE + " TEXT, "
            + C_NAMA + " TEXT, "
            + C_TELEPON + " TEXT, "
            + C_ADDED_TIME + " TEXT, "
            + C_UPDATED_TIME + " TEXT "
            + " );";

    public static final String CREATE_TABLE_MAPS = "CREATE TABLE "+TABLE_NAME2+
            "( "+ C_ID_MAPS +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_MAPS_LAT +" TEXT, "
            + C_MAPS_LONG +" TEXT "
            +" );";
}

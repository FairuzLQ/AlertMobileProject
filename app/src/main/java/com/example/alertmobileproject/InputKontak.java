package com.example.alertmobileproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageActivity;
import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.databinding.CropImageActivityBinding;


public class InputKontak extends AppCompatActivity implements View.OnClickListener {

    private ImageView input_pic;
    private EditText nama_edit, telepon_edit;
    private Button simpan_kontak;
    private Boolean isEditMode;
    public CardView btnBack;

    private String id, nama, telepon, image;

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 200;
    private static final int IMAGE_FROM_GALLERY_CODE = 300;
    private static final int IMAGE_FROM_CAMERA_CODE = 400;

    private String[] cameraPermission;
    private String[] storagePermission;

    private CropImageActivity cropImage;
    Uri imageUri;

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kontak);

        dbHelper = new DBHelper(this);

        getSupportActionBar().hide();

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        input_pic = findViewById(R.id.input_pic);
        nama_edit = findViewById(R.id.nama_edit);
        telepon_edit = findViewById(R.id.telepon_edit);
        simpan_kontak = findViewById(R.id.simpan_kontak);
        btnBack = findViewById(R.id.btn_back12);
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode",false);

        if(isEditMode){
            id = intent.getStringExtra("ID");
            nama = intent.getStringExtra("NAME");
            telepon = intent.getStringExtra("PHONE");
            image = intent.getStringExtra("IMAGE");

            nama_edit.setText(nama);
            telepon_edit.setText(telepon);

            imageUri = Uri.parse(image);

            if (image.equals(""))
            {
                input_pic.setImageResource(R.drawable.ic_baseline_person_24);
            }else{
                input_pic.setImageURI(imageUri);
            }
        }
        else{

        }

        btnBack.setOnClickListener(this);

        simpan_kontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        input_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickerDialog();
            }
        });
    }

    private void showImagePickerDialog(){
        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pilih Sumber");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromCamera();
                    }
                }
                else if(which == 1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        }).create().show();
    }

    private void pickFromCamera() {
        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.TITLE,"IMAGE_TITLE");
        values.put(MediaStore.Images.Media.DESCRIPTION,"IMAGE_DESCRIPTION");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        startActivityForResult(cameraIntent,IMAGE_FROM_CAMERA_CODE);
    }

    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");

        startActivityForResult(galleryIntent,IMAGE_FROM_GALLERY_CODE);
    }

    private void saveData(){
            nama = nama_edit.getText().toString();
            telepon = telepon_edit.getText().toString();

            String timeStamp = ""+System.currentTimeMillis();



            if(!nama.isEmpty() || !telepon.isEmpty()){
                if(isEditMode){
                    dbHelper.updateContact(
                            ""+id,
                            ""+image,
                            ""+nama,
                            ""+telepon,
                            ""+timeStamp,
                            ""+timeStamp
                    );

                    Toast.makeText(getApplicationContext(), "Update Sukses "+id, Toast.LENGTH_SHORT).show();
                }else{
                    long id = dbHelper.insertContact(
                            ""+imageUri,
                            ""+nama,
                            ""+telepon,
                            ""+timeStamp,
                            ""+timeStamp
                    );

                    Toast.makeText(getApplicationContext(), "Inserted "+id, Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "Harap isi data...", Toast.LENGTH_SHORT).show();
            }
    }

    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result & result1;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_PERMISSION_CODE);
    }

    private boolean checkStoragePermission(){
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result1;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_PERMISSION_CODE:
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(getApplicationContext(), "Aing butuh izin atuh...", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_PERMISSION_CODE:
                if(grantResults.length > 0){
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(storageAccepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(getApplicationContext(), "Aing butuh izin penyimpanan atuh...", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_FROM_GALLERY_CODE){
                imageUri = data.getData();
                input_pic.setImageURI(imageUri);
            }else if(requestCode == IMAGE_FROM_CAMERA_CODE){
                input_pic.setImageURI(imageUri);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_back12:
                i = new Intent(InputKontak.this, Profil.class);
                startActivity(i);
                break;
        }
    }
}
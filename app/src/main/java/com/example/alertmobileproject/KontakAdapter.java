package com.example.alertmobileproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apachat.swipereveallayout.core.SwipeLayout;

import java.util.ArrayList;

public class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList<ModelContact> contactList;
    private DBHelper dbHelper;

    public KontakAdapter(Context context, ArrayList<ModelContact> contactList) {
        this.context = context;
        this.contactList = contactList;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_kontak,parent,false);
        ContactViewHolder vh = new ContactViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        ModelContact modelContact = contactList.get(position);

        String id = modelContact.getId();
        String image = modelContact.getImage();
        String name = modelContact.getName();
        String phone = modelContact.getPhone();

        holder.contactName.setText(name);
        if(image.equals("")){
            holder.contactImage.setImageResource(R.drawable.ic_baseline_person_24);
        }else{
            holder.contactImage.setImageURI(Uri.parse(image));
        }

        //mengatur click geser pada kontak
        holder.contactGeser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //mengatur click dan menampilkan detail kontak
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ContactDetails.class);
                intent.putExtra("contactId",id);
                context.startActivity(intent);
            }
        });

        holder.contactEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InputKontak.class);
                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("IMAGE",image);
                intent.putExtra("PHONE",phone);

                intent.putExtra("isEditMode",true);

                context.startActivity(intent);
            }
        });

        holder.contactDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteContact(id);

                ((KontakDarurat)context).onResume();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView contactImage,contactGeser;
        TextView contactName,contactEdit,contactDelete;
        RelativeLayout relativeLayout;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            contactImage = itemView.findViewById(R.id.output_pic_kontak);
            contactGeser = itemView.findViewById(R.id.geser_kiri);
            contactName = itemView.findViewById(R.id.output_nama_kontak);
            contactEdit = itemView.findViewById(R.id.contact_edit);
            contactDelete = itemView.findViewById(R.id.contact_delete);
            relativeLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

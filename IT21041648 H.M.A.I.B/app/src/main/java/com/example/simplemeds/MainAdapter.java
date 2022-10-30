package com.example.simplemeds;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<car,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<car> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull car model) {
        holder.type.setText(model.getType());
        holder.model.setText(model.getModel());
        holder.location.setText(model.getBrand());
        holder.price.setText(model.getPrice());

        Glide.with(holder.curl.getContext())
                .load(model.getCurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.curl);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.curl.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updatepopup))
                        .setExpanded(true, 1600)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText type = view.findViewById(R.id.txtType);
                EditText mdel = view.findViewById(R.id.txtModel);
                EditText location = view.findViewById(R.id.txtLocation);
                EditText price = view.findViewById(R.id.txtPrice);
                EditText curl = view.findViewById(R.id.txtImageUrl);

                Button btnUpdate = view. findViewById(R.id.btnUpdate);

                type.setText(model.getType());
                mdel.setText(model.getModel());
                location.setText(model.getLocation());
                price.setText(model.getPrice());
                curl.setText(model.getCurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("type",type.getText().toString());
                        map.put("model",mdel.getText().toString());
                        map.put("location",location.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("curl",curl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Cars")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.type.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.type.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.location.getContext());
                builder.setTitle("Are you sure .?");
                builder.setMessage("Deleted Car");


                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        FirebaseDatabase.getInstance().getReference().child("Cars")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        Toast.makeText(holder.location.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.show();

            }
        });




    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.caritem,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView curl;
        TextView type, model, location, price, description;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            curl = (CircleImageView)itemView.findViewById(R.id.img1);
            type = (TextView) itemView.findViewById(R.id.typetext);
            model = (TextView)itemView.findViewById(R.id.modeltext);
            location = (TextView)itemView.findViewById(R.id.locationtext);
            price = (TextView)itemView.findViewById(R.id.pricetext);
            description = (TextView)itemView.findViewById(R.id.descriptiontext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}
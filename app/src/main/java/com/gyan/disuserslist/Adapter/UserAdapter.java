package com.gyan.disuserslist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyan.disuserslist.Model.Data;
import com.gyan.disuserslist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private List<Data> userList;
    Context context;

    public UserAdapter(List<Data> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.child,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        Data dataModel=userList.get(position);
        Picasso.get().load(dataModel.getAvatar()).placeholder(R.drawable.avatar).into(holder.imageView);
        holder.id.setText(dataModel.getId().toString());
        holder.fName.setText(dataModel.getFirst_name());
        holder.lName.setText(dataModel.getLast_name());
        holder.email.setText(dataModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView id,fName,lName,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.userImage);
            id=itemView.findViewById(R.id.tvId);
            fName=itemView.findViewById(R.id.tvFName);
            lName=itemView.findViewById(R.id.tvLName);
            email=itemView.findViewById(R.id.tvEmail);
        }
    }
}

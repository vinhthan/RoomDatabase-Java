package com.example.roomdatabaseandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaseandroid.database.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> list;
    private IClickItem iClickItem;

    public UserAdapter(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    public void setData(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    interface IClickItem {
        void delete(User user);
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.UserViewHolder holder, int position) {
        User user = list.get(position);
        if (user == null) {
            return;
        }
        holder.tvTitle.setText(user.getName());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItem.delete(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        Button btnDelete;
        public UserViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}

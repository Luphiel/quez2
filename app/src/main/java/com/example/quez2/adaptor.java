package com.example.quez2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class adaptor extends RecyclerView.Adapter<adaptor.MyViewHolder>{


    Context context;

    ArrayList<Coffee_model> userArraylist;

    public adaptor(Context context, ArrayList<Coffee_model> userArraylist) {
        this.context = context;
        this.userArraylist = userArraylist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.r_views_rows,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


     Coffee_model user = userArraylist.get(position);


     holder.Date.setText(user.Date);
     holder.write.setText(user.write);
    }

    @Override
    public int getItemCount() {
        return userArraylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView Date, write;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            Date = itemView.findViewById(R.id.Date);
            write = itemView.findViewById(R.id.write);


        }
    }
}

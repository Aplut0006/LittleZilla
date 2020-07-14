package app.littlezilla.littlezilla.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import app.littlezilla.littlezilla.Activities.AddActivity;
import app.littlezilla.littlezilla.Activities.DivideActivity;
import app.littlezilla.littlezilla.Activities.HindiActivity;
import app.littlezilla.littlezilla.Activities.MainActivity;
import app.littlezilla.littlezilla.Activities.MultiplicationActivity;
import app.littlezilla.littlezilla.Activities.SubtractActivity;
import app.littlezilla.littlezilla.Activities.TableActivity;
import app.littlezilla.littlezilla.Activities.ZerotoNineActivity;
import app.littlezilla.littlezilla.R;

public class MathsCategoryAdapter extends RecyclerView.Adapter<MathsCategoryAdapter.MathsCategoryViewHolder> {
    List<String> catlist;
    Context context;
     LayoutInflater inflater;
    public MathsCategoryAdapter(List<String> catlist, Context context) {
        this.catlist = catlist;
        this.context = context;
      this.inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MathsCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.maths_item,parent,false);
        return new MathsCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MathsCategoryViewHolder holder, final int position) {
         holder.textView.setText(catlist.get(position));

        Random rnd=new Random();
        //difrent color for each item
        int color= Color.argb(255,rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
       holder.layout.setBackgroundColor(color);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(position==0){
                  Intent intent=new Intent(holder.itemView.getContext(),ZerotoNineActivity.class);
                  holder.itemView.getContext().startActivity(intent);

               }
               else if(position==1){
                   Intent intent=new Intent(holder.itemView.getContext(), AddActivity.class);
                   holder.itemView.getContext().startActivity(intent);

               }
               else if(position==2){
                   Intent intent=new Intent(holder.itemView.getContext(), SubtractActivity.class);
                   holder.itemView.getContext().startActivity(intent);

               }
               else if(position==3){
                   Intent intent=new Intent(holder.itemView.getContext(), MultiplicationActivity.class);
                   holder.itemView.getContext().startActivity(intent);

               }
               else if(position==4){
                   Intent intent=new Intent(holder.itemView.getContext(), DivideActivity.class);
                   holder.itemView.getContext().startActivity(intent);

               }
               else{
                   Intent intent=new Intent(holder.itemView.getContext(), TableActivity.class);
                   holder.itemView.getContext().startActivity(intent);

               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return catlist.size();
    }

    public class MathsCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView textView;LinearLayout layout;
        public MathsCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
           textView=itemView.findViewById(R.id.textview);
          layout=itemView.findViewById(R.id.layout);

        }
    }


}

package com.example.recycleviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder>{
    private List<Animal> mAnimalList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View animalView;
        ImageView animalImage;
        EditText animalName;
        TextView animalNo;
        public ViewHolder(View view) {
            super(view);
            animalView=view;
            animalImage=(ImageView)view.findViewById(R.id.animal_image);
            animalName=(EditText)view.findViewById(R.id.animal_name);
            animalNo=(TextView)view.findViewById(R.id.animal_no);
        }
    }

    //构造函数（传值）
    public AnimalAdapter(List<Animal> animalList){
        mAnimalList=animalList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.animalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Animal animal = mAnimalList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + animal.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Animal animal = mAnimalList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + animal.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    //真正赋值数据
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal=mAnimalList.get(position);
        holder.animalImage.setImageResource(animal.getImageId());
        holder.animalName.setText(animal.getName());
        holder.animalNo.setText(String.valueOf(position+1));
    }

    @Override
    //返回子项数目
    public int getItemCount() {
        return mAnimalList.size();
    }
}

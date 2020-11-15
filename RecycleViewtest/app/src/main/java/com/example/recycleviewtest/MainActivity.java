package com.example.recycleviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Animal> animalList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnimal();   //初始化数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //用于实现瀑布流布局（两参数：指定布局列数，指定布局的排列方向）
        //StaggeredGridLayoutManager.VERTICAL  让布局纵向排列
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        AnimalAdapter adapter=new AnimalAdapter(animalList);    //适配器
        recyclerView.setAdapter(adapter);
    }

    private void initAnimal() {
        for(int i=0;i<2;i++){
            Animal frog=new Animal(getRandomLengthName("Frog"),R.drawable.frog);
            animalList.add(frog);
            Animal giraffe=new Animal(getRandomLengthName("Giraffe"),R.drawable.giraffe);
            animalList.add(giraffe);
            Animal monkey=new Animal(getRandomLengthName("Monkey"),R.drawable.monkey);
            animalList.add(monkey);
            Animal panda=new Animal(getRandomLengthName("Panda"),R.drawable.panda);
            animalList.add(panda);
            Animal pig=new Animal(getRandomLengthName("Pig"),R.drawable.pig);
            animalList.add(pig);
            Animal sheep=new Animal(getRandomLengthName("Sheep"),R.drawable.sheep);
            animalList.add(sheep);
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
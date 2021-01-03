package com.example.student.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.student.R;
import com.example.student.tools.Student;
import com.example.student.tools.StudentAdapter;
import com.example.student.tools.myDatabaseHelper;
import java.util.ArrayList;
import java.util.List;

//展示学生信息的activity
public class studentInfo_activity extends Activity {
    private List<Student> studentList = new ArrayList<Student>();
    private myDatabaseHelper dbHelper;
    private ListView listView;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.studentinfo_activity_layout);
        dbHelper = myDatabaseHelper.getInstance(this);
        initStudent();  //查询所有学生信息
        adapter = new StudentAdapter(studentInfo_activity.this, R.layout.student_item, studentList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Student student = studentList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(studentInfo_activity.this);
                LayoutInflater factory = LayoutInflater.from(studentInfo_activity.this);
                final View textEntryView = factory.inflate(R.layout.student_info_layout, null);
                builder.setView(textEntryView);
                builder.setTitle("请选择相关操作");

                //显示详细学生信息
                Button selectInfo = (Button) textEntryView.findViewById(R.id.student_info_select);
                selectInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder select_builder = new AlertDialog.Builder(studentInfo_activity.this);
                        select_builder.setTitle("学生详细信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + student.getName() + "\n");
                        sb.append("学号：" + student.getId() + "\n");
                        sb.append("手机号：" + student.getNumber() + "\n");
                        int math = student.getMathScore();//数学成绩
                        sb.append("数学成绩：" + math + "\n");
                        int chinese = student.getChineseScore();
                        sb.append("语文成绩：" + chinese + "\n");
                        int english = student.getEnglishScore();
                        sb.append("英语成绩：" + english + "\n");
                        int sum = math + chinese + english;//总成绩
                        sb.append("总成绩：" + sum + "\n");
                        sb.append("排名："+student.getOrder()+"\n");
                        select_builder.setMessage(sb.toString());
                        select_builder.create().show();
                    }
                });

                //删除学生信息
                Button delete_info = (Button) textEntryView.findViewById(R.id.student_info_delete);
                delete_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder delete_builder = new AlertDialog.Builder(studentInfo_activity.this);
                        delete_builder.setTitle("警告！！！！");
                        delete_builder.setMessage("您将删除该学生信息，此操作不可逆，请谨慎操作！");

                        delete_builder.setNegativeButton("取消", null);
                        delete_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from student where id=?", new String[]{student.getId()});
                                studentList.remove(position);  //删除
                                adapter.notifyDataSetChanged();  //刷新列表
                            }
                        });
                        delete_builder.create().show();
                    }
                });

                //修改学生信息,通过intent传递旧学生信息
                Button update_info = (Button) textEntryView.findViewById(R.id.student_info_update);
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到添加学生信息的界面,通过intent传递数据
                        Intent intent = new Intent(studentInfo_activity.this, addStudent_info_activity.class);
                        intent.putExtra("haveData", "true");
                        intent.putExtra("name", student.getName());
                        intent.putExtra("sex", student.getSex());
                        intent.putExtra("id", student.getId());
                        intent.putExtra("number", student.getNumber());
                        intent.putExtra("password", student.getPassword());
                        intent.putExtra("mathScore", student.getMathScore());
                        intent.putExtra("chineseScore", student.getChineseScore());
                        intent.putExtra("englishScore", student.getEnglishScore());
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
    }

    //初始化学生信息
    private void initStudent() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from student order by id", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            int mathScore = cursor.getInt(cursor.getColumnIndex("mathScore"));
            int chineseScore = cursor.getInt(cursor.getColumnIndex("chineseScore"));
            int englishScore = cursor.getInt(cursor.getColumnIndex("englishScore"));
            int order=cursor.getInt(cursor.getColumnIndex("ranking"));
            studentList.add(new Student(name,sex,id,number,password,mathScore,chineseScore, englishScore,order));
        }
        cursor.close();
    }
}

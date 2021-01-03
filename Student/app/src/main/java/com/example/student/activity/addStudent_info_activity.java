package com.example.student.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.student.R;
import com.example.student.tools.myDatabaseHelper;

 //添加学生信息的界面,修改学生信息的界面
public class addStudent_info_activity extends Activity {

    private EditText name;
    private EditText sex;
    private EditText id;
    private EditText number;
    private EditText password;
    private EditText math;
    private EditText chinese;
    private EditText english;
    private String oldID;  //用于防止修改信息时将ID修改
    private Button sure;  //确定按钮
    private myDatabaseHelper dbHelper;
    Intent oldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_student_info_layout);

        name = (EditText) findViewById(R.id.add_student_layout_name);
        sex = (EditText) findViewById(R.id.add_student_layout_sex);
        id = (EditText) findViewById(R.id.add_student_layout_id);
        number = (EditText) findViewById(R.id.add_student_layout_number);
        password = (EditText) findViewById(R.id.add_student_layout_password);
        math = (EditText) findViewById(R.id.add_student_layout_math);
        chinese = (EditText) findViewById(R.id.add_student_layout_chinese);
        english = (EditText) findViewById(R.id.add_student_layout_english);

        dbHelper = myDatabaseHelper.getInstance(this);

        oldData = getIntent();
        if (oldData.getStringExtra("haveData").equals("true")) {
            initInfo();  //恢复旧数据
        }

        sure = (Button) findViewById(R.id.add_student_layout_sure);
        //将数据插入数据库
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //id,name,sex都不能为空
                String name_ = name.getText().toString();
                String sex_ = sex.getText().toString();
                String id_ = id.getText().toString();
                String number_ = number.getText().toString();
                String password_ = password.getText().toString();
                String mathScore = math.getText().toString();
                String chineseScore = chinese.getText().toString();
                String englishScore = english.getText().toString();

                if (!TextUtils.isEmpty(id_) && !TextUtils.isEmpty(name_) && !TextUtils.isEmpty(sex_)) {
                    if (sex_.matches("[F|M]")) {   //指定性别输入只能'F'/'M'
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.beginTransaction();    //开启事务
                        db.execSQL("delete from student where id=?", new String[]{oldID});//删除旧数据
                        //判断学号是否重复
                        Cursor cursor = db.rawQuery("select * from student where id=?", new String[]{id_});
                        if (cursor.moveToNext()) {
                            Toast.makeText(addStudent_info_activity.this, "已有学生使用该学号,请重新输入", Toast.LENGTH_SHORT).show();
                        } else {
                            db.execSQL("insert into student(name,sex,id,number,password,mathScore,chineseScore,englishScore) values(?,?,?,?,?,?,?,?)",
                                    new String[]{name_,sex_,id_,number_,password_,mathScore,chineseScore,englishScore,});
                            db.setTransactionSuccessful();//事务执行成功
                            db.endTransaction();//结束事务
                            Intent intent = new Intent(addStudent_info_activity.this, admin_activity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(addStudent_info_activity.this, "请输入正确的性别信息（F/M）", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addStudent_info_activity.this, "姓名，学号，性别均不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //恢复旧数据
    private void initInfo() {
        String oldName = oldData.getStringExtra("name");
        name.setText(oldName);
        String oldSex = oldData.getStringExtra("sex");
        sex.setText(oldSex);
        String oldId = oldData.getStringExtra("id");
        oldID = oldId;
        id.setText(oldId);
        String oldNumber = oldData.getStringExtra("number");
        number.setText(oldNumber);
        String oldPassword = oldData.getStringExtra("password");
        password.setText(oldPassword);
        int mathScore = oldData.getIntExtra("mathScore", 0);
        math.setText(String.valueOf(mathScore));
        int chineseScore = oldData.getIntExtra("chineseScore", 0);
        chinese.setText(String.valueOf(chineseScore));
        int englishScore = oldData.getIntExtra("englishScore", 0);
        english.setText(String.valueOf(englishScore));
    }
}

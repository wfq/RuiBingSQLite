package com.chen.ellen.ruibingsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chen.ellen.ruibingsqlite.ruibingsqlite.RuiBingLibrary;
import com.chen.ellen.ruibingsqlite.ruibingsqlite.RuiBingSQLite;
import com.chen.ellen.ruibingsqlite.ruibingsqlite.RuiBingTable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注意库主demo中没有进行动态文件读写权限申请，如运行有问题，请添加文件读写权限

        /**
         * 创建库的流程
         */
        //1.新建一个RuiBingLibrerary对象，构造函数中传入数据库的名字
        RuiBingLibrary ruiBingLibrary = new RuiBingLibrary("student_library");
        //2.新建一个RuiBingSQLite对象，在构造函数中分别传入上下文，步骤1中的RuiBingLibrerary对象,以及库的版本
        RuiBingSQLite ruiBingSQLite = new RuiBingSQLite(this,ruiBingLibrary,1);
        //3.调用步骤2中的RuiBingSQLite对象的open方法创建库
        ruiBingSQLite.open();

        /**
         * 创建表流程
         */
        //1.新建一个RuiBingTable对象，构造函数中传入表的名字以及表关联的类对应的class对象
        RuiBingTable<Student> studentRuiBingTable = new RuiBingTable<>("student_table1",Student.class);
        //2.通过RuiBingSQLite对象的createTable方法，传入要创建的表对象RuiBingTable对象即可创建表
        ruiBingSQLite.createTable(studentRuiBingTable);

        /**
         * 表的增删改查
         */

        //增加数据，可以单条数据添加，也可以多条数据添加

        //添加单条数据,通过RuiBingSQLite对象的saveData方法，里面需要传入两个参数，一个
        // 是RuiBingTable对象代表要操作的表是哪一个，另外一个则是具体的数据类对象
        Student student = new Student();
        student.setAge(22);
        student.setMan(false);
        student.setName("方方");
        student.setXueHao("10101");
        ruiBingSQLite.saveData(studentRuiBingTable,student);

        //添加多条数据,不用库主说吧，意思应该懂哈
        Student student1 = new Student("方方1","10102",16,true);
        Student student2 = new Student("方方2","10103",17,false);
        Student student3 = new Student("方方3","10104",18,false);
        Student student4 = new Student("方方4","10105",19,true);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        ruiBingSQLite.saveData(studentRuiBingTable,students);

        //删除数据:删除 name = 方方3 的数据，目前仅支持单条件，多条件很快加上
        ruiBingSQLite.deleteData(studentRuiBingTable, new Student("方方3","10104",18,false),"name");

        //修改数据：修改 name = 方方3 的数据，目前仅支持单条件，多条件很快加上
        ruiBingSQLite.upDateData(studentRuiBingTable, new Student("方方3","10104",18,false),
                new Student("狼哥","10105",10000,true),"name");

        //查询数据,目前仅支持单条件，多条件很快加上,后期会加上值域查询，尽情期待
        List<Student> serachStudents = ruiBingSQLite.serachData(studentRuiBingTable,
                new Student("方方1","10104",18,false),
                "name"
        );

        //获取某个表的所有数据
        List<Student> allData = ruiBingSQLite.getAllData(studentRuiBingTable);

    }
}

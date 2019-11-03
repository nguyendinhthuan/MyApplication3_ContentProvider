package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    EditText edtMa , edtTieuDe,edtTacGia;
    Button btnSave,btnSelect,btnExit,btnUpdate,btnDelete;
    GridView gvDisplay;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        edtMa = (EditText)findViewById(R.id.edt_Ma);
        edtTieuDe = (EditText)findViewById(R.id.edt_Address);
        edtTacGia = (EditText)findViewById(R.id.edt_Email);
        gvDisplay = (GridView)findViewById(R.id.gv_display);
        dbHelper = new DBHelper(this);

        btnSave =(Button)findViewById(R.id.btn_Save);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Book book = new Book();
//                book.setId_book(Integer.parseInt(edtMa.getText().toString()));
//                book.setTitle(edtTieuDe.getText().toString());
//                book.setId_author(Integer.parseInt(edtTacGia.getText().toString()));
//                if(dbHelper.insertBook(book)>0)
//                    Toast.makeText(getApplicationContext(),"Đã luu thành công ",Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(getApplicationContext(),"Luu k thành công",Toast.LENGTH_SHORT).show();
//            }
//        });
        btnSelect = (Button)findViewById(R.id.btn_Select);
//        btnSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<String> list_string = new ArrayList<>();
//                ArrayList<Book> list_book = new ArrayList<>();
//                list_book = dbHelper.getAllBook();
//                for(Book b : list_book){
//                    list_string.add(b.getId_book()+"");
//                    list_string.add(b.getTitle()+"");
//                    list_string.add(b.getId_author()+"");
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookActivity.this,android.R.layout.simple_list_item_1,list_string);
//                gvDisplay.setAdapter(adapter);
//            }
//        });

        //Provider
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_string = new ArrayList<>();
                String uri = "content://com.example.myapplication";
                Uri book = Uri.parse(uri);
                Cursor cursor = getContentResolver().query(book,null,null,null,"title");
                if (cursor!=null){
                    cursor.moveToFirst();
                    do{
                        list_string.add(cursor.getInt(0)+"");
                        list_string.add(cursor.getString(1)+"");
                        list_string.add(cursor.getInt(2)+"");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookActivity.this,android.R.layout.simple_list_item_1,list_string);
                    gvDisplay.setAdapter(adapter);
                }
                else
                    Toast.makeText(getApplicationContext(),"Khong co",Toast.LENGTH_SHORT).show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id_book",edtMa.getText().toString());
                values.put("title",edtTieuDe.getText().toString());
                values.put("id_author",edtTacGia.getText().toString());
                String uri = "content://com.example.myapplication";
                Uri book = Uri.parse(uri);
                Uri insert_uri =getContentResolver().insert(book,values);
                Toast.makeText(getApplicationContext(),"Luu thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

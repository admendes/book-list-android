package pt.com.admendes.bookstore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.com.admendes.bookstore.R;
import pt.com.admendes.bookstore.databases.MyDatabaseHelper;
import pt.com.admendes.bookstore.model.ImageLinks;
import pt.com.admendes.bookstore.model.VolumeInfo;
import pt.com.admendes.bookstore.ui.adapter.RecyclerAdapter;

public class FavoritesActivity extends AppCompatActivity {


    private ArrayList<VolumeInfo> favoriteBookInfoArrayList;
    private EditText searchEdt;
    private ImageButton searchBtn;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        searchEdt = findViewById(R.id.idEdtSearchBooksFav);
        searchBtn = findViewById(R.id.idBtnSearchFav);
        myDB = new MyDatabaseHelper(FavoritesActivity.this);
        getBooksInfo();
    }

    private void getBooksInfo() {
        favoriteBookInfoArrayList = new ArrayList<>();
        Cursor cursor = myDB.readAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No favorites", Toast.LENGTH_SHORT).show();
        }
        else  {
            while (cursor.moveToNext()) {
                List<String> authors=new ArrayList<String>();
                String[] words = cursor.getString(3).split(", ");
                for (int i = 0; i< words.length; i++) {
                    authors.add(words[i]);
                }

                ImageLinks links = new ImageLinks(cursor.getString(7), cursor.getString(8));
                VolumeInfo volumeInfo = new VolumeInfo(
                        cursor.getString(1),
                        cursor.getString(2),
                        authors,
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        links,
                        cursor.getString(9)
                );

                favoriteBookInfoArrayList.add(volumeInfo);
                RecyclerAdapter adapter = new RecyclerAdapter(FavoritesActivity.this, favoriteBookInfoArrayList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(FavoritesActivity.this, 2);
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rViewFav);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(adapter);
            }
        }
    }
}
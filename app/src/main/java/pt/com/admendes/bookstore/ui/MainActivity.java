package pt.com.admendes.bookstore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.ArrayList;

import pt.com.admendes.bookstore.R;
import pt.com.admendes.bookstore.model.SearchResult;
import pt.com.admendes.bookstore.model.Volume;
import pt.com.admendes.bookstore.model.VolumeInfo;
import pt.com.admendes.bookstore.ui.adapter.RecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ArrayList<VolumeInfo> bookInfoArrayList;
    private EditText searchEdt;
    private ImageButton searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEdt = findViewById(R.id.idEdtSearchBooks);
        searchBtn = findViewById(R.id.idBtnSearch);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEdt.getText().toString().isEmpty()) {
                    searchEdt.setError("Please enter search query");
                    return;
                }
                getBooksInfo(searchEdt.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                //inside on click listner method we are calling a new activity and passing all the data of that item in next intent.
                Intent i = new Intent(getApplicationContext(), FavoritesActivity.class);

                //after passing that data we are starting our new  intent.
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBooksInfo(String query) {
        bookInfoArrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        mRequestQueue.getCache().clear();
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest booksObjrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                SearchResult searchResult;
                searchResult = gson.fromJson(response.toString(), SearchResult.class);

                for (Volume volume : searchResult.getItems()){
                    VolumeInfo volumeInfo = volume.getVolumeInfo();
                    bookInfoArrayList.add(volumeInfo);
                    RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this, bookInfoArrayList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rView);
                    mRecyclerView.setLayoutManager(gridLayoutManager);
                    mRecyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error found is " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(booksObjrequest);

    }
}
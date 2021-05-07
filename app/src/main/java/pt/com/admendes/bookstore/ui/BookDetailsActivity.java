package pt.com.admendes.bookstore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import pt.com.admendes.bookstore.R;
import pt.com.admendes.bookstore.databases.MyDatabaseHelper;
import pt.com.admendes.bookstore.model.ImageLinks;
import pt.com.admendes.bookstore.model.VolumeInfo;

public class BookDetailsActivity extends AppCompatActivity {

    private TextView txtTitleID;
    private TextView txtAuthorID;
    private TextView txtPagesID;
    private TextView txtPublisherID;
    private TextView txtDescriptionID;

    private ImageView imgViewID;
    private ImageLinks imgLinks;

    private String title;
    private String author;
    private int pages;
    private String publisher;
    private String publishedDate;
    private String description;
    private String previewLink;

    private Button btnBuy;
    private Button btnFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        txtTitleID = findViewById(R.id.txtTitle);
        txtAuthorID = findViewById(R.id.txtAuthor);
        txtPagesID = findViewById(R.id.txtPages);
        txtPublisherID = findViewById(R.id.txtPublisher);
        txtDescriptionID = findViewById(R.id.txtDescription);
        imgViewID = findViewById(R.id.imgCover);
        btnBuy = findViewById(R.id.btnBuy);
        btnFavorite = findViewById(R.id.btnFavorite);

        VolumeInfo book = (VolumeInfo) getIntent().getSerializableExtra("book");

        title = book.getTitle();
        if (book.getAuthors() != null)
            author = TextUtils.join(", ", book.getAuthors());
        else author = "Unknown Author";
        publisher = book.getPublisher();
        publishedDate = book.getPublishedDate();
        pages = book.getPageCount();
        description = book.getDescription();
        imgLinks = book.getImageLinks();
        previewLink = book.getPreviewLink();
        System.out.println(previewLink);

        txtTitleID.setText(title);
        txtAuthorID.setText(author);
        txtPublisherID.setText(publisher);
        txtPagesID.setText("Pages: " + Integer.toString(pages));
        txtDescriptionID.setText(description);

        if (imgLinks!=null && imgLinks.getThumbnail() != null) {
            Picasso.get().load(imgLinks.getThumbnail()).into(imgViewID);
        }
        else {
            imgViewID.setImageResource(R.drawable.ic_baseline_broken);
        }

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previewLink.isEmpty()){
                    Toast.makeText(BookDetailsActivity.this, "Error opening link", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(previewLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smallThumb;
                String thumb;
                if (imgLinks!=null && imgLinks.getThumbnail() != null && imgLinks.getSmallThumbnail() != null) {
                    smallThumb = imgLinks.getSmallThumbnail();
                    thumb = imgLinks.getThumbnail();
                }
                else {
                    smallThumb = null;
                    thumb = null;
                }
                MyDatabaseHelper myDB = new MyDatabaseHelper(BookDetailsActivity.this);
                myDB.addBook(title, description, author, publisher, publishedDate, pages,
                        smallThumb, thumb, previewLink);
            }
        });
    }
}
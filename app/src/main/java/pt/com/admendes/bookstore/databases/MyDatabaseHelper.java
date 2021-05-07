package pt.com.admendes.bookstore.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

import pt.com.admendes.bookstore.model.ImageLinks;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BooklLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_DESCRIPTION = "book_description";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PUBLISHER = "book_publisher";
    private static final String COLUMN_PUBLISHED_DATE = "book_published_date";
    private static final String COLUMN_PAGES = "book_pages";
    private static final String COLUMN_IMAGELINKS_SMALLTHUMBNAIL = "book_imagelinks_smallthumbnail";
    private static final String COLUMN_IMAGELINKS_THUMBNAIL = "book_imagelinks_thumbnail";
    private static final String COLUMN_PREVIEWLINK = "book_previewlink";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_PUBLISHER + " TEXT, " +
                        COLUMN_PUBLISHED_DATE + " TEXT, " +
                        COLUMN_PAGES + " INTEGER, " +
                        COLUMN_IMAGELINKS_SMALLTHUMBNAIL + " TEXT, " +
                        COLUMN_IMAGELINKS_THUMBNAIL + " TEXT, " +
                        COLUMN_PREVIEWLINK + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }




    public void addBook(String title, String description, String author, String publisher, String publishedDate, int pages,
                        String smallThumbnail, String thumbnail, String previewLink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PUBLISHER, publisher);
        cv.put(COLUMN_PUBLISHED_DATE, publishedDate);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_IMAGELINKS_SMALLTHUMBNAIL, smallThumbnail);
        cv.put(COLUMN_IMAGELINKS_THUMBNAIL, thumbnail);
        cv.put(COLUMN_PREVIEWLINK, previewLink);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

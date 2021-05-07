package pt.com.admendes.bookstore;

import org.junit.Test;

import pt.com.admendes.bookstore.model.BookHttp;
import pt.com.admendes.bookstore.model.SearchResult;
import pt.com.admendes.bookstore.model.Volume;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void googleStoreApiTest() {
        BookHttp book = new BookHttp();
        SearchResult result = book.searchBook("Dominando o Android");

        //System.out.println("ss" + result.getItems());

        for (Volume strTemp : result.getItems()){

            System.out.println(strTemp.getVolumeInfo().getTitle());

        }
    }
}
package pt.com.admendes.bookstore.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pt.com.admendes.bookstore.model.VolumeInfo;

public class BookStoreViewModel extends ViewModel {

    private MutableLiveData<List<VolumeInfo>> _bookList = new MutableLiveData<List<VolumeInfo>>();

}

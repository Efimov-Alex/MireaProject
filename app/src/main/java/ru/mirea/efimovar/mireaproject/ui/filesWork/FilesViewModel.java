package ru.mirea.efimovar.mireaproject.ui.filesWork;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FilesViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public FilesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

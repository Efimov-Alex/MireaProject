package ru.mirea.efimovar.mireaproject.ui.firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirebaseViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public FirebaseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

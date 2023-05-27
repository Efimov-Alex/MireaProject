package ru.mirea.efimovar.mireaproject.ui.data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.efimovar.mireaproject.databinding.FragmentDataBinding;

public class DataFragment extends Fragment {

    private FragmentDataBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataViewModel dataViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);

        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textData;
        final TextView textView2 = binding.textData2;
        textView.setText("Android Studio - это официальная интегрированная среда разработки (IDE) " +
                "для разработки приложений для Android. Android Studio, основанная на мощном " +
                "редакторе кода и инструментах разработчика IntelliJ IDEA, предлагает еще больше " +
                "возможностей, повышающих производительность при создании приложений для Android.");

        textView2.setText("Material Design — дизайн-система для создания интерфейсов программного" +
                " обеспечения и приложений, разработанная компанией Google.");
   //     dataViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
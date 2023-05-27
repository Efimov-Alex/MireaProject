package ru.mirea.efimovar.mireaproject.ui.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.efimovar.mireaproject.R;
import ru.mirea.efimovar.mireaproject.WebViewFragment;
import ru.mirea.efimovar.mireaproject.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    private FragmentBrowserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BrowserViewModel browserViewModel =
                new ViewModelProvider(this).get(BrowserViewModel.class);

        binding = FragmentBrowserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView webView = root.findViewById(R.id.web_view_g);

        // Url of website is passed here
        webView.loadUrl("https://google.com/");

        // WebViewController is used
        webView.setWebViewClient(new WebViewFragment());

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
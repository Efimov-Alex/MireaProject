package ru.mirea.efimovar.mireaproject.ui.filesWork;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.efimovar.mireaproject.R;

import ru.mirea.efimovar.mireaproject.databinding.FragmentFilesBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentFilesBinding binding;

    private String fileName1 = "textNormal.txt";

    public FilesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilesFragment newInstance(String param1, String param2) {
        FilesFragment fragment = new FilesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FilesViewModel filesViewModel =
                new ViewModelProvider(this).get(FilesViewModel.class);

        binding = FragmentFilesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.textView1.setText("[ORIGINAL]:" + "\n");
        String testText = "Я учусь В РТУ МИРЭА";
        TextView originalTextView = binding.textViewOriginal;
        originalTextView.setText(testText + "\n");

        String text = (String) originalTextView.getText();


        FileOutputStream outputStream;
        try {
            outputStream = getActivity().openFileOutput(fileName1, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String textFromFile = getTextFromFile();

        // Set up secret key spec for 128-bit AES encryption and decryption
        SecretKeySpec sks = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("Crypto", "AES secret key spec error");
        }

        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(textFromFile.getBytes());
        } catch (Exception e) {
            Log.e("Crypto", "AES encryption error");
        }

        TextView encodedTextView = binding.textViewEncoded;
        binding.textView2.setText("[ENCODED]:" + "\n");
        encodedTextView.setText(Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

        // Decode the encoded data with AES
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            Log.e("Crypto", "AES decryption error");
        }

        TextView decodedTextView = binding.textViewDecoded;
        binding.textView3.setText("[DECODED]:" + "\n");
        decodedTextView.setText(new String(decodedBytes) + "\n");

        FloatingActionButton button = binding.floatingActionButton;

        String decodeText = (String) decodedTextView.getText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = getTextFromFile();

                Snackbar.make(view, "Текст: " + decodeText, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        return root;
    }


    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput(fileName1);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);

            return text;
        } catch (IOException ex) {
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
            }
        }
        return null;
    }
}
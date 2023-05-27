package ru.mirea.efimovar.mireaproject.ui.firebase;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import ru.mirea.efimovar.mireaproject.R;
import ru.mirea.efimovar.mireaproject.databinding.FragmentFilesBinding;
import ru.mirea.efimovar.mireaproject.databinding.FragmentFirebaseBinding;
import ru.mirea.efimovar.mireaproject.ui.filesWork.FilesViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirebaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirebaseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentFirebaseBinding binding;

    private final String host = "time.nist.gov"; // или time-a.nist.gov
    private final int port = 13;

    public FirebaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirebaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirebaseFragment newInstance(String param1, String param2) {
        FirebaseFragment fragment = new FirebaseFragment();
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
        FirebaseViewModel firebaseViewModel =
                new ViewModelProvider(this).get(FirebaseViewModel.class);

        binding = FragmentFirebaseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GetTimeTask timeTask = new GetTimeTask();
        timeTask.execute();

        return root;
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine(); // игнорируем первую строку
                timeResult = reader.readLine(); // считываем вторую строку
                Log.d(TAG,timeResult);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            binding.textView.setText(result);
        }
    }
}
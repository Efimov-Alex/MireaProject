package ru.mirea.efimovar.mireaproject.ui.sensor;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.mirea.efimovar.mireaproject.R;
import ru.mirea.efimovar.mireaproject.databinding.FragmentSensorBinding;
import ru.mirea.efimovar.mireaproject.databinding.FragmentWorkerBinding;
import ru.mirea.efimovar.mireaproject.ui.worker.WorkerViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorFragment extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentSensorBinding binding;

    private TextView magneticxTextView;
    private TextView magneticyTextView;
    private TextView magneticzTextView;
    private SensorManager sensorManager;
    private Sensor magneticSensor;

    private boolean isWork = false;

    private static final int REQUEST_CODE_PERMISSION = 100;

    SensorEvent event;

    public SensorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
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
        SensorViewModel sensorViewModel =
                new ViewModelProvider(this).get(SensorViewModel.class);

        binding = FragmentSensorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        magneticSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        int storagePermissionStatus = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);


        if (storagePermissionStatus
                == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
// Выполняется запрос к пользователь на получение необходимых разрешений
            ActivityCompat.requestPermissions(getActivity(), new String[] {android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_PERMISSION);
        }





        magneticxTextView = binding.textViewAzimuth;
        magneticyTextView = binding.textViewPitch;
        magneticzTextView = binding.textViewRoll;




        return root;
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magneticSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float valueX = event.values[0];
            float valueY = event.values[1];
            float valueZ = event.values[2];
            magneticxTextView.setText("Magnit field x: " + valueX);
            magneticyTextView.setText("Magnit field y: " + valueY);
            magneticzTextView.setText("Magnit field z: " + valueZ);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
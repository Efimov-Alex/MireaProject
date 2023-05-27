package ru.mirea.efimovar.mireaproject.ui.maps;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

import ru.mirea.efimovar.mireaproject.R;
import ru.mirea.efimovar.mireaproject.databinding.FragmentMapsBinding;


public class MapsFragment extends Fragment {
    private FragmentMapsBinding binding;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private MapView mapView = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    private String mParam1;
    private String mParam2;

    public MapsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        //  Configuration.getInstance().load(getApplicationContext(),PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(getActivity().getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  Configuration.getInstance().load(getActivity().getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));


        MapsViewModel mapsViewModel =
                new ViewModelProvider(this).get(MapsViewModel.class);

        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();






       // View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = (MapView) root.findViewById(R.id.mapView);

        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);

        CompassOverlay compassOverlay = new CompassOverlay(getActivity().getApplicationContext(), new
                InternalCompassOrientationProvider(getActivity().getApplicationContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);


        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(55.6631094, 37.4810317));
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getActivity().getApplicationContext(),"пр. Вернадского, 86А, Москва, Россия, 119571 - ТЦ Авеню",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker);

        marker.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

        marker.setTitle("пр. Вернадского, 86А, Москва, Россия, 119571 - ТЦ Авеню");


        Marker marker1 = new Marker(mapView);
        marker1.setPosition(new GeoPoint(55.7902891, 37.5308876));
        marker1.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker1, MapView mapView) {
                Toast.makeText(getActivity().getApplicationContext(),"Ходынский б-р, 4, Москва, Россия, 125167 - ТЦ Авиапарк",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker1);

        marker1.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

        marker1.setTitle("Ходынский б-р, 4, Москва, Россия, 125167 - ТЦ Авиапарк");


        Marker marker2 = new Marker(mapView);
        marker2.setPosition(new GeoPoint(55.730217, 37.483998));
        marker2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker2, MapView mapView) {
                Toast.makeText(getActivity().getApplicationContext(),"Кутузовский проспект, д.57, 3-й этаж. - ТЦ Океания",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker2);

        marker2.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

        marker2.setTitle("Кутузовский проспект, д.57, 3-й этаж. - ТЦ Океания");


        Marker marker3 = new Marker(mapView);
        marker3.setPosition(new GeoPoint(55.7050837, 37.6407830));
        marker3.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker3, MapView mapView) {
                Toast.makeText(getActivity().getApplicationContext(),"Автозаводская ул., 18, Москва, Россия, 115280 - ТЦ Ривьера",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker3);

        marker3.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

        marker3.setTitle("Автозаводская ул., 18, Москва, Россия, 115280 - ТЦ Ривьера");

        Marker marker4 = new Marker(mapView);
        marker4.setPosition(new GeoPoint(55.6779790, 37.4668880));
        marker4.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker4, MapView mapView) {
                Toast.makeText(getActivity().getApplicationContext(),"ул. Мичуринский Проспект, Олимпийская деревня, 3к1 - ТЦ Фестиваль",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker4);

        marker4.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));

        marker4.setTitle("ул. Мичуринский Проспект, Олимпийская деревня, 3к1 - ТЦ Фестиваль");


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getActivity().getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getActivity().getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));

        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
package me.jlucas.nemo.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import me.jlucas.nemo.Adapters.ImageAdapter;
import me.jlucas.nemo.Models.Image;
import me.jlucas.nemo.R;
import me.jlucas.nemo.Utils.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    ImageAdapter adapter;

    public GalleryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        List<Image> listImages = Image.getAllImages(getContext());
        final RecyclerView galleryGrid = (RecyclerView) view.findViewById(R.id.galleryGrid);
        galleryGrid.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        galleryGrid.setLayoutManager(layoutManager);

        this.adapter = new ImageAdapter(getContext(), listImages);
        galleryGrid.setAdapter(this.adapter);

        EditText categorySearch = (EditText) view.findViewById(R.id.categorySearchBox);
        categorySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                ImageAdapter adapter = (ImageAdapter) galleryGrid.getAdapter();
                Log.d("IMAGE_SEARCH", text.toString());
                if (text.length() > 0){
                    List<Image> searchImages = Image.getImagesByCategory(text.toString(), getContext());
                    adapter.updateData(searchImages);
                } else {
                    adapter.updateData(Image.getAllImages(getContext()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}

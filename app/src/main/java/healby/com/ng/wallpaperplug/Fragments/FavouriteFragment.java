package healby.com.ng.wallpaperplug.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import healby.com.ng.wallpaperplug.R;


public class FavouriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_favourite, container, false);
        return  view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

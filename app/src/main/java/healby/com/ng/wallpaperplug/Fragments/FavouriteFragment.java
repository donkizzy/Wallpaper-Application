package healby.com.ng.wallpaperplug.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import healby.com.ng.wallpaperplug.Adapters.PhotosAdapters;
import healby.com.ng.wallpaperplug.Models.Photos;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Utils.RealmController;


public class FavouriteFragment extends Fragment {

    @BindView(R.id.favourites_notification)
    TextView textView ;
    @BindView(R.id.favourites_recycler)
    RecyclerView recyclerView ;

    private PhotosAdapters adapters ;
    private List<Photos> photos = new ArrayList<>();

    private Unbinder unbinder ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_favourite, container, false);
        unbinder = ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapters =  new PhotosAdapters(getActivity(),photos);
        recyclerView.setAdapter(adapters);
        getPhotos();
        return  view;
    }

    private void getPhotos(){
        RealmController realmController = new RealmController();
        photos.addAll(realmController.getPhotos());
        if (photos.size() == 0){
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            adapters.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

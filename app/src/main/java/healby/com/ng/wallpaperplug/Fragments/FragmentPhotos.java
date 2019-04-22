package healby.com.ng.wallpaperplug.Fragments;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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
import healby.com.ng.wallpaperplug.Webservices.ApiInterface;
import healby.com.ng.wallpaperplug.Webservices.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPhotos extends Fragment {
    public static final String TAG = FragmentPhotos.class.getSimpleName();

    @BindView(R.id.progress_photos)
    ProgressBar progressBar;
    @BindView(R.id.fragment_photos_recylcerView)
    RecyclerView recyclerView ;

    private PhotosAdapters photosAdapters ;
    private List<Photos> photos = new ArrayList<>();
    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_fragment_photos, container, false);
        unbinder = ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photosAdapters  = new PhotosAdapters(getActivity(),photos);
        recyclerView.setAdapter(photosAdapters);
        showProgress(true);
        getPhotos();
        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event

    public void getPhotos(){
        ApiInterface apiInterface = ServiceGenerator.createServiceClass(ApiInterface.class);
        Call<List<Photos>> call  =  apiInterface.getPhotos();
        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (response.isSuccessful()){
                    photos.addAll(response.body());
                    photosAdapters.notifyDataSetChanged();
                }else{
                    Log.e(TAG,"Fail => "+response.message());
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Log.e(TAG,"Fail => "+t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean isShow){
        if (isShow){
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
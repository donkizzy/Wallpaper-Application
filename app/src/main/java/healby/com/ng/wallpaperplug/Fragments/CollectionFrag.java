package healby.com.ng.wallpaperplug.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import healby.com.ng.wallpaperplug.Adapters.PhotosAdapters;
import healby.com.ng.wallpaperplug.Models.Collection;
import healby.com.ng.wallpaperplug.Models.Photos;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Webservices.ApiInterface;
import healby.com.ng.wallpaperplug.Webservices.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionFrag extends Fragment {
    public static final String TAG =  CollectionFrag.class.getSimpleName();
    @BindView(R.id.fragment_collection_userName)
    TextView userName  ;
    @BindView(R.id.fragment_collection_description)
    TextView description ;
    @BindView(R.id.fragment_collection_user_avatar)
    CircleImageView avatar ;
    @BindView(R.id.fragment_collection_title)
    TextView title ;

    @BindView(R.id.fragment_collections_progress)
    ProgressBar progressBar ;

    @BindView(R.id.fragment_collectionRecyclerView)
    RecyclerView recyclerView ;

    private Unbinder unbinder ;

    private List<Photos> photosList =  new ArrayList<>();
    private PhotosAdapters photosAdapters ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_collection, container, false);
        unbinder   = ButterKnife.bind(this,view);

        LinearLayoutManager  linearLayoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        photosAdapters =  new PhotosAdapters(getActivity(),photosList) ;
        recyclerView.setAdapter(photosAdapters);

        showProgress(true);
        Bundle bundle  =getArguments();
        int collectionId = bundle.getInt("collectionId");
        getCollectionInformation(collectionId);
        getPhotosOfCollections(collectionId);
        return view;
    }


        private  void getCollectionInformation(int collectionId){
           ApiInterface apiInterface =  ServiceGenerator.createServiceClass(ApiInterface.class);
           Call<Collection> call = apiInterface.getInformationCollection(collectionId);
           call.enqueue(new Callback<Collection>() {
               @Override
               public void onResponse(Call<Collection> call, Response<Collection> response) {
                   if (response.isSuccessful()){
                       Collection collection = response.body();
                       title.setText(collection.getTitle());
                       description.setText(collection.getDescription());
                       userName.setText(collection.getUser().getUsername());
                       Glide.with(getActivity())
                               .load(collection.getUser().getProfileImage().getSmall())
                               .into(avatar);
                   }else {
                       Log.e(TAG,response.message());
                   }
                   showProgress(false);
               }

               @Override
               public void onFailure(Call<Collection> call, Throwable t) {
                   Log.e(TAG, "Fail => " + t.getMessage());
                   showProgress(false);
               }
           });


        }
        private void getPhotosOfCollections(int collectionId){
        ApiInterface apiInterface =  ServiceGenerator.createServiceClass(ApiInterface.class);
        Call<List<Photos>> call = apiInterface.getPhotosOfCollection(collectionId);
        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (response.isSuccessful()){
                   photosList.addAll(response.body());
                   photosAdapters.notifyDataSetChanged();
                }else {
                    Log.e(TAG,response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Log.e(TAG, "Fail => " + t.getMessage());
            }
        });

        }

    private void showProgress(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

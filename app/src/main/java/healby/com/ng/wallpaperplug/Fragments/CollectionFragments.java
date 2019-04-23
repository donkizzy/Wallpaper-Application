package healby.com.ng.wallpaperplug.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import healby.com.ng.wallpaperplug.Adapters.CollectionsAdapter;
import healby.com.ng.wallpaperplug.Models.Collection;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Utils.Functions;
import healby.com.ng.wallpaperplug.Webservices.ApiInterface;
import healby.com.ng.wallpaperplug.Webservices.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionFragments extends Fragment {

    public final String TAG = CollectionFragments.class.getSimpleName();
    @BindView(R.id.collection_grid)
    GridView gridView;
    @BindView(R.id.fragments_collection_progress)
    ProgressBar progressBar;
    private Unbinder unbinder;

    private CollectionsAdapter collectionsAdapter;
    private List<Collection> collections = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection_fragments, container, false);
        unbinder = ButterKnife.bind(this, view);
        collectionsAdapter = new CollectionsAdapter(getActivity(), collections);
        gridView.setAdapter(collectionsAdapter);
       showProgress(true);
        getCollections();
        return view;
    }

    @OnItemClick(R.id.collection_grid)
    public void setGridView(int position){
        Collection  collection =  collections.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("collectionId", collection.getId());
        CollectionFrag collectionFrag =  new CollectionFrag();
        collectionFrag.setArguments(bundle);
        Functions.changeMainFragmentWithBack(getActivity(),collectionFrag);

    }

    public void getCollections() {
        ApiInterface apiInterface = ServiceGenerator.createServiceClass(ApiInterface.class);
        Call<List<Collection>> call = apiInterface.getCollections();
        call.enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                if (response.isSuccessful()) {
                    collections.addAll(response.body());
                    collectionsAdapter.notifyDataSetChanged();


                } else {
                    Log.e(TAG, "Fail => " + response.message());
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                Log.e(TAG, "Fail => " + t.getMessage());
                showProgress(false);
            }
        });

    }

    private void showProgress(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
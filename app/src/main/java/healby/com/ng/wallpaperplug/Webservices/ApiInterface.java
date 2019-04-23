package healby.com.ng.wallpaperplug.Webservices;

import java.util.List;

import healby.com.ng.wallpaperplug.Models.Collection;
import healby.com.ng.wallpaperplug.Models.Photos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET("photos")
    Call<List<Photos>> getPhotos();

    @GET("collections/featured")
    Call<List<Collection>> getCollections() ;

    @GET("collections/{id}")
    Call<Collection> getInformationCollection(@Path("id") int id);

    @GET("collections/{id}/photos")
    Call<List<Photos>> getPhotosOfCollection(@Path("id") int id);

}

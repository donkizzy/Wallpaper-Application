package healby.com.ng.wallpaperplug.Webservices;

import java.util.List;

import healby.com.ng.wallpaperplug.Models.Photos;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("photos")
    Call<List<Photos>> getPhotos();

}

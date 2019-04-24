package healby.com.ng.wallpaperplug.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import healby.com.ng.wallpaperplug.Models.Photos;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Utils.Functions;
import healby.com.ng.wallpaperplug.Webservices.ApiInterface;
import healby.com.ng.wallpaperplug.Webservices.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class Fullscreen extends AppCompatActivity {
    @BindView(R.id.fullscreen_user_avatar)
    CircleImageView circleImageView ;
    @BindView(R.id.activity_fullscreen_image)
    ImageView fullscreenPhoto ;

    @BindView(R.id.fullscreen_photo_username)
    TextView username ;
    @BindView(R.id.floating_actionMenu)
    FloatingActionMenu floatingActionMenu;
    @BindView(R.id.floating_action_button_favourite)
    FloatingActionButton favourite  ;
    @BindView(R.id.floating_action_button_wallpaper)
    FloatingActionButton wallpaper  ;

    private Unbinder unbinder ;
    private  Bitmap photoBitmap ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unbinder = ButterKnife.bind(this);
        Intent intent =  getIntent() ;
        String photoId = intent.getStringExtra("photoId");
        getPhoto(photoId);

    }

    private  void getPhoto(String id ){
        ApiInterface apiInterface = ServiceGenerator.createServiceClass(ApiInterface.class);
        Call <Photos> call = apiInterface.getPhoto(id);
        call.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if (response.isSuccessful()){
                    Photos photos =  response.body() ;
                    updateUI(photos);
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {

            }
        });


    }

    private void updateUI(Photos photos) {

        try {
            username.setText(photos.getUser().getUsername());
            Glide.with(this)
                    .load(photos.getUser().getProfileImage().getSmall())
                    .into(circleImageView);

            Glide.with(this)
                    .asBitmap()
                    .load(photos.getUrl().getRegular())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            fullscreenPhoto.setImageBitmap(resource);
                            photoBitmap =  resource ;

                        }
                    }) ;
        }catch (Exception e){

        }
    }
    @OnClick(R.id.floating_action_button_favourite)
    public void setFavourite(){
        floatingActionMenu.close(true);
    }
    @OnClick(R.id.floating_action_button_wallpaper)
    public void setWallpaper(){
        if (photoBitmap != null) {
            if (Functions.setWallpaper(Fullscreen.this,photoBitmap)){
                Toast.makeText(this,"Wallpaper Succesfully Changed",Toast.LENGTH_LONG);
            }else {
                Toast.makeText(this,"Failed to change  Wallpaper ",Toast.LENGTH_LONG);
            }
        }
        floatingActionMenu.close(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

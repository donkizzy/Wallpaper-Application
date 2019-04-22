package healby.com.ng.wallpaperplug.Adapters;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import healby.com.ng.wallpaperplug.Models.Photos;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Utils.SquareImageView;

public class PhotosAdapters extends RecyclerView.Adapter<PhotosAdapters.ViewHolder> {
    private Context context ;
    private List<Photos> photos;

    public PhotosAdapters (Context context,List<Photos> photos){
        this.context =  context;
        this.photos =  photos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_photos,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Photos photo = photos.get(i);
        viewHolder.textView.setText(photo.getUser().getUsername());
        Glide.with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600,600)
                .into(viewHolder.squareImageView);

        Glide.with(context)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(viewHolder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_photo_user_avatar)
        CircleImageView circleImageView;
        @BindView(R.id.item_photo_username)
        TextView textView ;
        @BindView(R.id.item_photo_photo)
        SquareImageView squareImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

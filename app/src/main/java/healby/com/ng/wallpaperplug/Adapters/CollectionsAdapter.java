package healby.com.ng.wallpaperplug.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import healby.com.ng.wallpaperplug.Models.Collection;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Utils.SquareImageView;

public class CollectionsAdapter extends BaseAdapter {
    private Context context ;
    private List<Collection> collections ;

    public CollectionsAdapter(Context context,List<Collection> collections){
        this.context =  context;
        this.collections =  collections ;
    }
    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return collections.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder ;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collections, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =  (ViewHolder) convertView.getTag();
        }

        ButterKnife.bind(this,convertView);
        Collection collection = collections.get(position);
        if (collection.getTitle() != null){
            viewHolder.collectionName.setText(collection.getTitle());
        }
        viewHolder.number.setText(String.valueOf(collection.getTotalPhotos() + " photos"));
        Glide
                .with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(viewHolder.squareImageView);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_collection_photo)
        SquareImageView squareImageView ;
        @BindView(R.id.item_collection_name)
        TextView collectionName ;
        @BindView(R.id.item_collection_number)
        TextView number;

        public ViewHolder (View view){
            ButterKnife.bind(this,view);
        }
    }
}

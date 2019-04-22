package healby.com.ng.wallpaperplug.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import healby.com.ng.wallpaperplug.Models.Collections;
import healby.com.ng.wallpaperplug.R;
import healby.com.ng.wallpaperplug.Utils.SquareImageView;

public class CollectionsAdapter extends BaseAdapter {
    private Context context ;
    private List<Collections> collections ;

    public CollectionsAdapter(Context context,List<Collections> collections){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collections, parent);
        }
        return null;
    }

    static class ViewHolder {
        @BindView(R.id.item_collection_photo)
        SquareImageView squareImageView ;
        @BindView(R.id.item_collection_name)
        TextView collectionName ;
        @BindView(R.id.item_collection_number)
        TextView textView;

        public ViewHolder (View view){
            ButterKnife.bind(this,view);
        }
    }
}

package me.jlucas.nemo.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import me.jlucas.nemo.Models.Image;
import me.jlucas.nemo.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Image> imageList;
    private Context context;

    public ImageAdapter(Context context, List<Image>android){
        this.imageList = android;
        this.context = context;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder viewHolder,int i){
        viewHolder.categoryName.setText(this.imageList.get(i).getCategory());

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(this.imageList.get(i).getFilePath()))
                .setResizeOptions(new ResizeOptions(300, 300, 0.5f))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)
                .setImageRequest(request)
                .setOldController(viewHolder.previewImage.getController()).build();
        viewHolder.previewImage.setController(controller);
    }

    @Override
    public int getItemCount(){
        return this.imageList.size();
    }

    public void updateData(List<Image> newImages){
        this.imageList = newImages;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView previewImage;
        private TextView categoryName;
        public ViewHolder(View view) {
            super(view);
            previewImage = (SimpleDraweeView) view.findViewById(R.id.galleryimgPreview);
            categoryName = (TextView) view.findViewById(R.id.gallerycategoryName);
        }
    }
}
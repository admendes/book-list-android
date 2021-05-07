package pt.com.admendes.bookstore.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pt.com.admendes.bookstore.R;
import pt.com.admendes.bookstore.model.VolumeInfo;
import pt.com.admendes.bookstore.ui.BookDetailsActivity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private Context context;
    private ArrayList<VolumeInfo> volumeList;

    public RecyclerAdapter(Context context, ArrayList<VolumeInfo> volumeList) {
        this.context = context;
        this.volumeList = volumeList;
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {


        if (volumeList.get(position).getImageLinks() !=null && volumeList.get(position).getImageLinks().getSmallThumbnail() != null) {
            Picasso.get().load(volumeList.get(position).getImageLinks().getSmallThumbnail()).into(holder.imgCover);
        }
        else {
            holder.imgCover.setImageResource(R.drawable.ic_baseline_broken);
        }

        /**
            holder.txtTitle.setText(volumeList.get(position).getTitle());
            if (volumeList.get(position).getAuthors() != null)
                holder.txtAuthor.setText(TextUtils.join(", ", volumeList.get(position).getAuthors()));
            else holder.txtAuthor.setText("Unknown Author");
            holder.txtPages.setText("Pages: " + Integer.toString(volumeList.get(position).getPageCount()));
        **/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BookDetailsActivity.class);
                i.putExtra("book", volumeList.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return volumeList.size();
    }


    public class MyviewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCover;
        private TextView txtTitle;
        private TextView txtAuthor;
        private TextView txtPages;

        public MyviewHolder(View view) {
            super(view);
            imgCover = (ImageView) view.findViewById(R.id.imgCover);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtAuthor = (TextView) view.findViewById(R.id.txtAuthor);
            txtPages = (TextView) view.findViewById(R.id.txtPages);
        }

        public ImageView getImgCover() {
            return imgCover;
        }

        public void setImgCover(ImageView imgCover) {
            this.imgCover = imgCover;
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }

        public void setTxtTitle(TextView txtTitle) {
            this.txtTitle = txtTitle;
        }

        public TextView getTxtAuthor() {
            return txtAuthor;
        }

        public void setTxtAuthor(TextView txtAuthor) {
            this.txtAuthor = txtAuthor;
        }

        public TextView getTxtPages() {
            return txtPages;
        }

        public void setTxtPages(TextView txtPages) {
            this.txtPages = txtPages;
        }
    }
}

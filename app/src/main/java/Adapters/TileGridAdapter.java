package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hunter.kardashevscale.R;

import java.util.ArrayList;

import static com.example.hunter.kardashevscale.MainActivity.gameData;

public class TileGridAdapter extends RecyclerView.Adapter<TileGridAdapter.ViewHolder> {

    private static final String TAG = "test";


    private ArrayList<Integer> mTileImages;
    private ArrayList<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<Integer> mTextColors;
    private ArrayList<Boolean> mCaptured;
    private ArrayList<Double> mDefense;      //the closer to bottom right the more battle required
    private ArrayList<Double> mResourceWeight;

    private ArrayList<Integer> mTileCaptureProgress;


    public TileGridAdapter(Context context, ArrayList<Integer>textColors, ArrayList<Boolean> captured, ArrayList<Integer> tileImages, ArrayList<String> data, ArrayList<Double> defense, ArrayList<Double> resourceWeight, ArrayList<Integer> tileCaptureProgress) {

        this.mInflater = LayoutInflater.from(context);

        this.mTextColors = textColors;
        this.mCaptured = captured;
        this.mTileImages = tileImages;
        this.mData = data;
        this.mDefense = defense;
        this.mResourceWeight = resourceWeight;
        this.mTileCaptureProgress = tileCaptureProgress;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tile_grid_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.text.setText(mData.get(i));
        viewHolder.text.setTextColor(mTextColors.get(i));
        viewHolder.image.setImageResource(mTileImages.get(i));
//        viewHolder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d(TAG, "CLICKED: " + mTileImages.get(i));
//                //Toast.makeText(context, "Clicked on " + mTileImages.get(i), Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tileGrid_text);

            image = itemView.findViewById(R.id.tileGrid_icon);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
                //progress.setVisibility(View.VISIBLE);
                //Log.d(TAG, "CLICKED: " + getAdapterPosition());
            }
        }
    }
    public Integer getTextColor(int id){
        return mTextColors.get(id);
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id);
    }

    public Double getDefense(int id) {
        return mDefense.get(id);
    }

    public Double getResWeight(int id) {
        return mResourceWeight.get(id);
    }

    public Boolean isCaptured(int id) {
        return mCaptured.get(id);
    }

    public void setTextColor(int id, int color){
        mTextColors.set(id, color);
        notifyItemChanged(id);
    }

    public void setCaptured(int id) {
        mCaptured.set(id, true);
        mTileImages.set(id, R.drawable.ic_claimed);
        mTextColors.set(id,Color.WHITE);
        gameData.setCaptureBonus(gameData.getCaptureBonus() + mResourceWeight.get(id));
        //Log.d(TAG, "Resource Bonus: " + gameData.getCaptureBonus());

        notifyItemChanged(id);       //CANNOT UPDATE VIEW FROM BACKGROUND THREAD
    }

    public void setProgress(int id, double battle) {
        mTileCaptureProgress.set(id, (int)(mTileCaptureProgress.get(id) + battle));
    }

    public int getProgress(int id) {
        return mTileCaptureProgress.get(id);
    }

    //Interface for mClickListener onItemClick
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}


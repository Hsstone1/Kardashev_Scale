package Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hunter.kardashevscale.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;

public class TreeGraphAdapter extends GraphAdapter<GraphView.ViewHolder> {
    private ItemClickListener mClickListener;
    private ArrayList<String> mName;

    public TreeGraphAdapter(@NotNull Graph graph, ArrayList<String> name) {
        super(graph);
        this.mName = name;
    }

    @NonNull
    @Override
    public GraphView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.treenode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull GraphView.ViewHolder viewHolder, @NotNull Object data, final int position) {
        ((ViewHolder) viewHolder).upgradeName.setText(mName.get(position)); //references textview from the viewholder class
        ((ViewHolder) viewHolder).upgradeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "onClick: " + position);
            }
        });
    }

    @Override
    public int getCount() {
        return mName.size();
    }

    @Override
    public Object getItem(int position) {
        return mName.get(position);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    class ViewHolder extends GraphView.ViewHolder implements View.OnClickListener {
        TextView upgradeName;

        ViewHolder(@NotNull View itemView) {
            super(itemView);
            upgradeName = itemView.findViewById(R.id.upgradeName_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                //mClickListener.onItemClick(v,getAdapterPosition());
            }
        }
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

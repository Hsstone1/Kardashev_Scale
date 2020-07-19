package Fragments;

import android.os.Bundle;

import UpgradeTree.UpgradeObject.UObject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunter.kardashevscale.R;
import com.otaliastudios.zoom.ZoomLayout;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import UpgradeTree.UpgradeTree;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.layered.SugiyamaAlgorithm;
import de.blox.graphview.layered.SugiyamaConfiguration;

import static com.example.hunter.kardashevscale.MainActivity.gameData;

public class UpgradeFragment extends Fragment {

    private static final String TAG = "test";
    private static final UpgradeTree upgradeTree = new UpgradeTree();

    public GraphView treeGraph;
    public ZoomLayout zoomLayout;
    public TextView energyText;
    public final static ArrayList<Node> nodes = new ArrayList<>();
    public final static ArrayList<Boolean> purchased = new ArrayList<>();
    public final static Graph tree = new Graph();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upgrade, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        treeGraph = Objects.requireNonNull(getView()).findViewById(R.id.upgradetree_graphview);
        zoomLayout = getView().findViewById(R.id.upgradetree_zoomlayout);
        energyText = getView().findViewById(R.id.energy_text);
        upgradeTree.addUpgrades();

        upgradeTree.getUpgrade(0).setPurchased(true);       //this assures the first child object has a parent


//        for (int i = 0; i < nodes.size(); i++) {
//            Log.d(TAG, "Node: " + nodes.get(i).getData().toString());
//        }


        GraphAdapter adapter = new GraphAdapter<GraphView.ViewHolder>(tree) {

            @NonNull
            @Override
            public GraphView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.treenode, parent, false);
                return new TreeViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NotNull GraphView.ViewHolder viewHolder, @NotNull Object data, final int position) {
                ((TreeViewHolder) viewHolder).upgradeName.setText(nodes.get(position).getData().toString()); //references textview from the viewholder class
                purchased.add(position, false);
                ((TreeViewHolder) viewHolder).background.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nodes.get(position).getData().toString();
                        UObject upgrade = upgradeTree.getUpgrade(position);

                        if (!name.equals(upgradeTree.getUpgrade(0).getUpgradeName())) {
                            if(purchased.get(position)){
                                Log.d(TAG, "Upgrade " + name + " is purchased.");
                            } else {
                                if(gameData.getEnergy() > upgrade.getuCost()[0] + (gameData.getPopulation() * gameData.getEnergyPerPop()/20)
                                        && upgrade.getuParent()[0].isPurchased()){
                                    Log.d(TAG, "You purchased upgrade " + name);
                                    upgradePurchased(upgrade,position);
                                } else {
                                    Log.d(TAG, "You cannot purchase upgrade " + name);

                                }
                            }

                        }
                    }
                });
            }

            @Override
            public int getCount() {
                return nodes.size();
            }

            @Override
            public Object getItem(int position) {
                return nodes.get(position);
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            class TreeViewHolder extends GraphView.ViewHolder {
                TextView upgradeName;
                LinearLayout background;

                TreeViewHolder(@NotNull View itemView) {
                    super(itemView);
                    upgradeName = itemView.findViewById(R.id.upgradeName_textview);
                    background = itemView.findViewById(R.id.upgradebackground_linearLayout);
                }
            }

        };

        treeGraph.setAdapter(adapter);
        final SugiyamaConfiguration configuration = new SugiyamaConfiguration.Builder().setLevelSeparation(300).setNodeSeparation(300).build();
        //final BuchheimWalkerConfiguration configuration = new BuchheimWalkerConfiguration.Builder().setSiblingSeparation(300).setLevelSeparation(500).setSubtreeSeparation(500).setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM).build();

        treeGraph.setLayout(new SugiyamaAlgorithm(configuration));
        //treeGraph.setLayout(new BuchheimWalkerAlgorithm(configuration));
        zoomLayout.zoomTo(2, true);
    }


    public static void addNode(String nodeName, String parent) {
        if (nodeName != null && parent != null) {
            nodes.add(new Node(nodeName));
            tree.addEdge(Objects.requireNonNull(findNode(parent)), Objects.requireNonNull(findNode(nodeName)));
        }
    }

    public static Node findNode(String string) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getData().toString().equals(string)) {
                return nodes.get(i);
            }
        }
        return null;
    }

    //uBonus.getBattleBonus(), uBonus.getFoodBonus(), uBonus.getProductionBonus(), uBonus.getEnergyBonus()
    public void upgradePurchased(UObject upgrade, int pos){
        gameData.setEnergySpent(gameData.getEnergySpent() + upgrade.getuCost()[0]);
        gameData.setEnergy(gameData.getEnergy() - upgrade.getuCost()[0]);
        gameData.setBattleBonus(gameData.getBattleBonus() * Math.max(upgrade.getuBonus()[0],1));
        gameData.setFoodBonus(gameData.getFoodBonus() * Math.max(upgrade.getuBonus()[1],1));
        gameData.setProductionBonus(gameData.getProductionBonus() * Math.max(upgrade.getuBonus()[2],1));
        gameData.setEnergyBonus(gameData.getEnergyBonus() * Math.max(upgrade.getuBonus()[3],1));
        upgrade.setPurchased(true);

        Log.d(TAG, "B: " + gameData.getBattleBonus() + " | F: " + gameData.getFoodBonus() );
        purchased.set(pos, true);
    }
}

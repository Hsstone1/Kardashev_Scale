package UpgradeTree;


import java.util.ArrayList;

import UpgradeTree.UpgradeObject.UBonus;
import UpgradeTree.UpgradeObject.UCost;
import UpgradeTree.UpgradeObject.UObject;
import UpgradeTree.UpgradeObject.UParent;
import de.blox.graphview.Node;

import static Fragments.UpgradeFragment.addNode;
import static Fragments.UpgradeFragment.nodes;


public class UpgradeTree {

    public static ArrayList<UObject> uTree = new ArrayList<>();


    public UObject findUpgrade(String string) {
        for (int i = 0; i < uTree.size(); i++) {
            if (uTree.get(i).getUpgradeName().equals(string)) {
                return uTree.get(i);
            }
        }
        return null;
    }

    public UObject getUpgrade(int pos){
        return uTree.get(pos);
    }

    //tree.addEdge(findNode("test"), findNode("test2"));        //this allows for multiple parents

    public void addUpgrades() {
        uTree.add(new UObject.Builder("Stone Tools").build());
        nodes.add(new Node("Stone Tools")); //root node


        uTree.add(new UObject.Builder("Fire").uParent(new UParent.Builder().parentOne(findUpgrade("Stone Tools")).build()).uCost(new UCost.Builder().energyCost(1e4).build()).uBonus(new UBonus.Builder().foodBonus(1.25).build()).build());
        addNode("Fire", "Stone Tools");

        uTree.add(new UObject.Builder("Tanning").uParent(new UParent.Builder().parentOne(findUpgrade("Fire")).build()).uCost(new UCost.Builder().energyCost(2e4).build()).uBonus(new UBonus.Builder().build()).build());
        addNode("Tanning", "Fire");

        uTree.add(new UObject.Builder("Hardened Tools").uParent(new UParent.Builder().parentOne(findUpgrade("Fire")).build()).uCost(new UCost.Builder().energyCost(2e4).build()).uBonus(new UBonus.Builder().battleBonus(1.2).build()).build());
        addNode("Hardened Tools", "Fire");

        uTree.add(new UObject.Builder("Cooking").uParent(new UParent.Builder().parentOne(findUpgrade("Fire")).build()).uCost(new UCost.Builder().energyCost(2e4).build()).uBonus(new UBonus.Builder().foodBonus(1.25).build()).build());
        addNode("Cooking", "Fire");

        uTree.add(new UObject.Builder("Leather").uParent(new UParent.Builder().parentOne(findUpgrade("Tanning")).build()).uCost(new UCost.Builder().energyCost(3e4).build()).uBonus(new UBonus.Builder().build()).build());
        addNode("Leather", "Tanning");

        uTree.add(new UObject.Builder("String").uParent(new UParent.Builder().parentOne(findUpgrade("Tanning")).build()).uCost(new UCost.Builder().energyCost(3e4).build()).uBonus(new UBonus.Builder().build()).build());
        addNode("String", "Tanning");

        uTree.add(new UObject.Builder("Spears").uParent(new UParent.Builder().parentOne(findUpgrade("Hardened Tools")).build()).uCost(new UCost.Builder().energyCost(3e4).build()).uBonus(new UBonus.Builder().battleBonus(2).build()).build());
        addNode("Spears", "Hardened Tools");

        uTree.add(new UObject.Builder("Copper Smelting").uParent(new UParent.Builder().parentOne(findUpgrade("Hardened Tools")).build()).uCost(new UCost.Builder().energyCost(3e4).build()).uBonus(new UBonus.Builder().build()).build());
        addNode("Copper Smelting", "Hardened Tools");

        uTree.add(new UObject.Builder("Mortar and Pestle").uParent(new UParent.Builder().parentOne(findUpgrade("Cooking")).build()).uCost(new UCost.Builder().energyCost(3e4).build()).uBonus(new UBonus.Builder().build()).build());
        addNode("Mortar and Pestle", "Cooking");

        uTree.add(new UObject.Builder("Domestication").uParent(new UParent.Builder().parentOne(findUpgrade("Cooking")).build()).uCost(new UCost.Builder().energyCost(3e4).build()).uBonus(new UBonus.Builder().build()).build());
        addNode("Domestication", "Cooking");


    }

































    /*
    public static UObject upgrade_0 = new UObject.Builder("Stone Tools")
                .tileUnlock(2).build();
                */

//    public static class stage_0 { //upgrade_(PARENT NUM)_(CHILD NUM)
//        public static UObject upgrade_0 = new UObject.Builder("Stone Tools")
//                .tileUnlock(2).build();
//        public static UObject upgrade_0_0 = new UObject.Builder("Animal Cleaning")
//                .hasParent(upgrade_0).tileUnlock(2)
//                .uCost(new UCost.Builder().energyCost(2e5).build())
//                .uBonus(new UBonus.Builder().foodBonus(1.25).build()).build();
//
//        public static UObject upgrade_0_1 = new UObject.Builder("Mechanical Advantage")
//                .hasParent(upgrade_0).tileUnlock(2)
//                .uCost(new UCost.Builder().energyCost(4e5).build())
//                .uBonus(new UBonus.Builder().energyBonus(1.5).build()).build();
//
//        public static UObject upgrade_0_2 = new UObject.Builder("Leather Clothing")
//                .hasParent(upgrade_0).tileUnlock(2)
//                .uCost(new UCost.Builder().energyCost(2e5).build())
//                .uBonus(new UBonus.Builder().battleBonus(1.5).build()).build();
//
//
//        public static UObject upgrade_1 = new UObject.Builder("Fire")
//                .tileUnlock(3).build();
//        public static UObject upgrade_1_0 = new UObject.Builder("Cook Meat")
//                .hasParent(upgrade_1).tileUnlock(3)
//                .uCost(new UCost.Builder().energyCost(1e6).build())
//                .uBonus(new UBonus.Builder().foodBonus(2).build()).build();
//
//        public static UObject upgrade_1_1 = new UObject.Builder("Torches")
//                .hasParent(upgrade_1).tileUnlock(3)
//                .uCost(new UCost.Builder().energyCost(5e5).build())
//                .uBonus(new UBonus.Builder().prodBonus(1.5).battleBonus(.1).build()).build();
//
//        public static UObject upgrade_1_2 = new UObject.Builder("Sharper Tools")
//                .hasParent(upgrade_1).tileUnlock(3)
//                .uCost(new UCost.Builder().energyCost(5e5).build())
//                .uBonus(new UBonus.Builder().battleBonus(1.25).build()).build();
//
//    }
}


        /*
        public static UObject upgrade_0 = new UObject(null, "Stone Tools", false,
                new UCost(2, 1e6, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                new UBonus(0,0,0,0));
        */

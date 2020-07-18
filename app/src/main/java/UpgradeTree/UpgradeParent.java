package UpgradeTree;

public class UpgradeParent {

    private UpgradeObject parentOne;
    private UpgradeObject parentTwo;
    private UpgradeObject parentThree;
    private UpgradeObject parentFour;
    private UpgradeObject parentFive;


    public static class Builder {

        private UpgradeObject parentOne;
        private UpgradeObject parentTwo;
        private UpgradeObject parentThree;
        private UpgradeObject parentFour;
        private UpgradeObject parentFive;



        public Builder parentOne(UpgradeObject parentOne){
            this.parentOne = parentOne;
            return this;
        }
        public Builder parentTwo(UpgradeObject parentTwo){
            this.parentTwo = parentTwo;
            return this;
        }
        public Builder parentThree(UpgradeObject parentThree){
            this.parentThree = parentThree;
            return this;
        }
        public Builder parentFour(UpgradeObject parentFour){
            this.parentFour = parentFour;
            return this;
        }
        public Builder parentFive(UpgradeObject parentFive){
            this.parentFive = parentFive;
            return this;
        }



        public UpgradeParent build(){
            UpgradeParent parent = new UpgradeParent();
            parent.parentOne = this.parentOne;
            parent.parentTwo = this.parentTwo;
            parent.parentThree = this.parentThree;
            parent.parentFour = this.parentFour;
            parent.parentFive = this.parentFive;

            return parent;
        }

    }

    //sets the default values
    private UpgradeParent(){
       this.parentOne = null;
       this.parentTwo = null;
       this.parentThree = null;
       this.parentFour = null;
       this.parentFive = null;

    }

    public UpgradeObject getParentOne() {
        return parentOne;
    }

    public UpgradeObject getParentTwo() {
        return parentTwo;
    }

    public UpgradeObject getParentThree() {
        return parentThree;
    }

    public UpgradeObject getParentFour() {
        return parentFour;
    }

    public UpgradeObject getParentFive() {
        return parentFive;
    }
}


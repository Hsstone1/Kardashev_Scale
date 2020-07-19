package UpgradeTree.UpgradeObject;

public class UParent {

    private UObject parentOne;
    private UObject parentTwo;
    private UObject parentThree;
    private UObject parentFour;
    private UObject parentFive;


    public static class Builder {

        private UObject parentOne;
        private UObject parentTwo;
        private UObject parentThree;
        private UObject parentFour;
        private UObject parentFive;



        public Builder parentOne(UObject parentOne){
            this.parentOne = parentOne;
            return this;
        }
        public Builder parentTwo(UObject parentTwo){
            this.parentTwo = parentTwo;
            return this;
        }
        public Builder parentThree(UObject parentThree){
            this.parentThree = parentThree;
            return this;
        }
        public Builder parentFour(UObject parentFour){
            this.parentFour = parentFour;
            return this;
        }
        public Builder parentFive(UObject parentFive){
            this.parentFive = parentFive;
            return this;
        }



        public UParent build(){
            UParent parent = new UParent();
            parent.parentOne = this.parentOne;
            parent.parentTwo = this.parentTwo;
            parent.parentThree = this.parentThree;
            parent.parentFour = this.parentFour;
            parent.parentFive = this.parentFive;

            return parent;
        }

    }

    //sets the default values
    private UParent(){
       this.parentOne = null;
       this.parentTwo = null;
       this.parentThree = null;
       this.parentFour = null;
       this.parentFive = null;

    }

    public UObject getParentOne() {
        return parentOne;
    }

    public UObject getParentTwo() {
        return parentTwo;
    }

    public UObject getParentThree() {
        return parentThree;
    }

    public UObject getParentFour() {
        return parentFour;
    }

    public UObject getParentFive() {
        return parentFive;
    }
}


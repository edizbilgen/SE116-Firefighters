package src.objectvillie.cells;



public abstract class Zone extends Cell{

    protected int level;

    protected int electricity;
    protected int water;
    protected int internet;
    protected int population;
    protected int goods;

    protected int lifestyle;
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;

    protected int output;

    protected int nextElectricity;
    protected int nextWater;
    protected int nextInternet;
    protected int nextPopulation;
    protected int nextGoods;
    protected int nextLifestyle;

    protected boolean nextHasSecurity;
    protected boolean nextHasHealth;
    protected boolean nextHasEducation;





    public Zone(int x, int y, char symbol) {
        super(x, y, symbol);
        this.level = 0;
        this.output = 0;
        resetTickData();

    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getElectricity() {
        return electricity;
    }
    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }
    public int getInternet() {
        return internet;
    }
    public void setInternet(int internet) {
        this.internet = internet;
    }
    public int getWater() {
        return water;
    }
    public void setWater(int water) {
        this.water = water;
    }
    public int getGoods() {
        return goods;
    }
    public void setGoods(int goods) {
        this.goods = goods;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public int getLifestyle() {
        return lifestyle;
    }
    public void setLifestyle(int lifestyle) {
        this.lifestyle = lifestyle;
    }

    public int getOutput() {
        return output;
    }
    public void setOutput(int output) {
        this.output = output;
    }
    public boolean isHasEducation() {
        return hasEducation;
    }
    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }
    public boolean isHasHealth() {
        return hasHealth;
    }
    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }
    public boolean isHasSecurity() {
        return hasSecurity;
    }
    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public int calculateUtilityDemand(){
        if (this.output <1){
            return 1;
        }
        return this.output;
    }

    public abstract void processTick();

    public abstract void generateResources();

    public abstract boolean canUpgrade();

    @Override

    public String toString() {
        return getClass().getSimpleName() + "  level=" + level + ", electricity=" + electricity + ", water=" + water + ", internet=" + internet + ", population=" + population + ", goods=" + goods +", lifestyle=" + lifestyle + ", output=" + output ;
    }


    public void applyNextState(){
        this.electricity = this.nextElectricity;
        this.water = this.nextWater;
        this.internet = this.nextInternet;
        this.population = this.nextPopulation;
        this.goods = this.nextGoods;
        this.lifestyle = this.nextLifestyle;
        this.hasSecurity = this.nextHasSecurity;
        this.hasHealth = this.nextHasHealth;
        this.hasEducation = this.nextHasEducation;
        resetTickData();}
    public void resetTickData(){this.nextPopulation = 0;
        this.nextGoods = 0;
        this.nextLifestyle = 0;
        this.nextElectricity = 0;
        this.nextWater = 0;
        this.nextInternet = 0;
        this.nextHasSecurity = false;
        this.nextHasHealth = false;
        this.nextHasEducation = false;}
    public int calculateM(){
        return Math.min(this.electricity, Math.min(this.water, this.internet));}
    public void addElectricity(int amount) {this.nextElectricity += amount;}
    public void addWater(int amount){this.nextWater += amount;}
    public void addInternet(int amount){this.nextInternet += amount;}
    public void addPopulation(int amount){this.nextPopulation += amount;}
    public void addGoods(int amount){this.nextGoods += amount;}
    public void addLifestyle(int amount){this.nextLifestyle += amount;}

    public void addSecurity() {
        nextHasSecurity = true;
    }

    public void addHealth() {
        nextHasHealth = true;
    }

    public void addEducation() {
        nextHasEducation = true;
    }

}

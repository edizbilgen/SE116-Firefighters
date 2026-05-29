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



    public Zone(int x, int y, char symbol, int level, int electricity, int water, int internet, int population, int goods, int lifestyle, boolean hasSecurity, boolean hasHealth, boolean hasEducation, int output) {
        super(x, y, symbol);
        level = 0;
        output = 0;

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


    public void resetTickData(){

    }
    public void addElectricity(int amount) {

    }
    public void addWater(int amount){

    }
    public void addInternet(int amount){

    }
    public void addPopulation(int amount){

    }
    public void addGoods(int amount){

    }
    public void addLifestyle(int amount){

    }





}

package src.objectvillie.engine;

import src.objectvillie.cells.Cell;
import src.objectvillie.cells.Zone;
import src.objectvillie.map.CityMap;
import src.objectvillie.utilities.BFSengine;
import src.objectvillie.utilities.UtilityProvider;

import java.util.*;


public class SimulationEngine {
    private CityMap cityMap;
    private int totalTick;
    private BFSengine  bfsEngine;

    private int totalPopulation = 0;
    private int totalGoods = 0;
    private int totalLifestyle = 0;
     public SimulationEngine(String mapFile,int totalTick) {
         this.totalTick = totalTick;
         this.bfsEngine = new BFSengine();

         if(mapFile == null || mapFile.trim().isEmpty()) {
             throw new IllegalArgumentException("The map file is null or empty");
         }
         if(totalTick <= 0) {
             throw new IllegalArgumentException("The total tick is negative or zero");
         }
         this.cityMap = new CityMap(0,0);
         this.cityMap.readMap(mapFile);
     }
     public void runSimulation() {
         for(int i=0;i<totalTick;i++) {
             System.out.println("Now We are in " + i);


             /* Services are provided
             Alp here is your job u gonna find the buidings and take these cordinate
             then you start the algoritma and you triger inside the Zone metthods ı think u got this
              */







             //utilities are distributed
             // I find all utulities and starting the BFS
             for (int j = 0; j < cityMap.getRow(); j++) {
                 for (int k = 0; k < cityMap.getCol(); k++) {
                     Cell cell = cityMap.getCell(j,k);
                     if (cell instanceof UtilityProvider) {
                         UtilityProvider utilityProvider = (UtilityProvider) cell;
                         utilityProvider.resetCapacity();
                         bfsEngine.bfsSearch(cityMap,utilityProvider);

                     }
                 }
             }
             distributeGlobalResources();
             //Zones are updated
             for (int v = 0; v < cityMap.getRow(); v++) {
                 for (int k = 0; k < cityMap.getCol(); k++) {
                     Cell cell = cityMap.getCell(v,k);
                    if (cell instanceof Zone) {
                     Zone zone = (Zone) cell;
                     zone.applyNextState();
                     /*
                     Alp here is your job U will calculate electric water like this somethinf look service position
                     and calculate incirease or decrase
                      */
                    }
                 }
             }
             accumulateGlobalResources();
             printMapState();
         }
     }
     private void distributeGlobalResources() {
         ArrayList<Zone> houses = new  ArrayList<>();
         ArrayList<Zone> industrialCommercial = new ArrayList<>();
         ArrayList<Zone> commercialOnly = new ArrayList<>();
         for(int i = 0; i < cityMap.getRow(); i++) {
             for(int j = 0; j < cityMap.getCol(); j++) {
                 Cell cell = cityMap.getCell(i,j);
                 if (cell instanceof Zone) {
                     Zone zone = (Zone) cell;
                     char symbol = zone.getSymbol();
                     if(symbol == 'H'){
                         houses.add(zone);
                     }else if(symbol == 'I' || symbol == 'C'){
                         industrialCommercial.add(zone);
                     }if(symbol == 'C'){
                         commercialOnly.add(zone);
                     }
                 }
             }
         }

         if(!industrialCommercial.isEmpty() && totalPopulation > 0){
             int share = totalPopulation/industrialCommercial.size();
             for( Zone zone : industrialCommercial ){
                 zone.addPopulation(share);
             }
         }
         if(!commercialOnly.isEmpty() && totalGoods > 0){
             int share = totalGoods/commercialOnly.size();
             for( Zone zone : commercialOnly ){
                 zone.addGoods(share);
             }
         }
         if(!houses.isEmpty() && totalLifestyle > 0){
             int share = totalLifestyle/houses.size();
             for( Zone zone : houses ){
                 zone.addLifestyle(share);
             }
         }
     }

     private void accumulateGlobalResources() {
         totalPopulation = 0;
         totalGoods = 0;
         totalLifestyle = 0;

         for(int i = 0; i < cityMap.getRow(); i++) {
             for(int j = 0; j < cityMap.getCol(); j++) {
                 Cell cell = cityMap.getCell(i,j);
                 if (cell instanceof Zone) {
                     Zone zone = (Zone) cell;

                     /*
                     Alp you goona write here what is the building right now level and 'm'
                     then u you calculate new output amount after u make a set thaht amount
                      */
                     char symbol = zone.getSymbol();
                     if(symbol == 'H'){
                         totalPopulation += zone.getOutput();
                     }else if(symbol == 'I'){
                         totalGoods += zone.getOutput();
                     }else if(symbol == 'C'){
                         totalLifestyle += zone.getOutput();
                     }

                 }

             }
         }

     }
     private void printMapState() {
         for(int i = 0; i < cityMap.getRow(); i++) {
             for(int j = 0; j < cityMap.getCol(); j++) {
                 System.out.print(cityMap.getCell(i,j).getSymbol() + " ");
             }
             System.out.println();
         }
         System.out.println("Total Population: " + totalPopulation + " Total Goods " +  totalGoods + " Total Lifestyle " + totalLifestyle);
     }

     // denemek için main yazıcam sonra

}

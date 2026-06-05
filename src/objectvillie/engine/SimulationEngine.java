package src.objectvillie.engine;

import src.objectvillie.cells.*;
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


             provideServices();



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
                     zone.processTick();
                    }
                 }
             }
             accumulateGlobalResources();
             printMapState();
         }
     }
    private void provideServices() {

        for (int i = 0; i < cityMap.getRow(); i++) {
            for (int j = 0; j < cityMap.getCol(); j++) {

                Cell cell = cityMap.getCell(i, j);

                if (!(cell instanceof ServiceProvider)) {
                    continue;
                }

                ServiceProvider service = (ServiceProvider) cell;

                for (int r = 0; r < cityMap.getRow(); r++) {

                    for (int c = 0; c < cityMap.getCol(); c++) {

                        Cell target = cityMap.getCell(r, c);

                        if (!(target instanceof Zone)) {
                            continue;
                        }

                        int distance = Math.abs(i - r) + Math.abs(j - c);

                        if (distance <= service.getRadius()) {

                            Zone zone = (Zone) target;

                            if (service instanceof PoliceStation) {
                                zone.addSecurity();
                            }

                            else if (service instanceof Hospital) {
                                zone.addHealth();
                            }

                            else if (service instanceof School) {
                                zone.addEducation();
                            }
                        }
                    }
                }
            }
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

    public static void main(String[] args) {
        String mapFilePath;
        int ticks;
        if (args.length >= 2) {
            mapFilePath = args[0];
            try {
                ticks = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error: ticks are not valid number");
                return;
            }
        } else {

            System.out.println("There is no argument default tick starting");
            mapFilePath = "mymap.txt";
            ticks = 5;
        }

        try {
            SimulationEngine engine = new SimulationEngine(mapFilePath, ticks);
            engine.runSimulation();
        } catch (Exception e) {
            System.out.println("Simulation stopped because of the error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

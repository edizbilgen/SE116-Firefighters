package src.objectvillie.utilities;

import src.objectvillie.cells.*;
import src.objectvillie.map.*;
import src.objectvillie.utilities.*;
import java.util.Queue;
import java.util.LinkedList;

public class BFSengine {

    public void visit(Cell cell,boolean[][] visited){
        visited[cell.getX()][cell.getY()]=true;
    }

 public void bfsSearch (CityMap cityMap ,UtilityProvider utilityProvider){
     Queue<Cell> cells= new LinkedList<>();
     boolean[][] visited=new boolean[cityMap.getRow()][cityMap.getCol()];

     cells.add(utilityProvider);
     visit(utilityProvider,visited);

     while (utilityProvider.getCurrentCapacity()>0 && !cells.isEmpty()){
         Cell currentCell=cells.poll();

         if(currentCell instanceof Zone){
             Zone currentzone = (Zone) currentCell;
             int demand = currentzone.calculateUtilityDemand();
             int availableCapacity = utilityProvider.getCurrentCapacity();

             int allocatedAmount = Math.min(demand,availableCapacity);

             char type = utilityProvider.getSymbol();
             if (type == 'P'){
                 currentZone.addElectricity(allocatedAmount);
             }
             else if (type == 'W'){
                 currentzone.addWater(allocatedAmount);
             }
             else if (type == 'T') {
                 currentzone.addInternet(allocatedAmount);
             }
             utilityProvider.consumeUtility(allocatedAmount);
         }
         for (int i = 0; i < 4; i++) {
             int nextRow = currentCell.getX() + dRow[i];
             int nextCol = currentCell.getY() + dCol[i];

             Cell nextCell = cityMap.getCell(nextRow, nextCol);
             if(nextCell != null && !visited[nextRow][nextCol] && !(nextCell instanceof EmptyCell)){
                 cells.add(nextCell);
                 visit(nextCell,visited);
             }
         }
         
         if(currentCell instanceof Zone){
             
         }
             

     }
 }
}

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

     int[] dRow = {-1, 1, 0, 0};
     int[] dCol = {0, 0, -1, 1};

     while (utilityProvider.getCurrentCapacity()>0 && !cells.isEmpty()){
         Cell currentCell=cells.poll();
         if(currentCell instanceof Zone){
              if(currentCell instanceof IndustrialZone && utilityProvider instanceof InternetHub){}
             else {
                 int demand=((Zone) currentCell).calculateUtilityDemand();
                 
                 int allocatedAmount =Math.min(demand,utilityProvider.getCurrentCapacity());

                  if (utilityProvider.getSymbol() == 'P') {
                      ((Zone)currentCell).addElectricity(allocatedAmount);
                  } else if (utilityProvider.getSymbol() == 'W') {
                      ((Zone)currentCell).addWater(allocatedAmount);
                  } else if (utilityProvider.getSymbol() == 'T') {
                      ((Zone)currentCell).addInternet(allocatedAmount);
                  }

                  utilityProvider.consumeUtility(allocatedAmount);
              }
         }
         for (int i=0;i<4;i++){
             int nextRow= currentCell.getX()+dRow[i];
             int nextCol=currentCell.getY()+dCol[i];

             Cell nextCell=cityMap.getCell(nextRow,nextCol);

             if(nextCell != null && !visited[nextRow][nextCol] && !(nextCell instanceof EmptyCell)){
                 cells.add(nextCell);
                 visit(nextCell,visited);
             }
         }

     }
 }
}

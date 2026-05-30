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
         
     }
 }
}

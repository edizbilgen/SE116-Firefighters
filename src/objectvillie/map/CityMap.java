package src.objectvillie.map;

import src.objectvillie.cells.Cell;

public class CityMap {
    //n*m grid structer
    private Cell[][] cellsGrid;
    private int row;
    private int col;
    public CityMap(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellsGrid = new Cell[row][col];
    }
    //u can access all cell with this method
    public Cell getCell(int i, int j) {
       if(i<0 && i>=row && j<0 && j>=col){
        return cellsGrid[i][j];
       }
       return null;
    }
    // u need to write map parser method here


}

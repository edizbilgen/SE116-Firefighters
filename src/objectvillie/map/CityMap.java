package src.objectvillie.map;

import src.objectvillie.cells.*;
import src.objectvillie.utilities.InternetHub;
import src.objectvillie.utilities.PowerPlant;
import src.objectvillie.utilities.WaterPumpingStation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;

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
       if(i >= 0 && i < row && j >= 0 && j < col){
        return cellsGrid[i][j];
       }
       return null;
    }
    // u need to write map parser method here

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void readMap(String mapFile){
        ArrayList<String> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(mapFile))){
            String line;
            while ((line = br.readLine()) != null){
                lines.add(line.trim());
            }
        }catch(Exception e){
            throw new RuntimeException("Error reading map file: " + e.getMessage());
        }
        if(lines.isEmpty()){
            System.out.println("Empty map file");
            return;
        }
        this.row = lines.size();
        this.col = lines.get(0).length();
        this.cellsGrid = new Cell[row][col];
        for(int i = 0; i < row; i++){
            String line = lines.get(i);
            if(line.length() != col ){
                System.out.println("Map row length not equal to column length");
            return;
            }
            for(int j = 0; j < col; j++){
                char symbol = line.charAt(j);
                switch(symbol){
                    case 'E':
                        cellsGrid[i][j] = new EmptyCell(i,j);
                        break;
                    case 'P':
                        cellsGrid[i][j] = new PowerPlant(i,j,'P');
                        break;
                        case 'W':
                            cellsGrid[i][j] = new WaterPumpingStation(i,j,'W');
                            break;
                    case 'T':
                        cellsGrid[i][j] = new InternetHub(i,j,'T');
                        break;
                    case 'H':
                        cellsGrid[i][j] = new HouseZone(i,j,'H');
                        break;
                        case 'I':
                            cellsGrid[i][j] = new IndustrialZone(i,j,'I');
                            break;
                    case 'C':
                        cellsGrid[i][j] = new CommercialZone(i,j,'C');
                        break;
                    case 'F':
                        cellsGrid[i][j] = new PoliceStation(i,j,'F');
                        break;
                    case 'D':
                        cellsGrid[i][j] = new Hospital(i,j,'D');
                        break;
                    case 'S':
                        cellsGrid[i][j] = new School(i,j,'S');
                        break;
                    case'R':
                    cellsGrid[i][j] = new Road(i,j,'R');
                    break;
                        //ı will add more zone
                        default:
                            cellsGrid[i][j] = new  EmptyCell(i,j);
                            break;
                }
            }
        }
    }


}

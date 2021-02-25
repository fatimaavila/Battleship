import java.util.*;


class Main {
  //-------------------------- globales -----------------------------
    public static int numFilas = 10;
    public static int numCols = 10;
    public static int playerShips;
    public static int compShips;
    public static String[][] tablero = new String[numFilas][numCols];
    public static int[][] fallos = new int[numFilas][numCols];
//------------------------------- métodos ---------------------------
         public static void printTablero(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println();
        //First 
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i + "   ");
        System.out.println();

        //Middle 
        for(int x = 0; x < tablero.length; x++) {
            //System.out.print("\u001B[0m");
            System.out.print("\u001B[36m" + x + "\u001B[36m|");
            System.out.print("\u001B[0m");

            for (int y = 0; y < tablero[x].length; y++){
                System.out.print(tablero[x][y]);
            }
            
            System.out.print("\u001B[0m");
            System.out.println("\u001B[36m|" + x);
            System.out.print("\u001B[0m");
            //System.out.print("\u001B[0m");
            }

        } // tablero

    //------------------------- main---------------------------------
  public static void main(String[] args) {
    printTablero();
    System.out.println("Hello world!");
  }
}
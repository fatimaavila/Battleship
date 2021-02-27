import java.util.*;


class Main {
  //-------------------------- globales -----------------------------
    public static int numFilas = 10;
    public static int numCols = 10;
    public static int playerShips = 3;
    public static int compShips = 3;

    public static String[][] tablero = new String[numFilas][numCols];
    public static int[][] fallos = new int[numFilas][numCols];
//------------------------------- métodos ---------------------------
         public static void printTablero(String p_mensaje){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println();
        //First 
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print("\u001B[33m  "+(char)(i+65) + "  ");
        System.out.println();

        //Middle 
        for(int x = 0; x < tablero.length; x++) {
            //System.out.print("\u001B[0m");
            System.out.print("\u001B[36m" + x + "\u001B[36m|");
            System.out.print("\u001B[0m");

            for (int y = 0; y < tablero[x].length; y++){
                System.out.print("  "+tablero[x][y]+"  ");
            }
            
            System.out.print("\u001B[0m");
            System.out.println("\u001B[36m|" + x);
            System.out.print("\u001B[0m");
            //System.out.print("\u001B[0m");
            }

        System.out.println("\n\t"+p_mensaje);
        } // tablero

        
public static void crearCampoDeBatalla(){

    for(int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
            tablero[i][j] = "🀆";
        }
    }
}  // ---- fin del ocean map

public static void crearBarcosPlayer(){
    Scanner input = new Scanner(System.in);

    System.out.println("\nAsigne posición ");
    //Deploying five ships for player
    int x,y,barcosAsignados=0;
    while (barcosAsignados < playerShips) {
       
        System.out.print(" -X- del barco " + (barcosAsignados+1) + ": ");

        x = input.nextInt();
        if (x>=0 && x<numFilas){

                System.out.print(" -Y- del barco " + (barcosAsignados+1) +": ");
                y = input.nextInt();
                            if(y >= 0 && y < numCols && (tablero[x][y] == "🀆"))
                                {
                                    tablero[x][y] =   "🚢";
                                    System.out.println("---------------------- ");
                                    barcosAsignados++;
                                }
                                else if(y >= 0 &&  y < numCols && tablero[x][y] == "🚢")
                                    System.out.println("\u001B[31m ¡No puedes colocar dos barcos en el mismo lugar!\u001B[0m");
                                else if((x < 0 || x > numFilas) || y < 0 || y > numCols)
                                    System.out.println("\u001B[31m No puedes colocar barcos fuera de " + numFilas + " por " + numCols + " en el tablero\u001B[0m");

        } else 
        { 
          System.out.println("\u001B[31m No puedes colocar barcos fuera de " + numFilas + " por " + numCols + " en el tablero\u001B[0m");
          }// si la posicion x es valida 
        
    } //fin numero de barcos a ingresar
    printTablero("Barcos listos");
} // barcos del jugador

public static void crearBarcosCompu(){
    System.out.println("\nEl contrincante está preparando sus barcos");
    //Deploying five ships for computer
   
    for (int i = 1; i <= compShips; ) {
        int x =  (int)(Math.random() * 10);
        int y = (int)(Math.random() * 5);

        if((x >= 0 && x < numFilas) && (y >= 0 && y < numCols) && (tablero[x][y] == "🀆"))
        {
            tablero[x][y] =   "🚤";
            System.out.println("Barco"+ i + "listo");
            i++;
        }
    }
    printTablero("Barcos listos");



} // fin de los barcos de la compu

    //------------------------- main---------------------------------
  public static void main(String[] args) {
    
    //---- paso 1: crear tablero
    crearCampoDeBatalla();
    printTablero("Bienvenidos a la BATALLA NAVAL");
    //------ deploy barcos
    crearBarcosPlayer();
    crearBarcosCompu();
   
    
    
  }
}
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
    char xletra;
    while (barcosAsignados < playerShips) {
       
        System.out.print(" -X- del barco " + (barcosAsignados+1) + ": ");

      //  x = input.nextInt();
      xletra = input.next().charAt(0);
      x= ((int) xletra)-65;
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

public static void turnoPlayer(){
    System.out.println("\nTU TURNO");
    int x = -1, y = -1;
    do {
        Scanner input = new Scanner(System.in);
        System.out.print("Ingresa la coordenada -X- ");
        x = input.nextInt();
        System.out.print("Ingresa la coordenada -Y- ");
        y = input.nextInt();

        if ((x >= 0 && x < numFilas) && (y >= 0 && y < numCols)) //valid guess
        {
            if (tablero[x][y] == "🚤") //if computer ship is already there; computer loses ship
            {
                System.out.println("¡Boom! Hundiste un barco del contrincante");
                tablero[x][y] = "🧨"; //Hit mark
                --compShips;
            }
            else if (tablero[x][y] == "🚢") {
                System.out.println("¡Oh no, hundiste tu barco!");
                tablero[x][y] = "🧨";
                --playerShips;
                ++compShips;
            }
            else if (tablero[x][y] == "🀆") {
                System.out.println("Oh no, fallaste el tiro");
                tablero[x][y] = "-";
            }
        }
        else if ((x < 0 || x >= numFilas) || (y < 0 || y >= numCols))  //invalid guess
            System.out.println("No puedes colocar barcos fuera de " + numFilas + " por " + numCols + "del tablero.");
    } while((x < 0 || x >= numFilas) || (y < 0 || y >= numCols)); 
} // ------ fin del turno del jugador

   public static void turnoCompu(){
    System.out.println("\nTURNO DEL CONTRINCANTE");
    //Guess co-ordinates
    int x = -1, y = -1;
    do {
        x = (int)(Math.random() * 10);
        y = (int)(Math.random() * 10);

        if ((x >= 0 && x < numFilas) && (y >= 0 && y < numCols)) //valid guess
        {
            if (tablero[x][y] == "🚢") //if player ship is already there; player loses ship
            {
                System.out.println("El contrincante hundió uno de tus barcos");
                tablero[x][y] = "🧨";
                --playerShips;
                ++compShips;
            }
            else if (tablero[x][y] == "🚤") {
                System.out.println("El contrincante hundió su propio barco");
                tablero[x][y] = "❌";
            }
            else if (tablero[x][y] == "🀆") {
                System.out.println("El contrincante falló");
                //Saving missed guesses for computer
                if(fallos[x][y] != 1)
                    fallos[x][y] = 1;
            }
        }
    }while((x < 0 || x >= numFilas) || (y < 0 || y >= numCols)); 
}  // balas de la computer

public static void Batalla(){
    turnoPlayer();
    turnoCompu();

    printTablero("");

    System.out.println();
    System.out.println("Barcos del jugador " + playerShips + " | Barcos del contrincante " + compShips);
    System.out.println();

} // fin batalla 

  public static void gameOver(){
        System.out.println("Your ships: " + playerShips + " | Computer ships: " + compShips);
        if(playerShips > 0 && compShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("");
        System.out.println();

   } // game over

    //------------------------- main---------------------------------
  public static void main(String[] args) {
    
    //---- paso 1: crear tablero
    crearCampoDeBatalla();
    printTablero("Bienvenidos a la BATALLA NAVAL");
    //------ deploy barcos
    crearBarcosPlayer();
    crearBarcosCompu();
    //turnoPlayer();
   // turnoCompu();
    while(playerShips != 0 && compShips != 0) {
      Batalla();
 }

 gameOver();
   
   
    
    
  }
}
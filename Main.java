import java.util.*;
//import java.io.*;


class Main {
  //-------------------------- globales -----------------------------
    public static int numFilas = 5;
    public static int numCols = 5;
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
        for(int f = 0; f < numFilas; f++) {
            //System.out.print("\u001B[0m");
            System.out.print("\u001B[36m" + f + "\u001B[36m|");
            System.out.print("\u001B[0m");

            for (int c = 0; c < numCols; c++){
              if(tablero[c][f]== "🚤"){
                System.out.print("  "+"🀆"+"  ");
              }
              else{
                System.out.print("  "+tablero[c][f]+"  "); // mostrar con barcos escondidos
              }
                
            }
            
            System.out.print("\u001B[0m");
            System.out.println("\u001B[36m|" + f);
            System.out.print("\u001B[0m");
            //System.out.print("\u001B[0m");
            }

        System.out.println("\n\t"+p_mensaje);
        } // tablero

        
public static void crearCampoDeBatalla(){

    for(int c = 0; c < numCols; c++) {
        for (int f = 0; f < numFilas; f++) {
            tablero[c][f] = "🀆";
        }
    }
}  // ---- fin del ocean map

public static void crearBarcosPlayer(){
    Scanner input = new Scanner(System.in);

    System.out.println("\nAsigne posición ");
    //Deploying five ships for player
    int x=0,y=0,barcosAsignados=0;
    char xletra;
    while (barcosAsignados < playerShips) {
       
        System.out.print(" -X- del barco " + (barcosAsignados+1) + ": ");

      //x = input.nextInt();
      xletra = input.next().toUpperCase().charAt(0);
     x= ((int) xletra)-65;
        if (x>=0 && x<numCols){

                System.out.print(" -Y- del barco " + (barcosAsignados+1) +": ");
                y = input.nextInt();
                            if(y >= 0 && y <numFilas && (tablero[x][y] == "🀆"))
                                {
                                    tablero[x][y] =   "🚢";
                                    System.out.println("---------------------- ");
                                    barcosAsignados++;
                                }
                                else if(y >= 0 &&  y < numFilas && tablero[x][y] == "🚢")
                                    System.out.println("\u001B[31m ¡No puedes colocar dos barcos en el mismo lugar!\u001B[0m");
                                else if((x < 0 || x > numCols) || y < 0 || y > numFilas)
                                    System.out.println("\u001B[31m No puedes colocar barcos fuera de " + numCols + " por " + numFilas + " en el tablero\u001B[0m");

        } else 
        { 
          System.out.println("\u001B[31m No puedes colocar barcos fuera de " + numFilas + " por " + numCols + " en el tablero\u001B[0m");
          }// si la posicion x es valida 
  //  System.out.println("Barco "+ xletra+y+tablero[x][y]);    
    } //fin numero de barcos a ingresar
    printTablero("Barcos listos");
} // barcos del jugador

public static void crearBarcosCompu(){
    System.out.println("\nEl contrincante está preparando sus barcos");
    //Deploying five ships for computer
   
    for (int i = 1; i <= compShips; ) {
        int x =  (int)(Math.random() * numCols);
        int y = (int)(Math.random() * numFilas);

        if((x >= 0 && x < numFilas) && (y >= 0 && y < numCols) && (tablero[x][y] == "🀆"))
        {
            tablero[x][y] =   "🚤";//------ barco de la compu
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
        char xletra;
        Scanner input = new Scanner(System.in);
        System.out.print("Ingresa la coordenada -X- ");
        xletra = input.next().toUpperCase().charAt(0);
        x= ((int) xletra)-65;
        //x = input.nextInt();
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
            }
            else if (tablero[x][y] == "🀆") {
                System.out.println("\u001B[36m Oh no, fallaste el tiro\u001B[0m");
                tablero[x][y] = "⚪️";
            }
        }
        else if ((x < 0 || x >= numFilas) || (y < 0 || y >= numCols))  //invalid guess
            System.out.println("No puedes colocar barcos fuera de " + numFilas + " por " + numCols + " del tablero.");
    } while((x < 0 || x >= numFilas) || (y < 0 || y >= numCols)); 
} // ------ fin del turno del jugador

   public static void turnoCompu(){
    System.out.println("\nTURNO DEL CONTRINCANTE");
    //Guess co-ordinates
    int x = -1, y = -1;
    do {
        x = (int)(Math.random() * numCols);
        y = (int)(Math.random() * numFilas);
        // System.out.println(x+","+y);
        if ((x >= 0 && x < numFilas) && (y >= 0 && y < numCols)) //valid guess
        {
            if (tablero[x][y] == "🚢") //if player ship is already there; player loses ship
            {
                System.out.println("El contrincante hundió uno de tus barcos");
                tablero[x][y] = "🧨";
                --playerShips;
            }
            else if (tablero[x][y] == "🚤") {
                System.out.println("El contrincante hundió su propio barco");
                tablero[x][y] = "❌";
                --compShips;
            }
            else if (tablero[x][y] == "🀆") {
                System.out.println("El contrincante falló");
                tablero[x][y] = "⬜️";
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
    pressAnyKeyToContinue();
   
    printTablero("");

    System.out.println();
    System.out.println("Barcos del jugador " + playerShips + " | Barcos del contrincante " + compShips);
    System.out.println();

} // fin batalla 

  public static void gameOver(){
        if(playerShips > 0 && compShips <= 0)
            System.out.println("🧨¡GANASTE!🧨\n ERES EL CAPITÁN SUPREMO");
        else
            System.out.println("😵AHHHHH TE HUNDIERON TODOS TUS BARCOS😵");
        System.out.println();

   } // game over

  public static void pressAnyKeyToContinue()
      { 
        System.out.println("Presiona ENTER para continuar");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
      }
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
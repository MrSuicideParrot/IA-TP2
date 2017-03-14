import java.util.LinkedList;

class Tabuleiro{
  /* Inicio dos Movimentos ganhadores */
  //Movimentos de diagonais y = x
  //Lado este
  private static final int[] MovX_D1_E = {1, 2, 3};
  private static final int[] MovY_D1_E = {1, 2, 3};
  //Lado oeste
  private static final int[] MovX_D1_O = {-1, -2, -3};
  private static final int[] MovY_D1_O = {-1, -2, -3};

  //Movimentos de diagonais y = -x
  //Lado este
  private static final int[] MovX_D2_E = {1, 2, 3};
  private static final int[] MovY_D2_E = {-1, -2, -3};
  //Lado oeste
  private static final int[] MovX_D2_O = {-1, -2, -3};
  private static final int[] MovY_D2_O = {1, 2, 3};

  //Movimentos Horizontal
  //Este
  private static final int[] MovX_HO_E = {1, 2, 3};
  private static final int[] MovY_HO_E = {0, 0, 0};
  //Oeste
  private static final int[] MovX_HO_O = {-1, -2, -3};
  private static final int[] MovY_HO_O = {0, 0, 0};

  //Movimentos Vertical
  //Norte
  private static final int[] MovX_VE_N = {0, 0, 0};
  private static final int[] MovY_VE_N = {1, 2, 3};
  //Sul
  private static final int[] MovX_VE_S = {0, 0, 0};
  private static final int[] MovY_VE_S = {-1, -2, -3};
  /* -------------------------------*/


  private static final int dimY = 6;
  private static final int dimX = 7;

  private static final char emp = '-';

  public static final char MACHINE = 'X';
  public static final char HUMAN = 'O';

  private char[][] tabu;
  private char User;

  private Boolean winner;
  private Integer evalValue ;
  LinkedList<Tabuleiro> filhos;

  Tabuleiro(){ // começar ao contrario
    tabu = new char[dimY][dimX];
    //this.User = null;
    evalValue = null;
    filhos = null;
    winner = null;

    //Tabuleiro branco
    for (int i = 0;i < dimY ;++i )
      for (int j = 0 ;j < dimX ;++j )
        tabu[i][j] = emp;
  }

  Tabuleiro(char[][] m, char User){
    tabu = new char[dimY][dimX];
    this.User = User;
    MatrizCopy.copy(dimX,dimY, tabu, m);
    evalValue = null;
    filhos = null;
    winner = null;
  }

  public LinkedList<Tabuleiro> nextRound(){
    if(filhos != null)
      return filhos;

    char nextUser;
    Tabuleiro aux;
    switch  (User){
      case 'X':
        nextUser = 'O';
        break;

      case 'O':
        nextUser = 'X';
        break;

      default:
        nextUser = '*';
        System.err.println("Erro: Jogador não encontrado!");
        System.exit(0);
    }

    LinkedList<Tabuleiro> round = new LinkedList<Tabuleiro>();
    for (int i = 0;i < dimX ;++i)
      round.add(nextRound(i, nextUser));
    filhos = round;
    return round;
  }

  public Tabuleiro nextRound(int row, char nextUser){
    Tabuleiro aux = new Tabuleiro(this.tabu, nextUser);
      for (int j = dimY-1;j>=0; --j) {
        if(aux.tabu[j][row] == emp){
          aux.tabu[j][row] = aux.User;
          break;
        }
      }
      return aux;
    }
    private boolean isValid(int y, int x){
      return (0<y && y < dimY && 0<x && x < dimX);
    }

    private int genericWin(int y, int x,int[] movx,int[] movy,int counter){
      for (int i = 0;i < 3 ; ++i) {
        int auxX = (x+movx[i]), auxY = (y+movy[i]);
        if(isValid(auxY,auxX) && tabu[auxY][auxX]==User){
          ++counter;
          if(counter>=4){
            if(User == MACHINE){
              evalValue += 512;
            }
            else{
              evalValue -= 512;
            }
            return counter;
          }
        }
        else{
          if (tabu[auxY][auxX]!=emp) {
           return 10+counter;
          }
          break;
        }
      }
      return counter;
    }

    private boolean winHO(int y, int x){
      int count;
      boolean flag = false;
      //Este
      count = genericWin(y, x, MovX_HO_E, MovY_HO_E,1);

      if(count >= 10){
        count -= 10;
        flag = true;
      }

      if(count>=4)
          return true;

      //Oeste
      count = genericWin(y, x, MovX_HO_O, MovY_HO_O, count);

      if(count >= 10){
        if(flag)
          return false;
        else
          count -= 10; // count = count - 10
      }


      if(count>=4)
          return true;

      int fac;
      if(User == MACHINE){
        fac = 1;
      }
      else{
        fac = -1;
      }

      //contador
      switch(count){
        case 1:
          evalValue += fac;
          break;
        case 2:
          evalValue += (fac*10);
          break;
        case 3:
          evalValue += (fac*50);
          break;
        default:
          System.err.println("Erro switch do eval");
          System.exit(0);
          break;
      }

      return false;
    }

    private boolean winVE(int y, int x){
      int count;
      boolean flag = false;
      //Este
      count = genericWin(y, x, MovX_VE_N, MovY_VE_N,1);

      if(count >= 10){
        count -= 10;
        flag = true;
      }

      if(count>=4)
          return true;

      //Oeste
      count = genericWin(y, x, MovX_VE_S, MovY_VE_S, count);

      if(count>=4)
          return true;

          if(count >= 10){
            if(flag)
              return false;
            else
          count -= 10; // count = count - 10
        }

      //EVALUATED
          int fac;
          if(User == MACHINE){
            fac = 1;
          }
          else{
            fac = -1;
          }

          //contador
          switch(count){
            case 1:
            evalValue += fac;
            break;
            case 2:
            evalValue += (fac*10);
            break;
            case 3:
            evalValue += (fac*50);
            break;
            default:
            System.err.println("Erro switch do eval");
            System.exit(0);
            break;
          }


      return false;
    }

    private boolean winD1(int y, int x){
      int count;
      boolean flag = false;
      //Este
      count = genericWin(y, x, MovX_D1_E, MovY_D1_E,1);

      if(count >= 10){
        count -= 10;
        flag = true;
      }

      if(count>=4)
          return true;

      //Oeste
      count = genericWin(y, x, MovX_D1_O, MovY_D1_O, count);

      if(count >= 10){
        if(flag)
          return false;
        else
          count -= 10; // count = count - 10
      }

      if(count>=4)
          return true;

          int fac;
          if(User == MACHINE){
            fac = 1;
          }
          else{
            fac = -1;
          }

          //contador
          switch(count){
            case 1:
              evalValue += fac;
              break;
            case 2:
              evalValue += (fac*10);
              break;
            case 3:
              evalValue += (fac*50);
              break;
            default:
              System.err.println("Erro switch do eval");
              System.exit(0);
              break;
          }


      return false;
    }

    private boolean winD2(int y, int x){
      int count;
      boolean flag = false;
      //Este
      count = genericWin(y, x, MovX_D2_E, MovY_D2_E,1);
      if(count>=4)
          return true;

      if(count == -1)
          return false;
      //Oeste
      count = genericWin(y, x, MovX_D2_O, MovY_D2_O, count);
      if(count>=4)
          return true;

      return false;
    }

  /*
-50 for three Os, no Xs,
-10 for two Os, no Xs,
- 1 for one O, no Xs,
0 for no tokens, or mixed Xs and Os,
1 for one X, no Os,
10 for two Xs, no Os,
50 for three Xs, no Os.
*/
    public boolean win(){
      if(winner != null)
        return winner;

      if(User == MACHINE)
        evalValue = 16;
      else
        evalValue = -16;

      for (int i=0;i < dimX ;++i) {
        for (int j = dimY-1;j >=0 ;++j) {
          if(tabu[j][i] == emp)
            break;
          if(tabu[j][i] == User){
            if(winHO(j,i) || winVE(j,i) || winD1(j,i) || winD2(j,i)){
              winner = true;
              return true;
            }
          }
        }
      }
      winner = false;
      return false;
    }

    public  Integer UTILITY(){
      return evalValue;
    }

    public boolean isFull(){
      for (int j = 0;j<dimX; ++j)
        if(tabu[0][j] == emp)
          return false;
      return true;
    }

    public boolean equals(Object o){
      Tabuleiro p1 = this;
      Tabuleiro p2 = (Tabuleiro)o;
      for (int i = 0;i < dimY ;++i ) {
        for (int j = 0 ;j < dimX ;++j ) {
          if(p1.tabu[i][j]!=p2.tabu[i][j])
            return false;
        }
      }
      return true;
    }

    public String toString(){
      String aux = "0 1 2 3 4 5 6\n\n";
      for (int i = 0;i < dimY ;++i ) {
        for (int j = 0 ;j < dimX ;++j ) {
          aux = aux + tabu[i][j]+" ";
        }
        aux = aux + '\n';
      }
      return aux;
    }
}

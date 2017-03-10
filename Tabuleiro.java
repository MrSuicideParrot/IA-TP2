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

  private char[][] tabu;
  private char User;

  Tabuleiro(char[][] m, char User){
    tabu = new char[dimY][dimX];
    this.lastUser = lastUser;
    MatrizCopy.copy(dimX,dimY, tabu, m);
  }

  public LinkedList<Tabuleiro> nextRound(){
    LinkedList<Tabuleiro> round = new LinkedList<Tabuleiro>();
    for (int i = 0;i < dimX ;++i)
      round.add(nextRound(i));
    return round;
  }

  private Tabuleiro nextRound(int row){
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

    aux = new Tabuleiro(this.tabu, nextUser);
      for (int j = dimY-1;j>=0; --j) {
        if(aux.tabu[j][row] == emp){
          aux.tabu[j][row] = aux.User;
          break;
        }
      }
      return aux;
    }

    private boolean win(){
    
    }

    private boolean isFull(){
      for (int j = 0;j<dimX; ++j)
        if(aux.tabu[0][j] == emp)
          return false;
      return true;
    }

    public String toString(){
      String aux = "";
      for (int i = 0;i < dimY ;++i ) {
        for (int j = 0 ;j < dimX ;++j ) {
          aux = aux + tabu[i][j]+" ";
        }
        aux = aux + '\n';
      }
      return aux;
    }

  }

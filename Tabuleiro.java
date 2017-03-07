import java.util.LinkedList;

class Tabuleiro{
  private static final int dimY = 6;
  private static final int dimX = 7;

  private static final char emp = '-';

  private char[][] tabu;
  private char lastUser;

  Tabuleiro(char[][] m, char lastUser){
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
    switch  (lastUser){
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
          aux.tabu[j][row] = aux.lastUser;
          break;
        }
      }
      return aux;
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

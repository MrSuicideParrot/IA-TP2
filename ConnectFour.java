import java.util.Scanner;
public class ConnectFour{

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Tabuleiro inicial = new Tabuleiro();
    MinMax bot = new MinMax();
    System.out.println(inicial);
    System.out.print("Insira onde pretende jogar: ");
    Tabuleiro jogada = inicial.nextRound(input.nextInt(),Tabuleiro.HUMAN);
    System.out.println(jogada);
    MinMax.MINIMAX_DECISION(jogada);
    System.out.println();

  }
}

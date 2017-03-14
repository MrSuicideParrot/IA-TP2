import java.util.Scanner;
public class ConnectFour{

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Tabuleiro inicial = new Tabuleiro();
    MinMax bot = new MinMax();
    System.out.println(inicial);
    System.out.print("Insira onde pretende jogar: ");
    Tabuleiro jogada = inicial.nextRound(input.nextInt(),Tabuleiro.HUMAN);
    while(true){
      System.out.println(jogada);
      if(jogada.win()){
        System.out.println("Ganhaste aind abem para ti, mal para quem fez o jogo!");
        break;
      }
      jogada = bot.MINIMAX_DECISION(jogada);
      System.out.println(jogada);
      if(jogada.winner){
        System.out.println("A maquina ganhou nao te queixes quando amaquina te tentar excluir da terra!");
        break;
      }
      System.out.print("Insira onde pretende jogar: ");
      jogada = jogada.nextRound(input.nextInt(),Tabuleiro.HUMAN);
    }
  }
}

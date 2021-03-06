import java.util.Scanner;
public class ConnectFour{

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Bem vindo ao ConnectFour");
    System.out.println("Contra qual algoritmo é que pretende jogar?");
    System.out.println("1 - MinMax");
    System.out.println("2 - AlfaBeta");
    int algo = input.nextInt();
    System.out.print("Insira a profundidade máxima, com que pretenda que a pesquisa seje efetuada: ");
    int prof = input.nextInt();
    //bots
    MinMax bot1 = null;
    AlfaBeta bot2 = null;

    switch (algo){
      case 1:
        bot1 = new MinMax(prof);
        break;

      case 2:
        bot2 = new AlfaBeta(prof);
        break;

      default:
        System.err.println("Opção não reconhecida");
        System.exit(0);
    }
    //Tabuleiro inicial = new Tabuleiro();
    Tabuleiro jogada = new Tabuleiro();

    /* Decisão de quem é que joga primeiro */
    while(true){
      System.out.println("Prentede que seja a máquina a jogar em primeiro lugar?[y/n]");
      switch(input.next()){
        case "y":
          if(algo == 1)
            jogada = bot1.DECISION(jogada);
          else
            jogada = bot2.DECISION(jogada);

          System.out.println(jogada);
          break;
        case "n":
          System.out.println("Tabuleiro vazio:");
          System.out.println(jogada);
          break;

        default:
          System.err.println("Opção inválida!");
          continue;
      }
      break;
    }


    System.out.print("Insira onde pretende jogar: ");
    jogada = jogada.nextRound(input.nextInt(),Tabuleiro.HUMAN);

    while(true){
      System.out.println("-Jogada do Humano-----\n");
      System.out.println(jogada);
      if(jogada.win(0)){
        System.out.println("Ganhaste ainda bem para ti, mal para quem fez o jogo!!");
        break;
      }
      if(jogada.isFull()){
        System.out.println("Parabéns, empataste!!");
        break;
      }
      //bot
      long startTime = System.currentTimeMillis();
      if(algo == 1)
        jogada = bot1.DECISION(jogada);
      else
        jogada = bot2.DECISION(jogada);
      System.out.println("-Jogada do BOT-----\n"+(double)(System.currentTimeMillis() - startTime) / 1000.0+" segundos.");
      if(algo == 1)
        System.out.println(bot1.nos+"nos visitados.");
      else
        System.out.println(bot2.nos+"nos visitados.");

      System.out.println(jogada);
      if(jogada.winner){
        System.out.println("A máquina ganhou, nao te queixes quando ela te tentar eliminar da terra!");
        break;
      }
      if(jogada.isFull()){
        System.out.println("Parabéns, empataste!!");
        break;
      }
      System.out.print("Insira onde pretende jogar: ");
      jogada = jogada.nextRound(input.nextInt(),Tabuleiro.HUMAN);
    }
  }
}

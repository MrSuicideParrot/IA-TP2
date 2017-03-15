class AlfaBeta{
  //Alpha = melhor opção explorada até agora maximizante
  //Beta = melhor opção explorada até agora minimizante
  private final static int profundidade_maxima = 6;
  private Tabuleiro lastMove;

  AlfaBeta(){
      lastMove = null;
  }

  public Tabuleiro DECISION(Tabuleiro tabu_adversario){
    Tabuleiro tabu_inicial=null;
    if(lastMove != null){
      //Se correr mal comentar a abaixo
      for (Tabuleiro aux : lastMove.nextRound()) {
        if(aux.equals(tabu_adversario)){
          tabu_inicial = aux;
          break;
        }
      }
    }
    else
      tabu_inicial = tabu_adversario;

    int max = Integer.MIN_VALUE;
    Tabuleiro max_node = null;
    for(Tabuleiro aux : tabu_inicial.nextRound()){
      int aux_max=MIN_VALUE(aux, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
      if (max<aux_max) { //ver quando tem coisas iguais
        max = aux_max;
        max_node = aux;
      }
    }
    lastMove = max_node;
    return max_node;
  }


  private int MAX_VALUE(Tabuleiro tabu_inicial,int altura,int alfa, int beta){
      if(tabu_inicial.win() || altura>=profundidade_maxima || tabu_inicial.isFull()){
         return tabu_inicial.UTILITY();
      }
      int max = Integer.MIN_VALUE;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        max = Math.max(max,MIN_VALUE(aux,altura+1, alfa, beta));
        if(max >= beta){
          return max;
        }
        else{
          alfa=Math.max(alfa,max);
        }
      }
      return max;
    }



  private int MIN_VALUE(Tabuleiro tabu_inicial,int altura,int alfa, int beta){
    if(tabu_inicial.win() || altura>=profundidade_maxima || tabu_inicial.isFull()){
       return tabu_inicial.UTILITY();
    }
    int min = Integer.MAX_VALUE;
    for(Tabuleiro aux : tabu_inicial.nextRound()){
      min = Math.min(min,MIN_VALUE(aux,altura+1, alfa, beta));
      if(min <= alfa){
        return min;
      }
      else{
        beta = Math.min(beta,min);
      }
    }
    return min;
  }
}

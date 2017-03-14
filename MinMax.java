class MinMax{
    private final static profundidade_maxima = 6;

    private Tabuleiro lastMove;
    MinMax(){

    }

    public Tabuleiro MINIMAX_DECISION(Tabuleiro tabu_adversario){
      Tabuleiro tabu_inicial;

      //Se correr mal comentar a abaixo
      for (Tabuleiro aux : lastMove.nextRound()) {
        if(aux.equals(tabu_adversario)){
          tabu_inicial = aux;
          break;
        }
      }

      //tabu_inicial == tabu_adversario;
      int max = Integer.MIN_VALUE;
      Tabuleiro max_node = NULL;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        int aux_max=MIN_VALUE(aux,2);
        if (max<aux_max) { //ver quando tem coisas iguais
          max = aux_max;
          max_node = aux;
        }
      }
      lastMove = max_node;
      return max_node;
    }


  private int MAX_VALUE(Tabuleiro tabu_inicial,int altura){
      if(tabu_inicial.win() || altura>=profundidade_maxima){
         return tabu_inicial.UTILITY();
      }
      int max = Integer.MIN_VALUE;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        max = Math.max(aux_max,MIN_VALUE(aux,altura+1));
      }
      return max;
    }



  private int MIN_VALUE(Tabuleiro tabu_inicial,int altura){
    if(tabu_inicial.win() || altura>=profundidade_maxima){
       return tabu_inicial.UTILITY();
    }
    int min = Integer.MAX_VALUE;
    for(Tabuleiro aux : tabu_inicial.nextRound()){
      min = Math.min(aux_min,MIN_VALUE(aux,altura+1));
    }
    return min;
  }
}

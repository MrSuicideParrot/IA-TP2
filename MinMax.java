class MinMax{
    private final static profundidade_maxima = 6;

    MinMax(){

    }

    public Tabuleiro MINIMAX_DECISION(Tabuleiro tabu_inicial){
      int max = Integer.MIN_VALUE;
      Tabuleiro max_node = NULL;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        int aux_max=MIN_VALUE(aux,2);
        if (max<aux_max) { //ver quando tem coisas iguais
          max = aux_max;
          max_node = aux;
        }
      }
      return max_node;
    }


  private int MAX_VALUE(Tabuleiro tabu_inicial,int altura){
      if(tabu_inicial.win()){
        return 1000; //a defenir
      }
      if(altura>=profundidade_maxima){
         return tabu_inicial.UTILITY();
      }
      int max = Integer.MIN_VALUE;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        max = Math.max(aux_max,MIN_VALUE(aux,altura+1));
      }
      return max;
    }



  private int MIN_VALUE(Tabuleiro tabu_inicial,int altura){
    if(tabu_inicial.win()){
      return 1000; //a defenir
    }
    if(altura>=profundidade_maxima){
       return tabu_inicial.UTILITY();
    }
    int min = Integer.MAX_VALUE;
    for(Tabuleiro aux : tabu_inicial.nextRound()){
      min = Math.min(aux_min,MIN_VALUE(aux,altura+1));
    }
    return min;
  }
}

class MinMax{
    private int profundidade_maxima;
    private Tabuleiro lastMove;

    MinMax(int i){
      lastMove = null;
      profundidade_maxima = i;
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
      int localalt = Integer.MIN_VALUE;
      Tabuleiro max_node = null;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        int aux_max=MIN_VALUE(aux,2);
        //System.out.println(aux+" "+aux_max);
        if (max<aux_max ||(max==aux_max && localalt > aux.alturaSol)) { //ver quando tem coisas iguais
          max = aux_max;
          max_node = aux;
          //System.out.println(aux.alturaSol);
          localalt = aux.alturaSol;
        }
      }
      lastMove = max_node;
      return max_node;
    }


  private int MAX_VALUE(Tabuleiro tabu_inicial,int altura){
      if(tabu_inicial.win(altura) || altura>=profundidade_maxima || tabu_inicial.isFull()){
         return tabu_inicial.UTILITY();
      }
      int max = Integer.MIN_VALUE;
      int localalt = Integer.MIN_VALUE;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        //max = Math.max(max,MIN_VALUE(aux,altura+1));
        int d = MIN_VALUE(aux,altura+1);
        if(max<d || (max==d && localalt > aux.alturaSol)) { //ver quando tem coisas iguais
          max = d;
          localalt = aux.alturaSol;
        }
      }
      tabu_inicial.alturaSol = localalt;
      //return (max+tabu_inicial.UTILITY());
      return max;
    }



  private int MIN_VALUE(Tabuleiro tabu_inicial,int altura){
    if(tabu_inicial.win(altura) || altura>=profundidade_maxima || tabu_inicial.isFull()){
       return tabu_inicial.UTILITY();
    }
    int min = Integer.MAX_VALUE;
    int localalt = Integer.MIN_VALUE;
    for(Tabuleiro aux : tabu_inicial.nextRound()){
      //min = Math.min(min,MAX_VALUE(aux,altura+1));
      int d = MAX_VALUE(aux,altura+1);
      if(min>d || (min==d && localalt > aux.alturaSol)) { //ver quando tem coisas iguais
        min = d;
        localalt = aux.alturaSol;
      }
    }
    //return (min+tabu_inicial.UTILITY());
    tabu_inicial.alturaSol = localalt;
    return min;
  }
}

class AlfaBeta{
  //Alpha = melhor opção explorada até agora maximizante
  //Beta = melhor opção explorada até agora minimizante
  public int nos;
  private int profundidade_maxima;
  private Tabuleiro lastMove;

  AlfaBeta(int prof){
      lastMove = null;
      profundidade_maxima = prof;
  }

  public Tabuleiro DECISION(Tabuleiro tabu_adversario){
    nos = 0;
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
      int aux_max=MIN_VALUE(aux, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
      if (max<aux_max ||(max==aux_max && localalt > aux.alturaSol)) { //ver quando tem coisas iguais
        ++nos;
        max = aux_max;
        max_node = aux;
        localalt = aux.alturaSol;
      }
    }
    lastMove = max_node;
    return max_node;
  }


  private int MAX_VALUE(Tabuleiro tabu_inicial,int altura,int alfa, int beta){
      if(tabu_inicial.win(altura) || altura>=profundidade_maxima || tabu_inicial.isFull()){
         return tabu_inicial.UTILITY();
      }
      int max = Integer.MIN_VALUE;
      int localalt = Integer.MIN_VALUE;
      for(Tabuleiro aux : tabu_inicial.nextRound()){
        ++nos;
        //max = Math.max(max,MIN_VALUE(aux,altura+1, alfa, beta));
        int d = MIN_VALUE(aux,altura+1, alfa, beta);
        if(max<d || (max==d && localalt > aux.alturaSol)) { //ver quando tem coisas iguais
          max = d;
          localalt = aux.alturaSol;
        }
        if(max >= beta){
          return max;
        }
        else{
          alfa=Math.max(alfa,max);
        }
      }
      tabu_inicial.alturaSol = localalt;
      return max;
    }



  private int MIN_VALUE(Tabuleiro tabu_inicial,int altura,int alfa, int beta){
    if(tabu_inicial.win(altura) || altura>=profundidade_maxima || tabu_inicial.isFull()){
       return tabu_inicial.UTILITY();
    }
    int min = Integer.MAX_VALUE;
    int localalt = Integer.MIN_VALUE;
    for(Tabuleiro aux : tabu_inicial.nextRound()){
      ++nos;
      //min = Math.min(min,MAX_VALUE(aux,altura+1, alfa, beta));
      int d = MAX_VALUE(aux,altura+1, alfa, beta);
      if(min>d || (min==d && localalt > aux.alturaSol)) { //ver quando tem coisas iguais
        min = d;
        localalt = aux.alturaSol;
      }
      if(min <= alfa){
        return min;
      }
      else{
        beta = Math.min(beta,min);
      }
    }
    tabu_inicial.alturaSol = localalt;
    return min;
  }
}

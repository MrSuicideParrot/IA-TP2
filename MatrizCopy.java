class MatrizCopy{
  public static void copy(int dimX,int dimY, char[][] dest, char[][] source){
    for (int i = 0;i < dimX ;++i ) {
      for (int j = 0;j < dimY ;++j) {
        dest[j][i] = source[j][i];
      }
    }
  }
}

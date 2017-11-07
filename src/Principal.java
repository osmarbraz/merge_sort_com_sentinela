/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides 23 da aula do dia 18/08/2017 
 * Mergesort com sentinela
 */

public class Principal {

    /**
     * Realiza o merge com sentinela.
     * Em comentario o algoritmo orginal
     * @param A Vetor a ser ordenado
     * @param q Pivo do vetor
     * @param p Inicio do vetor
     * @param r Fim do vetor     
     */
    public static void merge(int A[], int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int L[] = new int[n1 + 1];
        int R[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            //L[i] = A[p+i-1];
            L[i] = A[p + i];
        }

        for (int j = 0; j < n2; j++) {
            //R[j] = A[q+j];
            R[j] = A[q + j + 1];
        }
        //Original 
        //L[n1+1] = Integer.MAX_VALUE;
        L[n1] = Integer.MAX_VALUE;
        //R[n2+1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        //int i = 1;
        int i = 0;
        //int j = 1;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i = i + 1;
            } else {
                A[k] = R[j];
                j = j + 1;
            }
        }
    }

    /**
     * Mergesort com sentinela
     * T(n) = T(n/2) + T(n/2) + Theta(n)
     * Complexidade no pior caso é Theta(n^2)
     * Complexidade no caso médio/esperado é Theta(n log n)
     * @param A Vetor a ser ordenado
     * @param p Inicio do vetor
     * @param r Fim do vetor
     */
    public static void mergesort(int A[], int p, int r) {
        if (p < r) {                            //Theta(1)                
            int q = (p + r) / 2;                //Theta(1)
            mergesort(A, p, q);                 //T(n/2)
            mergesort(A, q + 1, r);             //T(n/2)
            merge(A, p, q, r);                  //Theta(n)
        }
    }

    public static void main(String args[]) {
        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};
        
        //Fim do vetor
        int n = A.length - 1;

        System.out.println(">>> MergeSort <<<");
        System.out.println("Original: ");
        for (int i = 0; i <= n; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }

        mergesort(A, 0, n);

        System.out.println("Depois: ");
        for (int i = 0; i <= n; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }
    }
}

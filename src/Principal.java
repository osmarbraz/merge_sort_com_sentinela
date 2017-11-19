/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 23 da aula do dia 18/08/2017 
 *
 * Página 22 Thomas H. Cormen 3 ed
 *
 * Mergesort com sentinela
 *
 * Atenção:
 * Vetor em java inicia em 0, os algoritmos consideram início em 1.
 * A subtraçào de -1 ocorre somente no local de acesso ao vetor ou matriz 
 * para manter a compatibilidade entre os algoritmos.
 *
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    /**
     * O piso (= floor) de um número real x é o resultado do arredondamento de x
     * para baixo. Em outras palavras, o piso de x é o único número inteiro i
     * tal que i<=x<i+1. Ex. O piso de 3.9 é 3.
     *
     * Em java pode ser utilizando Math.floor(double)
     *
     * @param x Número real a ser calculado o piso.
     * @return um valor inteiro com o piso de x.
     */
    public static int piso(double x) {
        //Pego a parte inteira de x
        int parteInteira = (int) x;
        //Pego a parte fracionária de x
        double parteFracionaria = x - parteInteira;
        //Retorno x subtraindo a parte fracionária 
        return (int) (x - parteFracionaria);
    }
    
    
    /**
     * Realiza o merge com sentinela.
     * Em comentario o algoritmo orginal
     * @param A Vetor a ser ordenado
     * @param q Pivô do vetor
     * @param p Inicio do vetor
     * @param r Fim do vetor     
     */
    public static void merge(int A[], int p, int q, int r) {
        int n1 = q - p + 1; // Tamanho do início (p) até o pivô (q)
        int n2 = r - q; // Tamanho do pivô (q) até o final (r)
        int L[] = new int[n1 + 1]; // n1 + 1 elementos, indexados de 0..n1
        int R[] = new int[n2 + 1]; // n2 + 1 elementos, indexados de 0..n2

        // Inicializa L com a primeira parte do vetor A
        for (int i = 1; i <= n1; i++) {            
            L[i-1] = A[p+i-1-1];
        }

        // Inicializa R com a segunda parte do vetor A
        for (int j = 1; j <= n2; j++) {            
            R[j-1] = A[(q + j)-1]; //(q + 1) -> após o pivô
        }
        L[n1+1-1] = Integer.MAX_VALUE;        
        R[n2+1-1] = Integer.MAX_VALUE;        
        int i = 1;        
        int j = 1;
        for (int k = p; k <= r; k++) {
            if (L[i-1] <= R[j-1]) {
                A[k-1] = L[i-1];
                i = i + 1;
            } else {
                A[k-1] = R[j-1];
                j = j + 1;
            }
        }
    }

    /**
     * Mergesort com sentinela.
     * Algoritmos de ordenação podem ser ou não in-place ou estáveis.
     * Um método de ordenação é estável se elementos iguais ocorrem no 
     * vetor ordenado na mesma ordem em que são passados na entrada.
     * O mergesort é estável. 
     * 
     * T(n) = T(n/2) + T(n/2) + Theta(n)
     * Complexidade no pior caso é Theta(n^2)
     * Complexidade no caso médio/esperado é Theta(n log n)
     * @param A Vetor a ser ordenado
     * @param p Inicio do vetor
     * @param r Fim do vetor
     */
    public static void mergesort(int A[], int p, int r) {
        if (p < r) {                            //Theta(1)                
            int q = piso((p + r) / 2);          //Theta(1)
            mergesort(A, p, q);                 //T(n/2)
            mergesort(A, q + 1, r);             //T(n/2)
            merge(A, p, q, r);                  //Theta(n)
        }
    }

    public static void main(String args[]) {
        
        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};
        //Quantidade de elementos
        int r = A.length;
        //Início do vetor
        int p = 1;

        System.out.println(">>> MergeSort com sentinela <<<");
        System.out.println("Original: ");
        for (int i = 0; i < r; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }

        mergesort(A, p, r);

        System.out.println("Depois: ");
        for (int i = 0; i < r; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }
    }
}

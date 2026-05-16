public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        arvoreBinaria.inserir(0);
        arvoreBinaria.inserir(-3);
        arvoreBinaria.inserir(-5);
        arvoreBinaria.inserir(-4);
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(3);
        arvoreBinaria.inserir(4);
        arvoreBinaria.inserir(10);
        arvoreBinaria.inserir(7);
        arvoreBinaria.inserir(9);

        System.out.println("\n=== Em-Ordem antes das remoções ===");
        arvoreBinaria.exibir("Em");

        // Caso 1: remoção de nó folha
        System.out.println("\n=== Removendo nó folha: -4 ===");
        arvoreBinaria.remover(-4);
        arvoreBinaria.exibir("Em");

        // Caso 2: remoção de nó com um filho
        System.out.println("\n=== Removendo nó com um filho: -3 ===");
        arvoreBinaria.remover(-3);
        arvoreBinaria.exibir("Em");

        // Caso 3: remoção de nó com dois filhos (sucessor)
        System.out.println("\n=== Removendo nó com dois filhos: 5 ===");
        arvoreBinaria.remover(5);
        arvoreBinaria.exibir("Em");

        // Caso extra: remoção da raiz
        System.out.println("\n=== Removendo a raiz: 0 ===");
        arvoreBinaria.remover(0);
        arvoreBinaria.exibir("Em");
    }
}
public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = new No(null);
        System.out.println("Árvore Binária criada com sucesso!");
    }

    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);

        if(estaVazia()) {
            this.raiz = novoNo;
        } else {
            inserirRecursivo(novoNo, this.raiz);
        }
    }

    public void inserirRecursivo(No novoNo, No atual) {
        if (atual.getConteudo() > novoNo.getConteudo()) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(novoNo);
                System.out.println("O nó " + novoNo.getConteudo() + " foi inserido na Árvore.");
                return;
            } else {
                inserirRecursivo(novoNo, atual.getEsquerda());
            }
        } else if (atual.getConteudo() == novoNo.getConteudo()) {
            System.out.println("Não é possível informar nós repetidos.");
            return;
        } else {
            if (atual.getDireita() == null) {
                atual.setDireita(novoNo);
                System.out.println("O nó " + novoNo.getConteudo() + " foi inserido na Árvore.");
                return;
            } else {
                inserirRecursivo(novoNo, atual.getDireita());
            }
        }
    }

    public void remover(Integer conteudo) {
        if (estaVazia()) {
            System.out.println("Não é possível remover de uma Árvore vazia.");
            return;
        }
        this.raiz = removerRecursivo(conteudo, this.raiz);
    }

    private No removerRecursivo(Integer conteudo, No atual) {
        if (atual == null) {
            System.out.println("O nó " + conteudo + " não foi encontrado na Árvore.");
            return null;
        }

        if (conteudo < atual.getConteudo()) {
            atual.setEsquerda(removerRecursivo(conteudo, atual.getEsquerda()));
        } else if (conteudo > atual.getConteudo()) {
            atual.setDireita(removerRecursivo(conteudo, atual.getDireita()));
        } else {
            // Caso 1: nó folha (sem filhos)
            if (atual.getEsquerda() == null && atual.getDireita() == null) {
                System.out.println("O nó " + conteudo + " (folha) foi removido da Árvore.");
                return null;
            }

            // Caso 2: nó com apenas um filho
            if (atual.getEsquerda() == null) {
                System.out.println("O nó " + conteudo + " (um filho) foi removido da Árvore.");
                return atual.getDireita();
            }
            if (atual.getDireita() == null) {
                System.out.println("O nó " + conteudo + " (um filho) foi removido da Árvore.");
                return atual.getEsquerda();
            }

            // Caso 3: nó com dois filhos — substitui pelo sucessor (menor dos maiores)
            No sucessor = encontrarSucessor(atual.getDireita());
            System.out.println("O nó " + conteudo + " (dois filhos) foi removido da Árvore. Substituído pelo sucessor " + sucessor.getConteudo() + ".");
            atual.setConteudo(sucessor.getConteudo());
            atual.setDireita(removerRecursivo(sucessor.getConteudo(), atual.getDireita()));
        }

        return atual;
    }

    private No encontrarSucessor(No no) {
        if (no.getEsquerda() == null) {
            return no;
        }
        return encontrarSucessor(no.getEsquerda());
    }

    public boolean estaVazia() {
        if(this.raiz.getConteudo() == null) {
            return true;
        } else {
            return false;
        }
    }

    private void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getConteudo());
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsquerda());
        System.out.println(no.getConteudo());
        emOrdem(no.getDireita());
    }

    private void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.println(no.getConteudo());
    }

    public void exibir(String percurso) {
        switch (percurso){
            case("Pre"):
                preOrdem(this.raiz);
                break;
            case("Em"):
                emOrdem(this.raiz);
                break;
            case("Pos"):
                posOrdem(this.raiz);
                break;
        }
    }


}
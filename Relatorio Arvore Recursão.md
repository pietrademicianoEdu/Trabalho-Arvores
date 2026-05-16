Relatório — Implementação de Remoção em Árvore Binária de Busca  
**Disciplina:** Estrutura de Dados  
---

1\. Descrição da implementação  
A funcionalidade de remoção foi adicionada à classe **ArvoreBinaria** por meio de dois métodos com o auxílio do método “encontrarSucessor()”:

- **remover(Integer conteudo)** — método público, ponto de entrada da operação. Verifica se a árvore está vazia antes de iniciar a remoção.  
- **removerRecursivo(Integer conteudo, No atual)** — método privado que realiza a remoção propriamente dita, retornando o nó atualizado a cada nível da recursão.  
- **encontrarSucessor(No no)** — método auxiliar privado que localiza o sucessor in-order (o menor nó da subárvore direita) de forma recursiva.

---

2\. Casos tratados  
2.1 Nó folha (sem filhos)  
Quando o nó encontrado não possui filhos à esquerda nem à direita, o método retorna null, fazendo com que o pai perca a referência para esse nó. A memória passa a ser elegível para coleta pelo Garbage Collector da JVM.  
Antes:   \-3

           \\

           \-4  ← folha

Após remover \-4:   \-3

2.2 Nó com um filho  
Quando o nó possui apenas um filho (esquerdo ou direito), o método retorna diretamente esse filho. O pai do nó removido passa a apontar para o neto, mantendo a estrutura da árvore intacta.  
Antes:   \-3

         /

        \-5  ← só tem filho direito (-4, já removido)

Após remover \-3:   \-5

2.3 Nó com dois filhos — Sucessor (menor dos maiores)  
Quando o nó possui dois filhos, a estratégia adotada é a do **Sucessor In-Order**, também conhecida como "menor dos maiores":

1. Encontra-se o menor nó da subárvore direita do nó a ser removido (o sucessor).  
2. O conteúdo do nó atual é substituído pelo conteúdo do sucessor.  
3. O sucessor é então removido recursivamente da subárvore direita — operação que, neste ponto, recai em um dos casos anteriores (folha ou um filho), pois o sucessor, sendo o menor da subárvore direita, nunca possui filho à esquerda.

Antes de remover 5:

        0

         \\

          5

         / \\

        3   10

         \\  /

          4 7

             \\

              9

Sucessor de 5 → menor da subárvore direita (10, 7, 9\) → 7

Após remover 5:

        0

         \\

          7

         / \\

        3   10

         \\  /

          4 9

---

3\. Métodos recursivos  
A escolha pela abordagem recursiva foi escolhida com base em 3 critérios:  
3.1 O código existente já usava Recursão  
O projeto já adota recursão tanto na inserção (**inserirRecursivo**) quanto nas três travessias (**preOrdem, emOrdem, posOrdem**). Implementar a remoção de forma iterativa quebraria a padronização do código, introduzindo uma complexidade desnecessária de controle manual de referências. Manter a recursão preserva a previsibilidade do projeto para quem vai ler ou fazer a manutenção do código.  
3.2 A recursão simplifica a reconexão da árvore  
O ponto mais delicado da remoção não é encontrar o nó — é reconectar corretamente a subárvore após a remoção. A abordagem recursiva com retorno de nó (return atual) resolve isso de forma natural: cada chamada retorna o nó que deve ocupar aquela posição na árvore, e o pai simplesmente atualiza sua referência via **setEsquerda** ou **setDireita**. Na abordagem iterativa, essa reconexão exige manter explicitamente uma referência ao pai e ao lado (esquerdo/direito) em que o filho está — o que aumenta a quantidade de variáveis auxiliares e, consequentemente, a chance de erro.  
3.3 Legibilidade e manutenibilidade  
A versão recursiva torna os três casos de remoção facilmente identificáveis e separados dentro do mesmo bloco lógico. Para uma árvore binária de busca sem balanceamento (como é o caso deste projeto), o nível de profundidade raramente é suficiente para causar estouro de pilha (**StackOverflowError**) em uso normal, tornando a preocupação com performance de chamadas recursivas irrelevante neste contexto.  
---

4\. Saída esperada ao executar Main.java  
Árvore Binária criada com sucesso\!

O nó \-3 foi inserido na Árvore.

O nó \-5 foi inserido na Árvore.

O nó \-4 foi inserido na Árvore.

O nó 5 foi inserido na Árvore.

O nó 3 foi inserido na Árvore.

O nó 4 foi inserido na Árvore.

O nó 10 foi inserido na Árvore.

O nó 7 foi inserido na Árvore.

O nó 9 foi inserido na Árvore.

\=== Em-Ordem antes das remoções \===

\-5  \-4  \-3  0  3  4  5  7  9  10

\=== Removendo nó folha: \-4 \===

O nó \-4 (folha) foi removido da Árvore.

\-5  \-3  0  3  4  5  7  9  10

\=== Removendo nó com um filho: \-3 \===

O nó \-3 (um filho) foi removido da Árvore.

\-5  0  3  4  5  7  9  10

\=== Removendo nó com dois filhos: 5 \===

O nó 5 (dois filhos) foi removido da Árvore. Substituído pelo sucessor 7\.

\-5  0  3  4  7  9  10

\=== Removendo a raiz: 0 \===

O nó 0 (dois filhos) foi removido da Árvore. Substituído pelo sucessor 3\.

\-5  3  4  7  9  10

A travessia em-ordem sempre retorna os elementos em ordem crescente, confirmando que a propriedade da BST foi preservada após cada remoção.  

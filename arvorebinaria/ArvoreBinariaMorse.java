package arvorebinaria;

import java.util.Scanner;

public class ArvoreBinariaMorse {

    // Classe interna para representar cada nó da árvore
    private class Node {
        char letra;        // caractere armazenado (ponto de Morse ou letra)
        Node esquerda;     // caminho para ponto (.)
        Node direita;      // caminho para traço (-)

        Node(char letra) {
            this.letra = letra;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private Node raiz;

    // Construtor: inicializa a raiz e carrega o mapa Morse
    public ArvoreBinariaMorse() {
        raiz = new Node('\0');
        inicializar();
    }

    // Insere manualmente todos os códigos Morse sem o uso de arrays ou listas
    private void inicializar() {
        inserir(".-", 'A');    inserir("-...", 'B');  inserir("-.-.", 'C');  inserir("-..", 'D');   inserir(".", 'E');
        inserir("..-.", 'F');  inserir("--.", 'G');   inserir("....", 'H');  inserir("..", 'I');    inserir(".---", 'J');
        inserir("-.-", 'K');   inserir(".-..", 'L');  inserir("--", 'M');    inserir("-.", 'N');    inserir("---", 'O');
        inserir(".--.", 'P');  inserir("--.-", 'Q');  inserir(".-.", 'R');   inserir("...", 'S');   inserir("-", 'T');
        inserir("..-", 'U');   inserir("...-", 'V');  inserir(".--", 'W');   inserir("-..-", 'X'); inserir("-.--", 'Y');
        inserir("--..", 'Z');  inserir("-----", '0'); inserir(".----", '1'); inserir("..---", '2'); inserir("...--", '3');
        inserir("....-", '4'); inserir(".....", '5'); inserir("-....", '6'); inserir("--...", '7'); inserir("---..", '8');
        inserir("----.", '9');
    }

    // Insere um símbolo na árvore seguindo pontos (.) à esquerda e traços (-) à direita
    public void inserir(String codigo, char letra) {
        Node atual = raiz;
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new Node('\0');
                }
                atual = atual.esquerda;
            } else { // assume '-'
                if (atual.direita == null) {
                    atual.direita = new Node('\0');
                }
                atual = atual.direita;
            }
        }
        atual.letra = letra;
    }

    // Busca um código Morse e retorna a letra correspondente (ou '?' se não existir)
    public char buscar(String codigo) {
        Node atual = raiz;
        for (int i = 0; i < codigo.length(); i++) {
            if (atual == null) {
                return '?';
            }
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
        return (atual != null && atual.letra != '\0') ? atual.letra : '?';
    }

    // Decodifica uma linha de texto em código Morse separada por espaços
    public void decodificar(String mensagem) {
        String token = "";
        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            if (c != ' ') {
                token = token + c;
            } else {
                System.out.print(buscar(token));
                token = "";
            }
        }
        // último token
        if (token.length() > 0) {
            System.out.print(buscar(token));
        }
        System.out.println();
    }

    // Exibe a árvore de forma hierárquica (desenho "girado")
    public void exibirArvore() {
        desenhar(raiz, 0);
    }

    private void desenhar(Node node, int nivel) {
        if (node == null) {
            return;
        }
        desenhar(node.direita, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        // imprime letra se existir, caso contrário um '*' para indicar nó intermediário
        System.out.println((node.letra != '\0') ? node.letra : '*');
        desenhar(node.esquerda, nivel + 1);
    }

    // Método principal: mostra a árvore e permite decodificar mensagens
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        System.out.println("Árvore Morse:");
        arvore.exibirArvore();

        System.out.println("\nDigite mensagem em código Morse (separe por espaços):");
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine().trim();
        System.out.println("\nMensagem decodificada:");
        arvore.decodificar(linha);
        sc.close();
    }
}

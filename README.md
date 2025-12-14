# ♟️ Chess Game (Java Swing)

Um jogo de xadrez desenvolvido em **Java**, utilizando **Swing** para a interface gráfica. O projeto implementa as regras principais do xadrez, incluindo movimentação correta das peças, captura, turnos, xeque e xeque-mate, além de um **bot simples** para jogar contra o usuário.

---

## 🎮 Funcionalidades

* Tabuleiro de xadrez 8x8
* Todas as peças implementadas:

  * Rei
  * Rainha
  * Torre
  * Bispo
  * Cavalo
  * Peão
* Regras corretas de movimentação
* Captura de peças
* Interface gráfica com **Java Swing**
* Música de fundo
* Executável `.jar`

---

## 🖥️ Tecnologias Utilizadas

* **Java 17+**
* **Java Swing (JFrame, JPanel, MouseListener)**
* Programação Orientada a Objetos (POO)
* Git & GitHub

---

## 🧠 Estrutura do Projeto

```
ChessGame/
│
├── src/
│   ├── Main.java
│   ├── Board.java
│   ├── Input.java
│   ├── Move.java
│   ├── MusicTheme.java
│   └── Pieces/
│       ├── Pieces.java
│       ├── King.java
│       ├── Queen.java
│       ├── Rook.java
│       ├── Bishop.java
│       ├── Knight.java
│       └── Pawn.java
│
├── resources/
│   ├── sprites/
│   │   └── peças do xadrez (.png)
│   └── ChessMusic.wav
│
├── dist/
│   └── Chess.jar
│
└── README.md
```

---

## 🕹️ Controles

* **Clique e arraste** uma peça para movê-la
* Apenas peças do jogador atual podem ser movimentadas
* Movimentos inválidos são automaticamente cancelados

---

## ▶️ Como Executar

### Executável (.jar)

### Basta ir até a pasta out/artifacts/chessGame e lá contém um executável .jar, chamado "chessGame.jar".
Instale e se divirta!

---

## 🎵 Música

* Música de fundo automática ao iniciar o jogo
* A música para ao fechar a janela

---

## 📦 Build do Executável

O projeto pode ser exportado como `.jar` diretamente pela IDE (IntelliJ / NetBeans / Eclipse).

Recomenda-se manter o executável na pasta:

```
dist/Chess.jar
```

---

## 🚀 Próximas Melhorias

* Turnos
* bOT
* Promoção de peão
* Roque
* Relógio de tempo

---

## 👨‍💻 Autor

 ##Lucas Moura
 
Projeto desenvolvido para **aprendizado em Java e lógica de jogos**, com foco em orientação a objetos e regras do xadrez.

---

♟️ **Divirta-se jogando!**

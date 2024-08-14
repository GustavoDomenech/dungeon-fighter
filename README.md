# DUNGEON-FIGHTER

## TO DO

* decidir o tema do jogo


## USEFUL

* [BUTTON SOUND EFFECTS](https://www.youtube.com/watch?v=q8ZLBOFQ2g0)

## GAME

* 1 jogador

* \/ é possivel
> explorar a masmorra
> vasculhar suas inumeras salas
> enfrentar monstros
> no fim derrotar um chefão

* herois possuem atributos
> ataque
> defesa
> saude

* herois possuem habilidades
> defesa 
> recuperação
> ataque

* 3 tipos de herói
> barbaro
> paladino
> guerreiro

1. Quando o jogo começa, o jogador escolhe o seu herói de acordo com um tipo e recebe:
    - X pontos para distribuir entre seus atributos. 
    - Os pontos de vida serão Y + saúde
2. Cada tipo de herói possui uma habilidade especial que pode ser usada uma vez a cada batalha:
    - Guerreiro: Postura Defensiva - Aumenta sua defesa em 50% durante duas rodadas;
    - Paladino: Recuperação - Recupera 50% dos seus pontos de vida totais;
    - Bárbaro: Golpe furioso - Desfere um ataque que causa 50% a mais de dano.
3. O objetivo do jogo é percorrer um calabouço
    
    ![CALABOUÇO](assets/board-example.png)

    e enfrentar o monstro no fim dele (que sempre está em uma posição da última linha do
    tabuleiro).


## JAVA

javax.swing.*;
java.awt.*;

1. Create Window class

```java
public class Window {
    // "main" da função window
    void initWindow() {
        JFrame frame = new Jframe("Dungeon Fighter");
    }        
}
```




































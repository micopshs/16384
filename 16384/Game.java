import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private Grid grid;
    private int score, highScore;
    private ArrayList<Perk> obtainedPerks;
    private boolean gameOver, hasWon;

    public Game() {
        grid = new Grid();
        score = 0;
        highScore = 0;
        obtainedPerks = new ArrayList<>();
        gameOver = false;
        hasWon = false;
    }

    public void play() {
        Scanner inputRead = new Scanner(System.in);
        char direction;
      
        System.out.println("Welcome to 16384");
        System.out.println("(u, d, l, r) for controls");
      
        do {
          grid.generateBlocks();
          display();
          System.out.print("Your move? ");

          direction = inputRead.nextLine().charAt(0);

          grid.shift(direction);
          grid.merge(direction);
          grid.shift(direction);

          calcScoreIncrease();
          checkIfGameOver();

        
        } while (!gameOver);

        if (hasWon) {
          System.out.println("Congrats! You won.");
          reward();
        }
    }

    public void reset() {
        grid = new Grid();
        score = 0;
        highScore = 0;
        obtainedPerks = new ArrayList<>();
        gameOver = false;
        hasWon = false;
    }

    private void checkIfGameOver() {
        boolean flagger = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
              if (grid.getTileAt(i, j).getBlock() != null &&
                 grid.getTileAt(i, j).getBlock().getValue() == 
                 grid.getTileAt(i, j+1).getBlock().getValue()) {
                    flagger = true;
                    break;
                 }
                    
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
              if (grid.getTileAt(j, i).getBlock() != null &&
                 grid.getTileAt(j, i).getBlock().getValue() == 
                 grid.getTileAt(j, i+1).getBlock().getValue()) {
                    flagger = true;
                    break;
                 }
                    
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
              if (grid.getTileAt(i, j).getBlock() != null &&
                grid.getTileAt(i, j).getBlock().getValue() == 16384) {
                flagger = true;
                hasWon = true;
                break;
              }
                
            }
        }

        gameOver = !flagger;
      
    }

    private void display() {
        System.out.println("\nScore: " + score + " | Highscore: " + highScore);
      
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
              if (grid.getTileAt(i, j).getBlock() == null)
                System.out.print("0 ");
              else 
                System.out.print(grid.getTileAt(i, j).getBlock().getValue()+ " ");
            }
            System.out.println("");
        }

      
    }

    private void usePerk(String name) {
      for (Perk p : obtainedPerks) {
        if (p.getName() == name) {
          p.perkSpecial(grid);
          obtainedPerks.remove(p);
          break;
          
        }
      }
    }

    private void calcScoreIncrease() {
      score += 2;
      if (score > highScore) highScore = score;
        
    }

    private void reward() {
        Random rand = new Random();
        switch(rand.nextInt(3)) {
          case 0: obtainedPerks.add(new DoublePerk()); break;
          case 1: obtainedPerks.add(new ReleasePerk()); break;
          case 2: obtainedPerks.add(new DeactivatePerk()); break;
        }
    
    }
    

  
}
import java.util.Random;
import java.util.ArrayList;

public class Grid {
    public Tile playGrid[][];

    public Grid() {
        playGrid = new Tile[6][6];
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                playGrid[x][y] = new RegularTile();
            }
        }
    }

    public Tile[][] getPlayGrid() {
        return playGrid;
    }

    public void setPlayGrid(Tile[][] grid) {
        playGrid = grid;
    }

    public Tile getTileAt(int x, int y) {
        return playGrid[x][y];
    }

    public void setTileAt(int x, int y, Tile t) {
        playGrid[x][y] = t;
    }

    public void lockTileAt(int x, int y) {
        (playGrid[x][y]).setUsable(false);
    }

    public void unlockTileAt(int x, int y) {
        (playGrid[x][y]).setUsable(true);
    }

    public void shift(char direction) {
        ArrayList<Block> blocks = new ArrayList<>();
        int zeroesCount = 0, currTileIndex = 0;

      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
          if (direction == 'r' || direction == 'l') {
            if (playGrid[i][j].getBlock() == null) zeroesCount++;
            else  blocks.add(playGrid[i][j].getBlock());
          } else if (direction == 'u' || direction == 'd') {
            if (playGrid[j][i].getBlock() == null) zeroesCount++;
            else  blocks.add(playGrid[j][i].getBlock());
          }
          
        }
  
        for (int k = 0; k < 6; k++) {
  
           
          if (direction == 'r' && k < zeroesCount) { 
            playGrid[i][k].setBlock(null);
            continue;
          } else if (direction == 'l' && k >= 6 - zeroesCount) {
            playGrid[i][k].setBlock(null);
            continue;
          } else if (direction == 'u' && k >= 6 - zeroesCount) {
            playGrid[k][i].setBlock(null);
            continue;
          } else if (direction == 'd' && k < zeroesCount) {
            playGrid[k][i].setBlock(null);
            continue;
          }
  
          if (direction == 'r' || direction == 'l')
            playGrid[i][k].setBlock(blocks.get(currTileIndex));
          else if (direction == 'u' || direction == 'd')
            playGrid[k][i].setBlock(blocks.get(currTileIndex));
          
          currTileIndex++;
        }
  
        blocks.clear();
        zeroesCount = 0;
        currTileIndex = 0;
      }
    }

    public void merge(char direction) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j += 2) {
              try {
                if (direction == 'l' || direction == 'r') {
                  if (playGrid[i][j].getBlock().getValue() != playGrid[i][j+1].getBlock().getValue()) 
                      continue;
                  if (direction == 'l') {
                    playGrid[i][j].getBlock().setValue(2*playGrid[i][j].getBlock().getValue());
                    playGrid[i][j+1].setBlock(null);
                  } else if (direction == 'r') {
                    playGrid[i][j].setBlock(null);
                    playGrid[i][j+1].getBlock().setValue(2*playGrid[i][j].getBlock().getValue());
                  }
                } else if (direction == 'u' || direction == 'd') {
                  if (playGrid[j][i].getBlock().getValue() != playGrid[j+1][i].getBlock().getValue()) 
                    continue;
                  if (direction == 'u') {
                    playGrid[j][i].getBlock().setValue(2*playGrid[j][i].getBlock().getValue());
                    playGrid[j+1][i].setBlock(null);
                  } else if (direction == 'd') {
                    playGrid[j][i].setBlock(null);
                    playGrid[j+1][i].getBlock().setValue(2*playGrid[j][i].getBlock().getValue());
                  }
                }
              } catch (NullPointerException e) {
                continue;
              }

             
      
          
      }
    }
    }

    public void generateBlocks() {
        int xCoord = 0, yCoord = 0;
        Random rand = new Random();

        for (int i = 1; i <= 2; i++) {
          while(true) {
          
            xCoord = rand.nextInt(6);
            yCoord = rand.nextInt(6);
  
            if (playGrid[xCoord][yCoord].getBlock() == null) {
              playGrid[xCoord][yCoord].setBlock(new Block(2));
              break;
            }
            
          }
        }

    }

    public void executeTileSpecial() {
        double temp = 0.0;
        Random rand = new Random();
        
        for (int i = 0; i < 6; i++) {
          for (int j = 0; j < 6; j++) {
            if (!(playGrid[i][j] instanceof RegularTile) 
              && playGrid[i][j].getBlock() != null) {
                temp = rand.nextDouble();
                if (playGrid[i][j].getChance() > temp) {
                  playGrid[i][j].tileSpecial();
                  if (playGrid[i][j] instanceof BombTile) {
                    detonateBomb(i, j);
                  }
                } 
                  
                  
            }
          }
        }
    }

    private void detonateBomb(int x, int y) {
        playGrid[x][y].setBlock(null);
        playGrid[x][y+1].setBlock(null);
        playGrid[x][y-1].setBlock(null);
        playGrid[x+1][y].setBlock(null);
        playGrid[x-1][y].setBlock(null);
    }
} 

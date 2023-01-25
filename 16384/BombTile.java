public class BombTile extends Tile {
  private boolean activated;
  
  public BombTile(double chance) {
      super(chance);
      activated = false;
  }

  public boolean isActivated() {
      return activated;
  }

  public void reset() {
      activated = false;
  }
  

  @Override
  public void tileSpecial() {
      activated = true;
  }
}
public class DeactivatePerk extends Perk {

  public DeactivatePerk(){
    super("deactivate");
  }

  @Override
  public void perkSpecial(Grid target){

    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        Tile t = target.getTileAt(x, y);
        if (t instanceof DividerTile || t instanceof BombTile)
            t.setChance(0.0);
      }
    }
    
  }
}
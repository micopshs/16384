public class ReleasePerk extends Perk {

  public ReleasePerk(){
    super("release");
  }

  @Override
  public void perkSpecial(Grid target){

    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        Tile t = target.getTileAt(x, y);
        t.setUsable(true);
      }
    }
    
  }
}
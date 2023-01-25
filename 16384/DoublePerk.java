public class DoublePerk extends Perk {

  public DoublePerk(){
    super("double");
  }

  @Override
  public void perkSpecial(Grid target){

    for(int x = 0; x < 6; x++){
      for(int y = 0; y < 6; y++){
        Tile t = target.getTileAt(x, y);
        if (t.getBlock() != null) {
            Block b = t.getBlock();
            b.setValue((b.getValue())*2);
        }
        
      }
    }
    
  }
}
public class DividerTile extends Tile {
    private int divider;
  
    public DividerTile(double chance, int divider) {
        super(chance);
        this.divider = divider;
    }

    @Override
    public void tileSpecial() {
        if (this.getBlock() != null) {
            this.getBlock().setValue(this.getBlock().getValue() * divider); 
        }
    }
}
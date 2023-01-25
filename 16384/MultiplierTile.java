public class MultiplierTile extends Tile {
    private int multiplier;
  
    public MultiplierTile(double chance, int multiplier) {
        super(chance);
        this.multiplier = multiplier;
    }

    @Override
    public void tileSpecial() {
        if (this.getBlock() != null) {
            this.getBlock().setValue(this.getBlock().getValue() * multiplier); 
        }
    }
}
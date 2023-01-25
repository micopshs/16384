public abstract class Tile {
    private Block numBlock;
    private boolean usable;
    protected double specialChance;

    public Tile() {
        this.numBlock = null;
        this.usable = true;
        this.specialChance = 0.0;
    }

    public Tile(double chance) {
        this.numBlock = null;
        this.usable = true;
        this.specialChance = chance;
    }

    public Block getBlock() {
        return this.numBlock;
    }

    public void setBlock(Block block) {
        this.numBlock = block;
    }

    public boolean IsUsable() {
        return this.usable;
    }

    public void setUsable(boolean usability) {
        this.usable = usability;
    }

    public double getChance() {
        return this.specialChance;
    }

    public void setChance(double chance) {
        this.specialChance = chance;
    }

    public abstract void tileSpecial();
}
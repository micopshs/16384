public abstract class Perk {

  protected String name;
  
  public Perk (String n) {
    name = n;
  }

  public String getName(){
    return name;
  }

  public void setName(String n) {
    name = n;
  }

  public abstract void perkSpecial(Grid target);
}
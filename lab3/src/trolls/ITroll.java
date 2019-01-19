package trolls;

import goats.IGoat;

public interface ITroll {

    public void interact(IGoat goat);

    public void adjustPower(int pwrAdj);

    public void finished(IGoat goat);

    public boolean isActive();

}

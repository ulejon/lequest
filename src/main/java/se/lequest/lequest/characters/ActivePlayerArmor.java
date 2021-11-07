package se.lequest.lequest.characters;

import se.lequest.lequest.items.Armor;
import se.lequest.lequest.items.Boots;
import se.lequest.lequest.items.Chestplate;
import se.lequest.lequest.items.Gloves;
import se.lequest.lequest.items.Helmet;
import se.lequest.lequest.items.Shield;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This Class takes care of the Players Active armor..
 * That is The armor the player is currently using.. (not in The itemscollection ,backpack)
 * Examples of Armor is Helmet, Gloves , Boots, Shield and so on..
 * The Player can oly use one Armor of a sertain sort..
 *
 * @author hagen, leino
 */
public class ActivePlayerArmor implements java.io.Serializable {

    private final List<Armor> armors;
    private final Random rand;
    private boolean helmetPresent = false;
    private boolean bodyArmorPresent = false;
    private boolean bootsPresent = false;
    private boolean shieldPresent = false;
    private boolean glovesPresent = false;

    /**
     * Creates a ActivePlayer armor object..
     * that is empty ..
     */
    public ActivePlayerArmor() {
        this.rand = new Random(System.currentTimeMillis());
        this.armors = new ArrayList<>();
    }

    /**
     * Add a Active Armor to the Player returns the oldone if already exsits of same sort..
     *
     * @param armor
     * @return
     */
    public Armor addArmor(Armor armor) {
        Armor oldone = null;
        if (armor instanceof Helmet) {
            helmetPresent = true;
        } else if (armor instanceof Chestplate) {
            bodyArmorPresent = true;
        } else if (armor instanceof Boots) {
            bootsPresent = true;
        } else if (armor instanceof Shield) {
            shieldPresent = true;
        } else if (armor instanceof Gloves) {
            glovesPresent = true;
        }
        for (Armor temp : armors) {
            if (temp.getClass().getSuperclass().getName().equals(armor.getClass().getSuperclass().getName())) {
                //one of the same sort allready exsists..
                oldone = temp;
                armors.remove(oldone);
                break;
            }
        }
        armors.add(armor);
        return oldone;

    }


    /**
     * Returns the Maximum protection value of the Players active Armor
     *
     * @return
     */
    public int getTotalMaximumProtection() {
        int tot = 0;
        for (Armor tmp : this.armors) {
            tot += tmp.getMaxProtectionValue();
        }
        return tot;
    }

    /**
     * Returns the Minimum protection value of the Players active Armor
     *
     * @return
     */
    public int getTotalMinimumProtection() {
        int tot = 0;
        for (Armor tmp : this.armors) {
            tot += tmp.getMinProtectionValue();
        }
        return tot;
    }

    /**
     * Returns a actual protection value of the Players active Armor, 0 if none added
     *
     * @return
     */
    public int getRandomProtectionOfTotalArmor() {
        if (this.getTotalMaximumProtection() == 0) {
            return 0;
        }
        return getTotalMinimumProtection() + rand.nextInt(getTotalMaximumProtection() - getTotalMinimumProtection());
    }

    /**
     * Returns a random value of armor if a helmet is present.. otherwise 0
     *
     * @return
     */
    public int getRandomProtectionOfHelmetArmor() {
        for (Armor tmp : this.armors) {
            if (tmp instanceof Helmet) {
                return tmp.getMinProtectionValue() + rand.nextInt(tmp.getMaxProtectionValue() - tmp.getMinProtectionValue());
            }
        }
        return 0;
    }

    /**
     * Returns a random value of armor if a BodyArmor is present.. otherwise 0
     *
     * @return
     */
    public int getRandomProtectionOfBodyArmor() {
        for (Armor tmp : this.armors) {
            if (tmp instanceof Chestplate) {
                return tmp.getMinProtectionValue() + rand.nextInt(tmp.getMaxProtectionValue() - tmp.getMinProtectionValue());
            }
        }
        return 0;
    }

    /**
     * Returns a random value of armor if boots is present.. otherwise 0
     *
     * @return
     */
    public int getRandomProtectionOfBootsArmor() {
        for (Armor tmp : this.armors) {
            if (tmp instanceof Boots) {
                return tmp.getMinProtectionValue() + rand.nextInt(tmp.getMaxProtectionValue() - tmp.getMinProtectionValue());
            }
        }
        return 0;
    }

    /**
     * Returns a random value of armor if a Shield is present.. otherwise 0
     *
     * @return
     */
    public int getRandomProtectionOfShieldArmor() {
        for (Armor tmp : this.armors) {
            if (tmp instanceof Shield) {
                return tmp.getMinProtectionValue() + rand.nextInt(tmp.getMaxProtectionValue() - tmp.getMinProtectionValue());
            }
        }
        return 0;
    }

    /**
     * Returns a random value of armor if gloves is present.. otherwise 0
     *
     * @return
     */
    public int getRandomProtectionOfGlovesArmor() {
        for (Armor tmp : this.armors) {
            if (tmp instanceof Gloves) {
                return tmp.getMinProtectionValue() + rand.nextInt(tmp.getMaxProtectionValue() - tmp.getMinProtectionValue());
            }
        }
        return 0;
    }

    /**
     * Removes a Armor from the Collection of active armor..
     *
     * @param armor
     */
    public void removeActiveArmor(Armor armor) {
        this.armors.remove(armor);

    }

    /**
     * Returns the active Armor list.. Usefull for Eric & Jonas if they want to have a panel with active armor..
     *
     * @return
     */
    public List<Armor> getArmorList() {
        return armors;
    }

    /**
     * Returns true if a helmet is present
     */
    public boolean isHelmetPresent() {
        return helmetPresent;
    }

    /**
     * Returns true if a Body Armor is present
     */
    public boolean isBodyArmorPresent() {
        return bodyArmorPresent;
    }

    /**
     * Returns true if boots is present
     */
    public boolean isBootsPresent() {
        return bootsPresent;
    }

    /**
     * Returns true if a Shield is present
     */
    public boolean isShieldPresent() {
        return shieldPresent;
    }

    /**
     * Returns true if gloves is present
     */
    public boolean isGlovesPresent() {
        return glovesPresent;
    }
}

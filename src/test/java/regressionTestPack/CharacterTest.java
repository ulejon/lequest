package regressionTestPack;

import se.lequest.lequest.characters.Player;
import se.lequest.lequest.items.Fists;
import se.lequest.lequest.items.Money;
import junit.framework.TestCase;

import static org.fest.assertions.Assertions.assertThat;

public class CharacterTest extends TestCase {
    private Player player;

    protected void setUp() throws Exception {
        super.setUp();
        player = new Player("JanBanan", 100, 100, new se.lequest.lequest.maps.CatacombMap());
    }

    public void testName() {
        assertThat(player.getName()).isEqualTo("JanBanan");
        player.setName("Per");
        assertThat(player.getName()).isEqualTo("Per");
    }

    public void testHealth() {
        assertThat(player.isAlive()).isTrue();
        assertThat(player.getHealth()).isEqualTo(100);
        player.setHealth(50);
        assertThat(player.getHealth()).isEqualTo(50);
        player.decreaseHealth(3);
        assertThat(player.getHealth()).isEqualTo(47);
        player.addHealth(100);
        assertThat(player.getHealth()).isEqualTo(100);
        player.setHealth(200);
        assertThat(player.getHealth()).isEqualTo(100);
        player.setIsAlive(false);
        assertThat(player.getHealth()).isEqualTo(0);
        assertThat(player.isAlive()).isFalse();
    }

    public void testItems() {
        //TODO test if can change current weapon..
        assertThat(player.getCurrentWeapon()).isInstanceOf(Fists.class);
        /*assertEquals(true,player.getNumberOfItems() == 1);*/ //remove comment when dagger not in constructor
        assertThat(player.getMaxnrofItems()).isEqualTo(10);
        player.setMaxNrOfItems(20);
        assertThat(player.getMaxnrofItems()).isEqualTo(20);
        player.addItem(new Fists());
		/*assertEquals(true,player.getNumberOfItems() == 2);*///remove comment when dagger not in constructor

    }

    public void testMoney() {
        int coinsBefore = player.getCoins();
        player.addItem(new Money(110));
        assertThat(player.getCoins()).isEqualTo(coinsBefore + 110);
    }

}

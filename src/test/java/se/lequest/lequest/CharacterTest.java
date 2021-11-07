package se.lequest.lequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lequest.lequest.characters.Player;
import se.lequest.lequest.items.Fists;
import se.lequest.lequest.items.Money;

import static org.fest.assertions.Assertions.assertThat;

class CharacterTest {
    private Player player;

    @BeforeEach
    void setUp() throws Exception {
        player = new Player("JanBanan", 100, 100, new se.lequest.lequest.maps.CatacombMap());
    }

    @Test
    void name() {
        assertThat(player.getName()).isEqualTo("JanBanan");
        player.setName("Per");
        assertThat(player.getName()).isEqualTo("Per");
    }

    @Test
    void health() {
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

    @Test
    void items() {
        //TODO test if can change current weapon..
        assertThat(player.getCurrentWeapon()).isInstanceOf(Fists.class);
        /*assertEquals(true,player.getNumberOfItems() == 1);*/ //remove comment when dagger not in constructor
        assertThat(player.getMaxnrofItems()).isEqualTo(10);
        player.setMaxNrOfItems(20);
        assertThat(player.getMaxnrofItems()).isEqualTo(20);
        player.addItem(new Fists());
        /*assertEquals(true,player.getNumberOfItems() == 2);*///remove comment when dagger not in constructor

    }

    @Test
    void money() {
        int coinsBefore = player.getCoins();
        player.addItem(new Money(110));
        assertThat(player.getCoins()).isEqualTo(coinsBefore + 110);
    }

}

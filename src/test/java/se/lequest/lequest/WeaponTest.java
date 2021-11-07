package se.lequest.lequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lequest.lequest.items.Fists;

import static org.fest.assertions.Assertions.assertThat;

class WeaponTest {
    private Fists fists;

    @BeforeEach
    void setUp() throws Exception {
        fists = new Fists();
    }

    @Test
    void weapon() {
        assertThat(Fists.MIN_DAMAGE_VALUE).isEqualTo(fists.getMinDamageValue());
        assertThat(Fists.MAX_DAMAGE_VALUE).isEqualTo(fists.getMaxDamageValue());

        for (int i = 0; i < 20; i++) {
            assertThat(fists.attack()).isLessThanOrEqualTo(Fists.MAX_DAMAGE_VALUE);
            assertThat(fists.attack()).isGreaterThanOrEqualTo(Fists.MIN_DAMAGE_VALUE);
        }

        // Test if can get Max Damage value sometime.,,,
        boolean gotvalue = false;
        for (int j = 0; j < 100000; j++) {
            if (fists.attack() == Fists.MAX_DAMAGE_VALUE) {
                gotvalue = true;
            }
        }
        assertThat(gotvalue).isTrue();

        // Test if can get Min Damage value sometime.,,,
        gotvalue = false;
        for (int j = 0; j < 100000; j++) {
            if (fists.attack() == Fists.MIN_DAMAGE_VALUE) {
                gotvalue = true;
            }
        }
        assertThat(gotvalue).isTrue();


    }

    @Test
    void fists() {
        assertThat(fists.getSellValue()).isEqualTo(0);
        assertThat(fists.getPurchaseValue()).isEqualTo(0);
        assertThat(fists.isDropable()).isFalse();
        assertThat(fists.isSellable()).isFalse();

    }
}

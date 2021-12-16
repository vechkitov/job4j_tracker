package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {

    @Test
    public void whenCollectProfilesThenGetAddresses() {
        Address msk = new Address("Moscow", "Tverskaya", 1, 1);
        Address stp = new Address("St.Petersburg", "Nevsky", 2, 3);
        Address vl = new Address("Vladivostok", "Lenina", 5, 7);
        List<Profile> profiles = List.of(
                new Profile(msk),
                new Profile(stp),
                new Profile(vl)
        );
        List<Address> expected = List.of(msk, stp, vl);
        List<Address> actual = new Profiles().collect(profiles);
        assertThat(actual, is(expected));
    }
}

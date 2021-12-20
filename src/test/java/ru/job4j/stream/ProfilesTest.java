package ru.job4j.stream;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {

    @Test
    public void whenCollectProfilesThenGetUniqueAddresses() {
        Address vl = new Address("Vladivostok", "Lenina", 5, 7);
        Address msk = new Address("Moscow", "Tverskaya", 1, 1);
        Address stp = new Address("St.Petersburg", "Nevsky", 2, 3);
        Address stpDupl = new Address("St.Petersburg", "Nevsky", 2, 3);
        List<Profile> profiles = List.of(
                new Profile(vl),
                new Profile(msk),
                new Profile(stp),
                new Profile(stpDupl)
        );
        List<Address> actual = new Profiles().collect(profiles);
        assertThat(actual.size(), is(3));
        List<Address> expected = List.of(msk, stp, vl);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCollectProfilesThenGetSortedByCityAddresses() {
        Address vl = new Address("Vladivostok", "Lenina", 5, 7);
        Address msk1 = new Address("Moscow", "Tverskaya", 1, 1);
        Address msk2 = new Address("Moscow", "Shvernika", 8, 2);
        Address stp = new Address("St.Petersburg", "Nevsky", 2, 3);
        List<Profile> profiles = List.of(
                new Profile(vl),
                new Profile(msk1),
                new Profile(msk2),
                new Profile(stp)
        );
        List<String> expected = List.of(
                "Moscow",
                "Moscow",
                "St.Petersburg",
                "Vladivostok");
        List<String> actual = new Profiles().collect(profiles)
                .stream()
                .map(adr -> adr.getCity())
                .collect(Collectors.toList());
        assertThat(actual, is(expected));
    }
}

package org.sooo.hash;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.Sink;

public class HashTest {

	Person person;
	Funnel<Person> personFunnel;

	@Before
	public void setUp() {
		person = new Person(1, "John", "Doe", 1970);
		personFunnel = new Funnel<Person>() {
			@Override
			public void funnel(Person from, Sink into) {
				into.putInt(person.id).putString(person.firstName)
						.putString(person.lastName).putInt(person.birthYear);
			}
		};
	}

	@Test
	public void createHashcode() {
		long id = 1L;
		String name = "name";
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.newHasher().putLong(id).putString(name)
				.putObject(person, personFunnel).hash();
		assertThat(hc.toString(), is("b3c4149e2ad4255ed9e9581cb5f71c54"));
	}

	@Test
	public void bloomFilter() {
		BloomFilter<Person> friends = BloomFilter
				.create(personFunnel, 10, 0.01);
		List<Person> friendsList = Lists.newArrayList();
		for (int i = 0; i < 10; i++)
			friendsList.add(new Person(i, "John" + i, "Doe" + i, 2000 + i));


		for (Person friend : friendsList)
			friends.put(friend);

		Person oneOfFriends = new Person(0, "John0", "Doe0", 2000);
		assertThat(friends.mightContain(oneOfFriends), is(true));
		// TODO find the case mightContain() return false.
		//Person notAFriend = new Person(1, "a", "a", 1);
		//assertThat(friends.mightContain(notAFriend), is(false));
	}
}

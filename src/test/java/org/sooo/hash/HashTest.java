package org.sooo.hash;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

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
}

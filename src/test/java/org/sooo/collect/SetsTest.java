package org.sooo.collect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.sooo.Animal;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class SetsTest {

	private final Set<Animal> set1 = Sets.newHashSet(Animal.DOG, Animal.CAT,
			Animal.MONKEY, Animal.PIG);
	private final Set<Animal> set2 = Sets.newHashSet(Animal.DOG, Animal.CAT,
			Animal.CHICKEN);

	@Test
	public void cartesianProduct() {
		@SuppressWarnings("unchecked")
		Set<List<Animal>> cartesianProducts = Sets.cartesianProduct(set1, set2);
		assertThat(cartesianProducts.size(), is(4 * 3));
	}

	@Test
	public void complementOf() {
		EnumSet<Animal> complements = Sets.complementOf(set1);
		assertThat(complements, is(Sets.newEnumSet(Arrays.asList(
				Animal.CHICKEN, Animal.COW, Animal.SHEEP, Animal.GOAT),
				Animal.class)));
	}

	@Test
	public void difference() {
		SetView<Animal> set1MinusSet2 = Sets.difference(set1, set2);
		assertTrue(set1MinusSet2.contains(Animal.MONKEY));
		assertTrue(set1MinusSet2.contains(Animal.PIG));
		assertFalse(set1MinusSet2.contains(Animal.DOG));
		assertFalse(set1MinusSet2.contains(Animal.CAT));
		assertFalse(set1MinusSet2.contains(Animal.CHICKEN));

		SetView<Animal> set2MinusSet1 = Sets.difference(set2, set1);
		assertTrue(set2MinusSet1.contains(Animal.CHICKEN));
		assertFalse(set2MinusSet1.contains(Animal.MONKEY));
		assertFalse(set2MinusSet1.contains(Animal.PIG));
		assertFalse(set2MinusSet1.contains(Animal.DOG));
		assertFalse(set2MinusSet1.contains(Animal.CAT));
	}

	@Test
	public void filter() {
		Set<Animal> animalsStartWithC = Sets.filter(set1,
				new Predicate<Animal>() {
					@Override
					public boolean apply(Animal input) {
						return input.toString().startsWith("C");
					}
				});
		assertTrue(animalsStartWithC.contains(Animal.CAT));
		assertFalse(animalsStartWithC.contains(Animal.DOG));
		assertFalse(animalsStartWithC.contains(Animal.MONKEY));
		assertFalse(animalsStartWithC.contains(Animal.PIG));
	}

	@Test
	public void intersection() {
		Set<Animal> intersection = Sets.intersection(set1, set2);
		assertTrue(Objects.equal(intersection,
				Sets.newHashSet(Animal.DOG, Animal.CAT)));
	}

	@Test
	public void symmetricDifference() {
		SetView<Animal> symmetricDifference = Sets.symmetricDifference(set1,
				set2);
		System.out.println(symmetricDifference);
		assertTrue(Objects.equal(symmetricDifference,
				Sets.newHashSet(Animal.MONKEY, Animal.PIG, Animal.CHICKEN)));
		assertTrue(Objects.equal(symmetricDifference,
				Sets.symmetricDifference(set2, set1)));
	}

	@Test
	public void union() {
		Set<Animal> union = Sets.union(set1, set2);
		assertTrue(Objects.equal(union, Sets.newHashSet(Animal.DOG, Animal.CAT,
				Animal.PIG, Animal.MONKEY, Animal.CHICKEN)));
	}
}

package org.sooo.base;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.base.Defaults;

public class DefaultsTest {

	@Test
	public void defaultValues() {
		assertThat(Defaults.defaultValue(int.class), is(0));
		assertThat(Defaults.defaultValue(long.class), is(0L));
		assertThat(Defaults.defaultValue(float.class), is(0.0F));
		assertThat(Defaults.defaultValue(double.class), is(0.0));
		assertThat(Defaults.defaultValue(boolean.class), is(false));
		assertThat(Defaults.defaultValue(char.class), is('\0'));
		assertThat(Defaults.defaultValue(String.class), is(nullValue()));
	}
}

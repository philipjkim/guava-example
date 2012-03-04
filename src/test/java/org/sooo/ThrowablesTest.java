package org.sooo;

import org.junit.Test;

import com.google.common.base.Throwables;

public class ThrowablesTest {

	@Test(expected = RuntimeException.class)
	public void handleCheckedExceptionWithoutThrowables() {
		try {
			generateCheckedException();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void generateCheckedException() throws Exception {
		try {
			Integer.parseInt("aaa");
		} catch (NumberFormatException e) {
			throw new Exception(e);
		}
	}

	@Test(expected = RuntimeException.class)
	public void handleCheckedExceptionWithThrowables() {
		try {
			generateCheckedException();
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}

	@Test
	public void getRootCause() {
		try {
			generateCheckedException();
		} catch (Exception e) {
			System.out.println(Throwables.getRootCause(e));
		}
	}

	@Test
	public void getCausalChain() {
		try {
			generateCheckedException();
		} catch (Exception e) {
			System.out.println(Throwables.getCausalChain(e));
		}
	}

	@Test
	public void getStackTraceAsString() {
		try {
			generateCheckedException();
		} catch (Exception e) {
			System.out.println(Throwables.getStackTraceAsString(e));
		}
	}
}

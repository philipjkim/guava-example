package org.sooo.collect;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.sooo.collect.Device.Grade;

import com.google.common.collect.Lists;

/**
 * Reference:
 * <code><a href="http://bit.ly/r3IHDN">http://bit.ly/r3IHDN</a></code>
 * 
 * @author sooo
 * 
 */
public class ComparisonChainTest {

	/**
	 * For ComparisonChain usage, refer to {@link Device#compareTo(Device)}.
	 */
	@Test
	public void compareDevices() {
		Device dev1 = Device.newBuilder().grade(Grade.D).name("Lighter")
				.build();
		Device dev2 = Device.newBuilder().grade(Grade.C).name("Clock").build();
		Device dev3 = Device.newBuilder().grade(Grade.A).name("Phone").build();
		Device dev4 = Device.newBuilder().grade(Grade.B).name("Calculator")
				.build();
		Device dev5 = Device.newBuilder().grade(Grade.A).name("PC").build();
		
		List<Device> devices = Lists.newArrayList(dev1, dev2, dev3, dev4, dev5);
		System.out.println(devices);
		
		Collections.sort(devices);
		System.out.println(devices);
	}
}

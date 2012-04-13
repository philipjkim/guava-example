package org.sooo.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Device implements Comparable<Device> {

	Grade grade;
	String name;

	private Device(Builder builder) {
		grade = builder.grade;
		name = builder.name;
	}

	public static Builder newBuilder() {
		return Builder.create();
	}

	public static final class Builder {
		private Grade grade;
		private String name;

		private Builder() {
		}

		private static Builder create() {
			return new Builder();
		}

		public Builder grade(Grade g) {
			grade = g;
			return this;
		}

		public Builder name(String n) {
			name = n;
			return this;
		}

		public Device build() {
			Preconditions.checkNotNull(grade);
			Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
			return new Device(this);
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("grade", grade)
				.add("name", name).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(grade, name);
	}

	@Override
	public int compareTo(Device that) {
		return ComparisonChain.start()
				.compare(this.grade, that.grade, Ordering.natural())
				.compare(this.name, that.name).result();
	}

	enum Grade {
		A, B, C, D
	}
}

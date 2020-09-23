package com.github.nathandelane.experiments.sets;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SetsTests {

	@Test
	public void testIntersection() {
		final Set<Long> s1 = new HashSet<>();
		s1.addAll(Arrays.asList(456L, 123L));
		
		final Set<Long> s2 = new HashSet<>();
		s2.addAll(Arrays.asList(123L, 456L));
		
		final boolean result = s1.equals(s2);
		
		System.out.format("Result: s1=%s s2=%s", s1, s2);
		
		assertTrue(result);
	}
	
}

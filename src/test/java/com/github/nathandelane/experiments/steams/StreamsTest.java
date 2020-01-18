package com.github.nathandelane.experiments.steams;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamsTest {
	
	@Test
	public void testNull() {
		final List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		
		final List<Boolean> bools = list.stream()
    .map(w -> {
      Boolean bool = getValue(w);
      if (bool == null) {
        return null;
      }
      return bool;
    })
    .filter(w -> w != null)
    .distinct()
    .collect(Collectors.toList());
		
		assertFalse(bools.isEmpty());
		assertTrue(bools.size() == 1);
	}
	
	final Boolean getValue(final Integer i) {
		if (i >= 20) {
			return true;
		}
		return null;
	}

}

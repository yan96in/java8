package collector;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class EncounterOrderTest {

	@Test
	public void listToStream() {
		List<Integer> numbers = asList(1, 2, 3, 4);

		List<Integer> sameOrder = numbers.stream().collect(toList());
		// 顺序测试永远通过
		assertEquals(numbers, sameOrder);
	}

	@Ignore
	@Test
	public void hashSetToStream() {
		Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));

		List<Integer> sameOrder = numbers.stream().collect(toList());

		// 顺序测试不能保证每次通过
		assertEquals(asList(4, 3, 2, 1), sameOrder);
	}

	@Test
	public void hashSetToStreamSorted() {
		Set<Integer> numbers = new HashSet<>(asList(4, 2, 1, 3));
		//通过sorted方法可以产生顺序
		List<Integer> sameOrder = numbers.stream()
				.sorted()
				.collect(toList());
		// 生成出现顺序
		assertEquals(asList(1, 2, 3, 4), sameOrder);
	}

	@Test
	public void toStreamMapped() {
		List<Integer> numbers = asList(1, 2, 3, 4);
		//有些操作会产生顺序，比如键值对映射，映射后的值是有序的，这种顺序会保留下来。
		List<Integer> stillOrdered = numbers.stream().map(x -> x + 1).collect(toList());
		assertEquals(asList(2, 3, 4, 5), stillOrdered);//顺序得到保留

		Set<Integer> unordered = new HashSet<>(numbers);
		
		List<Integer> stillUnordered = unordered.
				/* 大多数操作在有序流上效率高，比如filter，map，reduce等，
				 * 但一些操作在有序的流上开销很大，这时调用unordered方法消除顺序就能解决该问题。*/
				stream().map(x -> x + 1).collect(toList());

	}

}

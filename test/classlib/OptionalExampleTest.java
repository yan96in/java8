package classlib;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalExampleTest {

	@Test
	public void examples() {
		// 使用工厂方法of创建某个值的Optional对象
		Optional<String> a = Optional.of("a");
		assertEquals("a", a.get());
		// 创建一个空的Optional对象，并检查其是否有值
		Optional emptyOptional = Optional.empty();
		Optional alsoEmpty = Optional.ofNullable(null);

		assertFalse(emptyOptional.isPresent());
		assertTrue(a.isPresent());

		assertEquals("b", emptyOptional.orElse("b"));
		assertEquals("c", emptyOptional.orElseGet(() -> "c"));
	}

}

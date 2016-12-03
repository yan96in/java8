package newclasslib;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalExampleTest {

	@Test
	public void examples() {
		// 使用工厂方法of创建某个值的Optional对象
		Optional<String> a = Optional.of("a");
		
		// 创建一个空的Optional对象
		Optional emptyOptional = Optional.empty();
		Optional alsoEmpty = Optional.ofNullable(null);

		assertFalse(emptyOptional.isPresent());
		assertTrue(a.isPresent());//调用get方法前先调用isPresent方法检查对象是否有值。
		
		assertEquals("a", a.get());
		assertEquals("b", emptyOptional.orElse("b"));//orEsle方法会在Optional对象为空时提供一个备选值。
		assertEquals("c", emptyOptional.orElseGet(() -> "c"));		
	}
}

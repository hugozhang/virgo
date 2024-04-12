package me.about.widget.antlr.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import me.about.widget.BusinessRule;
import me.about.widget.DefaultBusinessRule;
import me.about.widget.MultiBusinessRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestMulti {

	private final String[] values;
	private final String name;
	private final Object expected;

	public TestMulti(String[] values, String name, Object expected) {
		this.values = values;
		this.name = name;
		this.expected = expected;
	}
	
	@Test
	public void testSimple() {
		MultiBusinessRule rule = new MultiBusinessRule(Arrays.stream(values).map(DefaultBusinessRule::new).toArray(BusinessRule[]::new));
		List<Map<String, Object>> result = rule.execute(null);
		System.out.println(result);
//		if (name == null)
//			Assert.assertEquals(expected, result.getValue());
//		else
//			Assert.assertEquals(expected, result.getValue(name));
	}
	
	@Parameters
	public static List<Object[]> data() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { new String[] { "IF 1 + 1 = 2 THEN SET result = 1", "IF 2 + 2 = 4 THEN SET result2 = 2" }, "result", 1L });
//		list.add(new Object[] { new String[] { "IF 1 + 1 = 2 THEN SET result = 3", "IF 2 + 2 = 4 THEN SET result2 = 3" }, "result2", 2L });

		return list;
	}
}

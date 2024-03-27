package org.joo.virgo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joo.virgo.BusinessRule;
import org.joo.virgo.DefaultBusinessRule;
import org.joo.virgo.RuleContext;
import org.joo.virgo.model.ExecutionResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//@RunWith(Parameterized.class)
public class TestSimple {

	private String value;
	private String name;
	private Object expected;

	public TestSimple() {
	}
//	public TestSimple(String value, String name, Object expected) {
//		this.value = value;
//		this.name = name;
//		this.expected = expected;
//	}
	
//	@Test
//	public void testSimple() {
//		BusinessRule rule = new DefaultBusinessRule(value);
//		ExecutionResult result = rule.execute(null).orElseThrow(() -> new NullPointerException("result is null"));
//		if (name == null)
//			Assert.assertEquals(value + ":", expected, result.getValue());
//		else
//			Assert.assertEquals(value + ":", expected, result.getValue(name));
//	}


	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User("张三", 2, new Date(), new Date()));
		users.add(new User("李四", 1, new Date(), new Date()));
		users.add(new User("王五", 0, new Date(), new Date()));
		return users;
	}

	private List<String> executeRule (User user) {

		List<String> result = new ArrayList<String>();

		RuleContext context = new RuleContext(user);

		BusinessRule rule1 = new DefaultBusinessRule("if age > 1  then set result = '年龄大于1'",context);
		ExecutionResult result1 = rule1.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
		Object resultValue1 = result1.getValue("result");
		if(resultValue1 != null) {
			result.add(user.getName() + "," + resultValue1);
		}

		BusinessRule rule2 = new DefaultBusinessRule("if age == 1  then set result = '年龄等于1'",context);
		ExecutionResult result2 = rule2.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
		Object resultValue2 = result2.getValue("result");
		if(resultValue2 != null) {
			result.add(user.getName() + "," + resultValue2);
		}

		BusinessRule rule3 = new DefaultBusinessRule("if age < 1  then set result = '年龄小于1'",context);
		ExecutionResult result3 = rule3.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
		Object resultValue3 = result3.getValue("result");
		if(resultValue3 != null) {
			result.add(user.getName() + "," + resultValue3);
		}

		return result;
	}

	@Test
	public void  testSimple2() {
		List<String> result = new ArrayList<String>();
		List<User> users = getUsers();
		for (User user : users) {
			result.addAll(executeRule(user));
		}
		System.out.println(result);
	}
	
//	@Parameters
	public static List<Object[]> data() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "IF 1 + 1 == 2 THEN { IF sqrt(4) == 2 THEN SET result = 1 ELSE SET result = 2 } ELSE SET result = 3", null, 1L });
		list.add(new Object[] { "IF 1 + 1 == 2 THEN { IF sqrt(4) == 3 THEN SET result = 1 ELSE SET result = 2 } ELSE SET result = 3", null, 2L });
		list.add(new Object[] { "IF 1 + 1 == 3 THEN { IF sqrt(4) == 2 THEN SET result = 1 ELSE SET result = 2 } ELSE SET result = 3", null, 3L });
		list.add(new Object[] { "IF 1 + 1 == 2 THEN IF sqrt(4) == 2 THEN SET result = 1", null, 1L });
		list.add(new Object[] { "IF 1 + 1 == 2 THEN SET result = 1; SET result2 = 2", "result2", 2L });
		list.add(new Object[] { "IF 1 + 1 == 2 THEN SET result = 1", null, 1L });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1", null, null });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1", null, null });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1 ELSE SET result = 2", null, 2L });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1 ELSE SET result = 2; SET result2 = 3", "result", 2L });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1 ELSE SET result = 2; SET result2 = 3", "result2", 3L });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1 ELSE IF 1 + 1 == 2 THEN SET result = 2; SET result2 = 3", "result", 2L });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1 ELSE IF 1 + 1 == 2 THEN SET result = 2; SET result2 = 3", "result2", 3L });
		list.add(new Object[] { "IF 1 + 1 < 2 THEN SET result = 1 ELSE IF 1 + 1 > 2 THEN SET result = 2; SET result2 = 3", null, null });
		list.add(new Object[] { "IF (1 + 1 < 2) THEN SET result = 1 ELSE IF 1 + 1 > 2 THEN SET result = 2 ELSE SET result2 = sqrt(9)", null, 3.0 });
        list.add(new Object[] { "SET result = true", null, true });
        list.add(new Object[] { "IF not list contains 1 THEN SET result = 1", null, 1L });
        list.add(new Object[] { "IF not list in 1 THEN SET result = 1", null, 1L });
        list.add(new Object[] { "IF 1 + 1 == 2 AND 2 + 2 == 4 THEN SET result = 1", null, 1L });
        list.add(new Object[] { "IF sku in {'1', '2', '3'} THEN  SET result = 1", null, null });
        list.add(new Object[] { "IF not sku in {'1', '2', '3'} THEN  SET result = 1", null, 1L });

		return list;
	}
}

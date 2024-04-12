package me.about.widget.antlr.test;

import java.util.*;

import me.about.widget.BusinessRule;
import me.about.widget.DefaultBusinessRule;
import me.about.widget.RuleContext;
import me.about.widget.model.ExecutionResult;
import org.junit.Test;

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


	public User getUser() {

		String key = "key1";

		Job job = new Job("工作1", 20000);

		Job job2 = new Job("工作2", 10000);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value2");

        return new User("张三", 2, new Date(), new Date(), Arrays.asList(job, job2),key,map);
	}

	private List<String> executeRule (User user) {

		List<String> result = new ArrayList<String>();

		Object resultValue0 = getResultValue0(user);
		if(resultValue0 != null) {
			if(resultValue0 instanceof Collection) {
				result.addAll((Collection) resultValue0);
			} else {
				result.add(resultValue0.toString());
			}
		}


//
//		BusinessRule rule1 = new DefaultBusinessRule("if age > 1  then set result = '年龄大于1'");
//		ExecutionResult result1 = rule1.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
//		Object resultValue1 = result1.getValue("result");
//		if(resultValue1 != null) {
//			result.add(user.getName() + "," + resultValue1);
//		}
//
//		BusinessRule rule2 = new DefaultBusinessRule("if age == 1  then set result = '年龄等于1'");
//		ExecutionResult result2 = rule2.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
//		Object resultValue2 = result2.getValue("result");
//		if(resultValue2 != null) {
//			result.add(user.getName() + "," + resultValue2);
//		}
//
//		BusinessRule rule3 = new DefaultBusinessRule("if age < 1  then set result = '年龄小于1'");
//		ExecutionResult result3 = rule3.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
//		Object resultValue3 = result3.getValue("result");
//		if(resultValue3 != null) {
//			result.add(user.getName() + "," + resultValue3);
//		}

		return result;
	}

	private static Object getResultValue0(User user) {
		RuleContext context = new RuleContext(user);
//		context.setTempVariable("$jobs", user.getJobs());


//		BusinessRule rule0 = new DefaultBusinessRule("if (exist for $job in jobs if $job.salary > 10000) then set result = join($jobs,'name') + ',薪水大于10000'");

//		BusinessRule rule0 = new DefaultBusinessRule("$a = [] ;$b = [];  for $job in jobs if $job.salary in [10000,20000] then $a append $job.name + '，薪资满足';print $b");


//		BusinessRule rule0 = new DefaultBusinessRule("$b = (exist for $job in jobs if $job.salary in [20000,40000]) if $b is not empty then $a = '123'");

//		BusinessRule rule0 = new DefaultBusinessRule("$a = sum(1,3)  if $a >= 4 then $c = 'ok'");

//		BusinessRule rule0 = new DefaultBusinessRule("$b = (exist for $job in jobs if $job.salary in [20000,40000]) ; print $b");

//		BusinessRule rule0 = new DefaultBusinessRule("$a=jobs;print $a[1:2]");

//		BusinessRule rule0 = new DefaultBusinessRule("print objectMap[key]");

		BusinessRule rule0 = new DefaultBusinessRule("$b = objectMap[key] ; if $b == '123' then $c= '123'");




//		ExecutionResult result0 = rule0.execute(context).orElseThrow(() -> new NullPointerException("result is null"));

		ExecutionResult result0 = rule0.execute(context).orElseThrow(() -> new NullPointerException("result is null"));
		return result0.getValue();
	}

	@Test
	public void  testSimple2() {
        List<String> result = new ArrayList<String>(executeRule(getUser()));
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

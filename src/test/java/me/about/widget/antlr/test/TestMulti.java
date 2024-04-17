package me.about.widget.antlr.test;

import java.util.*;

import com.github.javafaker.Faker;
import com.google.common.base.Joiner;
import me.about.widget.BusinessRule;
import me.about.widget.DefaultBusinessRule;
import me.about.widget.MultiBusinessRule;
import me.about.widget.RuleContext;
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

	private static final int LIST_SIZE = 100; // 指定生成的User数量
	private List<User> getUsers() {
		Faker faker = new Faker();

		List<User> users = new ArrayList<>();
		for (int i = 0; i < LIST_SIZE; i++) {
			List<Job> jobs = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				Job job = new Job();
				job.setName(faker.name().fullName());
				job.setSalary(faker.number().numberBetween(5000, 50000)); // 生成18到100岁之间的年龄
				jobs.add(job);
			}

			User user = new User();
			user.setName(faker.name().fullName());
			user.setAge(faker.number().numberBetween(18, 80)); // 生成18到80岁之间的年龄
			user.setJobs(jobs);
			users.add(user);
		}

		return users;
	}

	@Test
	public void testSimple() {

		String[] rules = new String[] {
				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",


				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",


				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",


				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",


				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",


				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"if age >= 10 and age < 20 then $a = name;$b = age;$c = '年龄在10-20岁之间'",
				"if age >= 20 and age < 40 then $a = name;$b = age;$c = '年龄在20-40岁之间'",
				"for $job in jobs if $job.salary >= 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水大于1w' + $job.salary",
				"for $job in jobs if $job.salary < 10000 then $a = name;$b = age;$c = '工作' + $job.name + ',薪水小于1w' + $job.salary",

				"$aa = name[0:1] ; if $aa == 'a' || $aa == 'A'  then $a = name;$b = age;$c = '名字首字母是a或A'",

		};

		List<User> users = getUsers();

		List<Map<String, Object>> results = new ArrayList<>();

		MultiBusinessRule rule = new MultiBusinessRule(Arrays.stream(rules)
				.map(DefaultBusinessRule::new)
				.toArray(BusinessRule[]::new));

		int total = 1000000;
		long started = System.nanoTime();
		for (User user : users) {
			List<Map<String, Object>> result = rule.execute(new RuleContext(user));
			results.addAll(result);
		}
		long elapsed = System.nanoTime() - started;
		long throughput = (long) ((double) total / elapsed * 1e9);

		System.out.println(Joiner.on("\n").join(results));

		System.out.println("Elapsed: " + (long) (elapsed / 1e6) + "ms. Throughput: " + throughput);


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

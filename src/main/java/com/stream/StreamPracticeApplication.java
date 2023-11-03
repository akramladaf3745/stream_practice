package com.stream;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.comparator.Comparators;

import com.stream.dsa.linkedList.LinkedList;

@SpringBootApplication
public class StreamPracticeApplication {
	static List<Employee> asList = new ArrayList<>();
	

	static {
		asList = generateEmployeeRecords(10000);

	}
	
	

//	private static final String WEEK_PATTERN = "EEEE dd MMM YYYY";
//	private static final String DATE_PATTERN = "dd";
//	private static final String YEAR_PATTERN = "MMMM";

	public static List<Employee> getAsList() {
		return asList;
	}

	public static void setAsList(List<Employee> asList) {
		StreamPracticeApplication.asList = asList;
	}

	public static void main(String[] args) {
		SpringApplication.run(StreamPracticeApplication.class, args);
		
		
		int[] arr = {1,5,2,4,45,7,8,9,3,5,2,58,8,44};
		
		StackTraceElement[] stac= new StackTraceElement[0];
		
		System.err.println(stac.length +" lenghth");
		
		List<Integer> collect = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).limit(4).skip(3).collect(Collectors.toList());
		
		System.err.println(collect.get(0));

		LinkedList linkedList = new LinkedList(0);

		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
//		linkedList.preApend(2);

		linkedList.getHead();
		linkedList.getTail();
		linkedList.getLength();

//		System.err.println("Remove First : " + linkedList.RemoveFirst().getValue());

		System.err.println("Get Index Based : " + linkedList.get(1).getValue());

		linkedList.set(1, 8);

		System.err.println("Get Index Based : " + linkedList.get(1).getValue());

		System.err.println("Remove Index Based : " + linkedList.remove(5).getValue());

		System.err.println("\n");
		linkedList.getHead();
		linkedList.getTail();
		linkedList.printLidst();
		System.err.println("\n");
		linkedList.reverse();
		linkedList.printLidst();

//		System.err.println(linkedList.removeLast().getValue());
//		System.err.println(linkedList.removeLast().getValue());
//		System.err.println(linkedList.removeLast());

	}

	private static List<Employee> generateEmployeeRecords(int numRecords) {

		List<Integer> usedIds = new ArrayList<>();
		Random random = new Random();
		return IntStream.range(0, numRecords)
				.mapToObj(x -> Employee.builder().id(random.nextInt(1000) + x).name(generateRandomName())
						.designation(generateRandomDesignation()).salary(generateRandomSalary())
						.referId(generateRandomReferId()).createdDate(generateRandomDateTime(2000, 2023)).build())
				.collect(Collectors.toList());

	}

	private static String generateRandomName() {
		String[] names = { "John Doe", "Jane Smith", "David Johnson", "Emily Davis", "Michael Wilson", "Linda Lee",
				"Robert Anderson", "Sarah Martinez", "William Taylor", "Karen Brown" };
		Random random = new Random();
		return names[random.nextInt(names.length)];
	}

	private static String generateRandomDesignation() {
		String[] designations = { "Manager", "Software Engineer", "Sales Representative", "Accountant",
				"HR Specialist" };
		Random random = new Random();
		return designations[random.nextInt(designations.length)];
	}

	private static int generateRandomSalary() {
		Random random = new Random();
		return 40000 + random.nextInt(50001); // Generating random salaries between 40000 and 90000
	}

	private static int generateRandomReferId() {
		Random random = new Random();
		return 101 + random.nextInt(100); // Generating random referIds between 101 and 200
	}

	private static LocalDateTime generateRandomDateTime(int startYear, int endYear) {
		Random random = new Random();
		int year = startYear + random.nextInt(endYear - startYear + 1); // Random year within the given range
		int month = 1 + random.nextInt(12); // Random month (1 to 12)
		int day = 1 + random.nextInt(28); // Random day (1 to 28; adjust based on specific needs)
		int hour = random.nextInt(24); // Random hour (0 to 23)
		int minute = random.nextInt(60); // Random minute (0 to 59)
		int second = random.nextInt(60); // Random second (0 to 59)

		return LocalDateTime.of(year, Month.of(month), day, hour, minute, second);
	}
}

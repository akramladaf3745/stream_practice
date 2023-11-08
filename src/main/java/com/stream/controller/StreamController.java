package com.stream.controller;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stream.EmployeeRepo;
import com.stream.StreamPracticeApplication;
import com.stream.response.SuccesResponse;

@RestController
@CrossOrigin("*")
public class StreamController {

	@Autowired
	private EmployeeRepo employeeRepo;

//	@GetMapping("/employee")
//	public ResponseEntity<List<Employee>> getEmployee(@RequestBody int[] ides) {
//		List<Employee> asList = StreamPracticeApplication.asList;
//
//		List<Employee> collect = asList.stream().filter(x -> x.getSalary() > 80000.00)
//				.collect(Collectors.collectingAndThen(Collectors.toList(), x -> {
//					List<Employee> elmpoyees = x;
//					List<Integer> collect2 = elmpoyees.stream().map(d -> d.getReferId()).collect(Collectors.toList());
////					List<Integer> collect3 = elmpoyees.stream().collect(Collectors.mapping(Employee::getReferId, Collectors.toList()));
//					elmpoyees
//							.addAll(Arrays.stream(ides).boxed().filter(f -> !collect2.contains(f))
//									.map(f -> Employee.builder().id(f + 100).name("ExtraName")
//											.designation("Extra Designation").salary(50000.00).referId(f).build())
//									.collect(Collectors.toList()));
//
//					return elmpoyees;
//				}));
//		return ResponseEntity.ok(collect);
//	}

//	@GetMapping("/mapping")
//	public ResponseEntity<?> mapping() {
//		List<Employee> asList = StreamPracticeApplication.asList;
//
//		Map<String, Double> collect = asList.stream().collect(Collectors.groupingBy(Employee::getName,
//				Collectors.mapping(Employee::getSalary, Collectors.reducing(0.0, (x, y) -> x + y))));
//		
//		Map<String, Double> collect4 = asList.stream().collect(Collectors.groupingBy(Employee::getName,Collectors.summingDouble(Employee::getSalary)));
//
//		String string = asList.stream().map(x -> x.getDesignation())
//				.collect(Collectors.reducing((f1, f2) -> f1.length() > f2.length() ? f1 : f2)).get();
//
//		Double collect2 = asList.stream().map(x -> x.getSalary()).collect(Collectors.reducing(0.0, Double::sum));
//
//		List<String> collect3 = asList.stream().collect(Collectors.mapping(Employee::getName, Collectors.toList()));
//
//		return ResponseEntity.ok(employeeRepo.saveAll(asList));
//	}

	@GetMapping("/getEmployee")
	public ResponseEntity<SuccesResponse> getEmployee() {
		return ResponseEntity.ok(SuccesResponse.builder().isError(false).message("fetch successfully")
				.data(employeeRepo.findAll()).build());
	}

	@GetMapping("/employees/{referalId}")
	public ResponseEntity<SuccesResponse> getEmployeeByReferalId(@PathVariable int referalId) {
		return ResponseEntity.ok(SuccesResponse.builder().isError(false).message("Data fetch successfully")
				.data(employeeRepo.findByReferId(referalId)).build());
	}

	@PostMapping("/save")
	public boolean save() {
		employeeRepo.saveAll(StreamPracticeApplication.getAsList());
		return true;
	}

	@GetMapping("apache/httpClient")
	public ResponseEntity<?> httpClient() {
		CloseableHttpClient createDefault = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"http://10.10.20.238:8765/api/v1/products/resellerProducts?resellerId=RS082300046");

		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				System.err.println("Response Code :" + status);
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				System.err.println("Throw Exception");
				System.err.println("Response Code :" + status);
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};

		try {
			String execute = createDefault.execute(httpGet, responseHandler);
			return ResponseEntity.ok(execute);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.badRequest().body(null);

	}

}

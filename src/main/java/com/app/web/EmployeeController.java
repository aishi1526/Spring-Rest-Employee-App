package com.app.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.app.dao.EmployeeDAO;
import com.app.model.Employee;

@RestController
@RequestMapping(value = "/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDAO dao;

	//http://localhost:9080/employee/
	
	@RequestMapping(value="/")
	public String getResponse() {
		return " Welcome to Employee Rest API Project  new";

	}
	
	//http://localhost:9080/employee/all
	
	@RequestMapping(value="/all")
	public List<Employee> readAllRecords() {
		System.out.println("\n *** the DAO employee impl class is  " + dao.getClass().getName());

		List<Employee> empList = (List<Employee>) dao.findAll();
		return empList;
	}
	
	//http://localhost:9080/employee/add
	
	@RequestMapping(value="/add" , method=RequestMethod.POST, consumes={"application/json"})
	public Employee addNewEmployee(@RequestBody Employee newEmp) {
		System.out.println("Add employee record");
		Employee saveObj=dao.save(newEmp);
		
		return saveObj;
	}
	
	//http://localhost:9080/employee/read/1
	
	@RequestMapping(value="read/{empid}", method=RequestMethod.GET, produces={"application/xml","application/json"})
	public Employee findAccountByPrimaryKey(@PathVariable Integer empid)
	{
		Optional<Employee > wrapper =dao.findById(empid);
		Employee  current= wrapper.get();
		return current;
		
	}
	
	//http://localhost:9080/employee/remove/1
	
	@RequestMapping(value="remove/{id}", method=RequestMethod.DELETE)
	public boolean removeEmployeeRecord(@PathVariable Integer id)
	{
		boolean exits =dao.existsById(id);
		if(exits == false)
			throw new RuntimeException("the account record not deleted" );
		
		Optional<Employee>  current=dao.findById(id);
		Employee record =current.get();
		
		dao.delete(record);
		return true;
	}
	
	//http://localhost:9080/employee/updateSalary
	
	@RequestMapping(value="/updateSalary" , method=RequestMethod.PUT)
	public Employee findAndUpdateSalary(@RequestBody Employee currentUpdate)
	{
		int currentID= currentUpdate.getEmp_id();
		boolean result=dao.existsById(currentID);
		
		if(result == false)
			throw new RuntimeException("the employee record with id " +currentID + " doesnot exit here" );
		
		Optional<Employee>  wrapped=dao.findById(currentID);
		Employee existingRecord =wrapped.get();
		
		existingRecord.setSalary(currentUpdate.getSalary());
		
		dao.save(existingRecord);
		return existingRecord;
		
	}
	
	//http://localhost:9080/employee/updateAddress
	
	@RequestMapping(value="/updateAddress" , method=RequestMethod.PUT)
	public Employee findAndUpdateAddress(@RequestBody Employee currentUpdate)
	{
		int currentID1= currentUpdate.getEmp_id();
		boolean result1=dao.existsById(currentID1);
		
		if(result1 == false)
			throw new RuntimeException("the employee record with id " +currentID1 + " doesnot exit here" );
	
		Optional<Employee>  wrapped1=dao.findById(currentID1);
		Employee existingRecord1 =wrapped1.get();
		
		existingRecord1.setHouse_number(currentUpdate.getHouse_number());
		existingRecord1.setRoadName(currentUpdate.getRoadName());
		
		dao.save(existingRecord1);
		return existingRecord1;
		
	}
	
	//http://localhost:9080/employee/updateBalance
	
	@RequestMapping(value="/updateBalance" , method=RequestMethod.PUT)
	public Employee findAndUpdateBalance(@RequestBody Employee currentUpdate)
	{
		int currentID2= currentUpdate.getEmp_id();
		boolean result2=dao.existsById(currentID2);
		
		if(result2 == false)
			throw new RuntimeException("the employee record with id " +currentID2 + " doesnot exit here" );
		
		Optional<Employee>  wrapped2=dao.findById(currentID2);
		Employee existingRecord2 =wrapped2.get();
		
		existingRecord2.setAccount_balance(currentUpdate.getAccount_balance());
		
		dao.save(existingRecord2);
		return existingRecord2;
		
	}

}

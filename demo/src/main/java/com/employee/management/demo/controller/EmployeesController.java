package com.employee.management.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employee.management.demo.entity.Employee;
import com.employee.management.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;

	// add mapping for "/list"

	@RequestMapping("/list")
	public String listEmployees(Model theModel) {
		System.out.println("request Recieved");

		// get Employee from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("Employees", theEmployees);
		return "list-employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("Employee", theEmployee);

		return "Employee-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		// get the Employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set Employee as a model attribute to pre-populate the form
		theModel.addAttribute("Employee", theEmployee);

		// send over to our form
		return "Employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@RequestParam("id") int id, @RequestParam("first_name") String FirstName,
			@RequestParam("last_name") String LastName, @RequestParam("email") String eMail) {

		System.out.println(id);
		Employee theEmployee;
		if (id != 0) {
			theEmployee = employeeService.findById(id);
			theEmployee.setFirstName(FirstName);
			theEmployee.setLastName(LastName);
			theEmployee.seteMail(eMail);
		} else
			theEmployee = new Employee(FirstName, LastName, eMail);
		// save the Employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the Employee
		employeeService.deleteById(theId);

		// redirect to /Employee/list
		return "redirect:/employees/list";

	}

	@RequestMapping("/search")
	public String search(@RequestParam("first_name") String FirstName, @RequestParam("last_name") String LastName,
			@RequestParam("email") String eMail, Model theModel) {

		// check names, if both are empty then just give list of all Employee

		if (FirstName.trim().isEmpty() && LastName.trim().isEmpty() && eMail.trim().isEmpty()) {
			return "redirect:/employees/list";
		} else {
			// else, search by first name and last name
			List<Employee> theEmployees = employeeService.searchBy(FirstName, LastName, eMail);
			// add to the spring model
			theModel.addAttribute("Employees", theEmployees);

			// send to list-Employee
			return "list-Employees";
		}

	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}

package com.example.doma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employees")
public class EmployeesController {

    @Autowired
    EmployeeDao dao;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("employees", dao.findAll());
        return "employees/index";
    }

    @GetMapping("new")
    public String addNew(@ModelAttribute EmployeeForm form) {
        return "employees/form";
    }

    @PostMapping
    public String create(@Validated @ModelAttribute EmployeeForm form,
                         BindingResult result) {

        if(result.hasErrors()) return "employees/form";

        Employee employee = new Employee();
        employee.name = form.getName();
        employee.email = Email.of(form.getEmail());
        dao.insert(employee);

        return "redirect:/employees";
    }

    @GetMapping("{id:[\\d]+}/edit")
    public String edit(@PathVariable("id") int id,
                       @ModelAttribute EmployeeForm form) {

        Employee employee = dao.findById(id);
        form.setId(employee.id);
        form.setName(employee.name);
        form.setEmail(employee.email.getValue());

        return "employees/form";
    }

    @PostMapping("{id:[\\d]+}")
    public String update(@PathVariable("id") int id,
                         @Validated @ModelAttribute EmployeeForm form,
                         BindingResult result) {

        if(result.hasErrors()) return "employees/form";

        Employee employee = dao.findById(id);
        employee.name = form.getName();
        employee.email = Email.of(form.getEmail());
        dao.update(employee);

        return "redirect:/employees";
    }

    @DeleteMapping("{id:[\\d]+}")
    public String destroy(@PathVariable("id") int id) {
        Employee employee = dao.findById(id);
        dao.delete(employee);
        return "redirect:/employees";
    }

}

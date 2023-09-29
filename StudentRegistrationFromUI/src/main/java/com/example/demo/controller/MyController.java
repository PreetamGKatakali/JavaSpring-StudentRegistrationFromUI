package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.SortById;
import com.example.demo.model.SortByName;
import com.example.demo.model.Student;
import com.example.demo.model.select;
import com.example.demo.repository.StudentRepo;

@Controller
public class MyController {
	
	@Autowired
	StudentRepo s;
	
	
	
	@GetMapping("/")
	public String getindex(Model model) {
		List<Student> list=s.findAll();
		model.addAttribute("student", list);
		return "index";
	}
	@RequestMapping("/Registration")
	public String getRegistration() {
		return "registration";
	}
	
	@PostMapping("/save")
	public String getstudent(@ModelAttribute Student std) {
		s.save(std);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{id}")
	public String deletestd(@PathVariable(name = "id") int id) {
		s.deleteById(id);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editstd(@PathVariable(name="id") int id) {
		ModelAndView m=new ModelAndView("editstudent");
		Student std=s.findById(id).get();
		System.out.println(std);
		m.addObject("Student",std);
		System.out.println(id);
		return m;
	}
	
	@PostMapping("/saveEdit")
	public String editstddetails(@ModelAttribute Student std) {
		s.save(std);
		return "redirect:/";
	}
	
	@PostMapping("/select")
	public String getselect(@ModelAttribute select sel) {
		String val=sel.getNumber();
		System.out.println(val);
		if(val.equals("1")) {
			return "redirect:/sortbyid";
		}
		else if(val.equals("2")) {
			return "redirect:/sortbyname";
		}
		else if(val.equals("0")) {
			System.out.println("hello codeing");
		}
		return "redirect:/";
	}
	
	@RequestMapping("/sortbyid")
	public String getsortbyid(Model model){
		List<Student> list=s.findAll();
		SortById sortid=new SortById();
		Collections.sort(list,sortid);
		model.addAttribute("student",list);
		return "sortbyid";
		
	}
	
	@RequestMapping("/sortbyname")
	public String getsortbyname(Model model){
		List<Student> list=s.findAll();
		SortByName sortname=new SortByName();
		Collections.sort(list,sortname);
		model.addAttribute("student",list);
		return "sortbyname";
		
	}
}

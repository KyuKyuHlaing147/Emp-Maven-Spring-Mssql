package com.example.controller;

import java.util.List;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod; 
import com.example.beans.Emp;    
import com.example.dao.EmpDao;  

@Controller
public class EmpController {

	 @Autowired    
	    EmpDao dao;//will inject dao from XML file    
	        
	    /*displays form to input data,"command" is a reserved request attribute  
	     */    
	    @RequestMapping("/empform")    
	    public String showform(Model m){    
	        m.addAttribute("command", new Emp());  
	        return "empform";   
	    } 
	    
	    /*saves object into database
	     */    
	    @RequestMapping(value="/save",method = RequestMethod.POST)    
	    public String save(@ModelAttribute("emp") Emp emp){    
	        dao.save(emp);    
	        return "redirect:/viewemp";  
	    }    
	    
	    /* list of employees in model object */    
	    @RequestMapping("/viewemp")    
	    public String viewemp(Model m){    
	        List<Emp> list=dao.getEmployees();    
	        m.addAttribute("list",list);  
	        return "viewemp";    
	    }    
	    /* It displays object data into form for the given id.   
	     * The @PathVariable puts URL data into variable.*/    
		/*
		 * @RequestMapping(value="/editemp/{id}") public String edit(@PathVariable int
		 * id, Model m){ Emp emp=dao.getEmpById(id); m.addAttribute("command",emp);
		 * return "empeditform"; }
		 */
	    @RequestMapping(value="/editemp/{id}")    
	    public String edit(@PathVariable int id, Model m){    
	        Emp emp = dao.getEmpById(id);    
	        m.addAttribute("emp", emp); 
	        return "empeditform";    
	    }

	    
	    /*updates model object.*/ 
	    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
	    public String editsave(@ModelAttribute("emp") Emp emp){    
	        dao.update(emp);    
	        return "redirect:/viewemp";    
	    }   
	    
	    /* It deletes record for the given id in URL and redirects to /viewemp */    
	    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
	    public String delete(@PathVariable int id){    
	        dao.delete(id);    
	        return "redirect:/viewemp";    
	    }   
	    
	    @RequestMapping("/index")
	    	public String showIndex(Model m) {
	    		List<Emp> list=dao.getEmployees();
	    		m.addAttribute("list",list);
	            return "index"; 
	        }
	    
	    
	    

	    

	    
	   
	    
}

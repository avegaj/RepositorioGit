package eb.onlinedc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eb.dcbackend.dao.SecondEntityDAO;
import eb.dcbackend.model.SecondEntity;

@Controller
@RequestMapping("/json/data")
public class JSONController {
	
	@Autowired
	private SecondEntityDAO secondEntityDAO;
	
	
	@RequestMapping("/entities")
	@ResponseBody
	public List<SecondEntity> getEntities(){
		return secondEntityDAO.list();
	}
	
	@RequestMapping("/{id}/entities")
	@ResponseBody
	public SecondEntity getEntities(@PathVariable int id){
		return secondEntityDAO.findById(id);
	}

}

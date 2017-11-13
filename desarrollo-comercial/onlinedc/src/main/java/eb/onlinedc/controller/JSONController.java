package eb.onlinedc.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eb.dcbackend.dao.SecondEntityDAO;
import eb.dcbackend.model.SecondEntity;

@Controller
@RequestMapping(value = "/json/data")
public class JSONController {
	
	@Autowired
	private SecondEntityDAO secondEntityDAO;
	
	@RequestMapping(value = "/SaveData", method = RequestMethod.POST)
	@ResponseBody
	public List<SecondEntity> SaveData(@RequestBody List<SecondEntity> excelData){
		return excelData;
	}
	
	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	@ResponseBody
	public List<SecondEntity> getEntities(){
		return secondEntityDAO.list();
	}
	
	@RequestMapping(value = "/{id}/entities", method = RequestMethod.GET)
	@ResponseBody
	public SecondEntity getEntities(@PathVariable int id){
		return secondEntityDAO.findById(id);
	}

}

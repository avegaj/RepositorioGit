/*package eb.dcbackend.test;*/

/*import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import eb.dcbackend.dao.SecondEntityDAO;
import eb.dcbackend.model.SecondEntity;*/

/*public class SecondEntityTest {*/

	
	/*private static AnnotationConfigApplicationContext context;*/
	
	/*private static SecondEntityDAO secondEntityDAO;*/
	
	/*@SuppressWarnings("unused")
	private SecondEntity secondEntity;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("eb.dcbackend");
		context.refresh();
		
		secondEntityDAO = (SecondEntityDAO)context.getBean("secondEntityDAO");
	}*/
	/*@Test
	public void testAddSecondEntity() {
		secondEntity = new SecondEntity();
		secondEntity.setName("Oty");
		secondEntity.setDescription("I am entity");
		secondEntityDAO.save(secondEntity);	
	}*/
	
	/*@Test
	public void testGetIdSecondrEntity() {
		secondEntity = secondEntityDAO.findById(1);
		assertEquals("Ok?","Oty",secondEntity.getName());
	}*/
	
	/*@Test
	public void testGetNameSecondrEntity() {
		secondEntity = secondEntityDAO.findByName("Eloy");
		assertEquals("Ok?","Eloy",secondEntity.getName());
	}
	
	@Test
	public void testDeleteSecondrEntity() {
		secondEntityDAO.deleteById(2);
	}*/
	
	/*@Test
	public void testUpdateSecondrEntity() {
		secondEntity = secondEntityDAO.get(1);
		secondEntity.setName("Carlos");
		
		assertEquals("Successfully update",true,secondEntityDAO.update(secondEntity));
	}*/
	
	/*@Test
	public void testDeleteSecondrEntity() {
		secondEntity = secondEntityDAO.get(4);
		
		assertEquals("Successfully delete",true,secondEntityDAO.delete(secondEntity));
	}*/
	
	/*@Test
	public void testListSecondrEntity() {
					
		assertEquals("Successfully list!",5,secondEntityDAO.list().size());
		
	}*/
/*}*/
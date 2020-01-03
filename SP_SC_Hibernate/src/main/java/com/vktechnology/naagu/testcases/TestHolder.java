package com.vktechnology.naagu.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.vktechnology.naagu.models.Holder;
import com.vktechnology.naagu.service.HolderService;

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml", "file:src/main/webapp/WEB-INF/spring-security.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHolder {
	
	@Autowired
	public HolderService holderService;
	
	public static final Logger logger = Logger.getLogger(TestHolder.class);
	
	@BeforeClass 
	public static void setUpBeforeClass() throws Exception { 		
		logger.info("@BeforeClass calling");  
	}  
	  
	@Before
	public void setBeforeEachTest(){		
		Authentication auth = new UsernamePasswordAuthenticationToken("naagu@gmail.com", "123456");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(auth);
		logger.info("@Before calling");
	}
	
	@Test(timeout=1000)
	@Transactional
	@Rollback(true)
	public void saveSouTest() throws Exception{
		Holder h = new Holder();
		h.setHolder_Date("11/03/2016");
		h.setHolder_Type("bank");
		h.setHolder_Name("iciciicici");
		h.setHolder_Amount(new BigDecimal("10.23"));
		h.setHolder_Description("for testing purpose");
		h.setHolder_Source("Salary");
		h.setHolder_User(getPrincipal());
		assertEquals("SuccessSourceToHolder", holderService.saveBank(h));
		logger.info("save source :");
	}
	
	@Test(timeout=1000)
	public void ListOfHolderTest() throws Exception{	
			assertNotNull(holderService.allTransList(getPrincipal()));
			logger.info("Success if object not null :");
	}
	
	@Test
	public void ListOfBankTest() throws Exception{	
			List<Holder> h = holderService.BankList(getPrincipal());
			assertNotNull(h);
			assertTrue(h instanceof List);
			logger.info("Success if object not null :");
	}
	
	@Ignore
	public void deductSourceTest() throws Exception{
		
	}
	
	@Test(timeout=1000)
	public void loadHolderListTest(){
		List<String> hd = holderService.loadHolderList(getPrincipal(), "bank");
		assertNotNull(hd);
		assertTrue(hd instanceof List);
		logger.info("test for loadHolderList");
	}
	
	@Test(timeout=1000)
	public void existOrNotBankTest(){
		List<String> hs = holderService.existOrNotBank(getPrincipal());
		assertNotNull(hs);
		assertTrue(hs instanceof List);
		logger.info("test for existOrNotBank");
	}
	
	@Test(timeout=1000)
	public void existOrNotSourceTest(){
		List<String> hs = holderService.existOrNotSource(getPrincipal());
		assertNotNull(hs);
		assertTrue(hs instanceof List);
		logger.info("test for existOrNotSource");
	}
	
	@Test
	public void holderBalanceTest(){
		BigDecimal b = holderService.holderBalance(getPrincipal(), "Pocket");
		assertFalse(b.equals(0));
		int res = b.compareTo(new BigDecimal("0"));
		if(res == 0){
			assertTrue(false);
		}else if(res == 1){
			assertTrue(true);
		}else if(res == -1){
			assertTrue(false);
		}
	}
	
	@After
	public void setAfterTest(){
		logger.info("@After test calling");
	}
	
	private String getPrincipal(){
	    String userName = null;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof UserDetails) {
	        userName = ((UserDetails)principal).getUsername();
	    } else {
	        userName = principal.toString();
	    }
	    return userName;
	}
	
	 @AfterClass 
	    public static void tearDownAfterClass() throws Exception {  
		 logger.info("@AfterClass test calling"); 
	 }  
	
	 
}

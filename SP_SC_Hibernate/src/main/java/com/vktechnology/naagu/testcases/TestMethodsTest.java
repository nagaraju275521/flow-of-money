package com.vktechnology.naagu.testcases;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vktechnology.naagu.dao.DebitDao;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.service.DebitService;
import com.vktechnology.naagu.service.SourceService;


@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml", "file:src/main/webapp/WEB-INF/spring-security.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class TestMethodsTest {

	   
	@Autowired
	private DebitService debitService;
	
	@Autowired
	private DebitDao debitDao;
	
	@Autowired
	private SourceService sourceService;

	private static final Logger logger = Logger.getLogger(TestMethodsTest.class);
	
@Before
public void setBeforeEachTest(){
	Authentication auth = new UsernamePasswordAuthenticationToken("naagu@gmail.com", "123456");
	SecurityContext securityContext = SecurityContextHolder.getContext();
	securityContext.setAuthentication(auth);
	logger.info("@Before calling");
}

@AfterClass
public static void setAfterClass(){
	SecurityContextHolder.clearContext();
	logger.info("@AfterClass calling");
}
	
	
@Test
/*@Transactional
@Rollback(true)*/
public void testFail() throws Exception {
	BigDecimal b = debitService.debitBalance("naagu@gmail.com", "bheem");
	assertFalse(b.equals(0));
	logger.info("Success--"+debitDao.debitBalance("naagu@gmail.com", "bheem"));
	
	}

@Test
public void ListOfSourceTest() throws Exception{	
		assertNotNull(sourceService.ListOfSource("naagu@gmail.com"));
		logger.info("Success if object not null :"+sourceService.ListOfSource("naagu@gmail.com"));
}

@Test
public void souListOnlyTest(){
	assertNotNull(sourceService.sourceListOnly("naagu@gmail.com"));
	logger.info("Success if object not null :"+sourceService.sourceListOnly("naagu@gmail.com"));
}

@Test
@Transactional
@Rollback(true)
public void saveSouTest() throws Exception{
	Source sou = new Source();
	sou.setSourceDate("11/03/2016");
	sou.setSourceType("Source");
	sou.setSourceName("Rent");
	sou.setSourceAmount(new BigDecimal("10.23"));
	sou.setSourceDescription("for testing purpose");
	assertEquals("SuccessAsSaveNew", sourceService.saveSource(sou));
	logger.info("save source :");
}


}

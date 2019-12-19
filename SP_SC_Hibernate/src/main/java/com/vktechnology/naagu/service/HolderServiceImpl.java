package com.vktechnology.naagu.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vktechnology.naagu.dao.HolderDao;
import com.vktechnology.naagu.models.AllTransRecord;
import com.vktechnology.naagu.models.Holder;
import com.vktechnology.naagu.models.HolderRecord;

public class HolderServiceImpl implements HolderService{
	
	@Autowired
	private HolderDao holderDao;
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Holder> BankList(String user){
		return holderDao.BankList(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveBank(Holder holder){
		return holderDao.saveBank(holder);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void saveBankList(Holder holder){
			holderDao.saveBankList(holder);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<HolderRecord> HolderList(String user){
		return holderDao.HolderList(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void deductSource(String Sname, BigDecimal Samount, String user){
		holderDao.deductSource(Sname, Samount, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void deductHolderMoney(String Sname, BigDecimal Samount, String user){
		holderDao.deductHolderMoney(Sname, Samount, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void AmountAddToHolder(String Hname, BigDecimal Hamount, String user){
		holderDao.AmountAddToHolder(Hname, Hamount, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> loadHolderList(String user, String holdertype){
		return holderDao.loadHolderList(user, holdertype);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> existOrNotBank(String user){
		return holderDao.existOrNotBank(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> availability_Holder_Name(String user, String holdertype, String holdername){
		return holderDao.availability_Holder_Name(user, holdertype, holdername);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Holder> existOrNot(Holder holder){
		return holderDao.existOrNot(holder);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String availablityCheckForHolder(String user, String source, BigDecimal amount){
		return holderDao.availablityCheckForHolder(user, source, amount);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public BigDecimal holderBalance(String user, String Source){
		return holderDao.holderBalance(user, Source);
	}

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String holderRecordExcel(XSSFWorkbook workbook) throws FileNotFoundException{
		return holderDao.holderRecordExcel(workbook);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String holderReRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return holderDao.holderReRecordExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> loadHolderComplteList(String user){
		return holderDao.loadHolderComplteList(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> existOrNotSource(String user){
		return holderDao.existOrNotSource(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<HolderRecord> HolderListForExl(String user, String fromDate, String toDate){
		return holderDao.HolderListForExl(user, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<AllTransRecord> allTransList(String user){
		return holderDao.allTransList(user);
	}
}

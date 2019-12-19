package com.vktechnology.naagu.dao;

import com.vktechnology.naagu.models.AllTransRecord;
import com.vktechnology.naagu.models.Holder;
import com.vktechnology.naagu.models.HolderRecord;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface HolderDao {

    public List<Holder> BankList(String user);
    public String saveBank(Holder holder);
    public void saveBankList(Holder holder);
    public List<HolderRecord> HolderList(String user);
    public void deductSource(String Sname, BigDecimal Samount, String user);
    public void deductHolderMoney(String Sname, BigDecimal Samount, String user);
    public void AmountAddToHolder(String Hname, BigDecimal Hamount, String user);
    public List<String> loadHolderList(String user, String holdertype);
    public List<String> existOrNotBank(String user);
    public List<String> availability_Holder_Name(String user, String holdertype, String holdername);
    public List<Holder> existOrNot(Holder holder);
    public String availablityCheckForHolder(String user, String source, BigDecimal amount);
    public BigDecimal holderBalance(String user, String Source);
    public String holderRecordExcel(XSSFWorkbook workbook) throws FileNotFoundException;
    public String holderReRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
    public List<String> loadHolderComplteList(String user);
    public List<String> existOrNotSource(String user);
    public List<HolderRecord> HolderListForExl(String user, String fromDate, String toDate);
    public List<AllTransRecord> allTransList(String user);
}

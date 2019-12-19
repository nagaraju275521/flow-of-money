package com.vktechnology.naagu.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.vktechnology.naagu.dao.CreditDao;
import com.vktechnology.naagu.service.CreditService;
import com.vktechnology.naagu.service.DebitService;
import com.vktechnology.naagu.service.ExpensesService;
import com.vktechnology.naagu.service.HolderService;
import com.vktechnology.naagu.service.SourceService;



@Controller
public class ExportController extends MultiActionController{
	
	@Autowired
    private CreditService creditService;
	
	@Autowired
    private ExpensesService expensesService;
	
	@Autowired
    private HolderService holderService;
	
	@Autowired
    private DebitService debitService;
	
	@Autowired
	private SourceService sourceService;
		
	@RequestMapping(value = "/generateExcelFile", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String generateExcelFile(HttpServletRequest request, HttpServletResponse response, XSSFWorkbook workbook,
    		@RequestParam("tableOption") String tableName,
    		@RequestParam("FromDate") String fromDate,
    		@RequestParam("ToDate") String toDate) throws FileNotFoundException, MessagingException, IOException{
		System.out.println(tableName+"-------------++generate Excel File++------------");
		String MessageGE = "none";
		System.out.println(fromDate+"--()---"+toDate);
		if("Credit".equalsIgnoreCase(tableName)){
			MessageGE = creditService.buildExcelDocument(workbook, fromDate, toDate);
		}else if("CreditRecord".equalsIgnoreCase(tableName)){
			MessageGE = creditService.creditRecordExcel(workbook, fromDate, toDate);
		}else if("Debit".equalsIgnoreCase(tableName)){
			MessageGE = debitService.debitListExcel(workbook);
		}else if("DebitRecord".equalsIgnoreCase(tableName)){
			MessageGE = debitService.debitRecordExcel(workbook, fromDate, toDate);
		}else if("Expenses".equalsIgnoreCase(tableName)){
			MessageGE = expensesService.generateExpensesExcel(workbook, fromDate, toDate);
		}else if("Holder".equalsIgnoreCase(tableName)){
			MessageGE = holderService.holderRecordExcel(workbook);
		}else if("HolderRecord".equalsIgnoreCase(tableName)){
			MessageGE = holderService.holderReRecordExcel(workbook, fromDate, toDate);
		}else if("Source".equalsIgnoreCase(tableName)){
			MessageGE = sourceService.sourceListExcel(workbook);
		}else if("SourceRecord".equalsIgnoreCase(tableName)){
			MessageGE = sourceService.sourceRecordExcel(workbook, fromDate, toDate);
		}else if("CreditClearRecord".equalsIgnoreCase(tableName)){
			MessageGE = creditService.creditClearReExcel(workbook, fromDate, toDate);
		}else if("DebitClearRecord".equalsIgnoreCase(tableName)){
			MessageGE = debitService.debitClearRecordExcel(workbook, fromDate, toDate);
		}else{
			MessageGE = "under Construction Sorry.....";
		}
		
		Gson gson = new Gson();
        String jsMessa = gson.toJson(MessageGE);
		return jsMessa;
	}
	
	
	@RequestMapping(value = "/sendUserDataToMail", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String sendUserDataToMail(HttpServletRequest request, HttpServletResponse response, XSSFWorkbook workbook,
    		@RequestParam("user_Mail") String usermail,
    		@RequestParam("Pass_word") String password,
    		@RequestParam("Subject") String subject,
    		@RequestParam("Message") String mailMessage,
    		@RequestParam("tableOption") String fileName,
    		@RequestParam("mailOption") String mailOption,
    		@RequestParam("Fdate") String fromDate,
    		@RequestParam("direct") String Direct,
    		@RequestParam("Tdate") String toDate) throws FileNotFoundException, MessagingException, IOException{
		System.out.println(usermail+"-------------++send table data mails++------------");
		String Message = "none";
		String FilePath = "null";
		String MessageFromMail = "none";
		System.out.println(fromDate+"---"+toDate+" ---"+Direct);
		String AjaxMessage = "none";
		File dir = new File("D:/FlowOfMoneyExcels");
		//dir.mkdir();
		
		if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }else{
        	System.out.println("Directory is already exist!");
        }
		
		if("SendMail".equals(mailOption)){
			String ToMail = usermail;
			String FromMail = usermail;
			String Password = password;
			String Subject = subject;
			String MailMessage = mailMessage;
			
			if("Credit".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = creditService.buildExcelDocument(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}
				if("Success".equals(Message)){
				FilePath = creditService.gettingFilePath();
				System.out.println("-file path---"+FilePath);
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}
			}else if("Expenses".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = expensesService.generateExpensesExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}else if("CreditRecord".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = creditService.creditRecordExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}				
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}else if("Holder".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = holderService.holderRecordExcel(workbook);
				}else{
					Message = "Success";
				}
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					System.out.println("-file path---"+FilePath);
						if(StringUtils.isEmpty(FilePath)){
							AjaxMessage = "file path Not selected";										
						}else{
							AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
						}
					}else{
						AjaxMessage = "fileNotCreate";
					}				
			}else if("HolderRecord".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = holderService.holderReRecordExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}				
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}else if("Debit".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = debitService.debitListExcel(workbook);
				}else{
					Message = "Success";
				}
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					System.out.println("-file path---"+FilePath);
						if(StringUtils.isEmpty(FilePath)){
							AjaxMessage = "file path Not selected";										
						}else{
							AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
						}
					}else{
						AjaxMessage = "fileNotCreate";
					}				
			}else if("DebitRecord".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = debitService.debitRecordExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}				
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}else if("Source".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = sourceService.sourceListExcel(workbook);
				}else{
					Message = "Success";
				}
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					System.out.println("-file path---"+FilePath);
						if(StringUtils.isEmpty(FilePath)){
							AjaxMessage = "file path Not selected";										
						}else{
							AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
						}
					}else{
						AjaxMessage = "fileNotCreate";
					}				
			}else if("SourceRecord".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = sourceService.sourceRecordExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}				
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}else if("CreditClearRecord".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = creditService.creditClearReExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}				
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}else if("DebitClearRecord".equalsIgnoreCase(fileName)){
				if("null".equals(Direct)){
					Message = debitService.debitClearRecordExcel(workbook, fromDate, toDate);
				}else{
					Message = "Success";
				}				
				if("Success".equals(Message)){
					FilePath = creditService.gettingFilePath();
					if(StringUtils.isEmpty(FilePath)){
						AjaxMessage = "file path Not selected";										
					}else{
						AjaxMessage = creditService.mailSuccessMessage(Message, ToMail, FromMail, Password, FilePath, Subject, MailMessage);
					}
				}else{
					AjaxMessage = "fileNotCreate";
				}				
			}
		}else{
			AjaxMessage = "select Mail";
		}
		

		
		Gson gson = new Gson();
        String jsMessage = gson.toJson(AjaxMessage);
		return jsMessage;
            }

	
}

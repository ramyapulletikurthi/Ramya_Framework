package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.RegistrationBean;
import com.training.dao.ELearningDAO;
import com.training.dao.RegistrationDAO;
import com.training.readexcel.ApachePOIExcelRead;

public class RegistrationDataProviders {

	@DataProvider(name = "db-inputs")
	public Object[][] getDBData() {

		List<RegistrationBean> list = new RegistrationDAO().getregistration();

		Object[][] result = new Object[list.size()][];
		int count = 0;
		for (RegistrationBean temp : list) {
			Object[] obj = new Object[8];
			obj[0] = temp.getFirstName();
			obj[1] = temp.getLastName();
			obj[2] = temp.geteMail();
			obj[3] = temp.getUserName();
			obj[4] = temp.getPassword();
			obj[5] = temp.getConfirmPassword();
			obj[6] = temp.getPhoneNumber();
			obj[7] = temp.getLanguage();

			result[count++] = obj;
		}

		return result;
	}

	@DataProvider(name = "TestData_ELTC091")
	public Object[][] getExcelData_ELTC91() {
		String fileName = "./resources/Testing.xlsx";
		String sheetName = "TestData_ELTC091";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName);
	}

	@DataProvider(name = "TestData_ELTC092")
	public Object[][] getExcelData_ELTC92() {
		String fileName = "./resources/Testing.xlsx";
		String sheetName = "TestData_ELTC092";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName);
	}

	@DataProvider(name = "TestData_ELTC095")
	public Object[][] getExcelData_ELTC95() {
		String fileName = "./resources/Testing.xlsx";
		String sheetName = "TestData_ELTC095";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName);
	}

}

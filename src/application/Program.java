package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Department;
import model.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Department d1 = new Department(1, "Computers");
		Seller s1 = null;
		try {
			s1 = new Seller(4, "Joao Pedro", "joaodorea300@gmail.com", sdf.parse("22/01/1999"), 15000.0, d1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sellerDao.update(s1);
		

	
		
		/*
		for(Seller x: sellerDao.findAll()) {
			System.out.println(x);
		}
		*/
		

	}

}

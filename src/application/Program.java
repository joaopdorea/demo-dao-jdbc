package application;

import java.util.Date;

import model.Department;
import model.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj = new Department(1, "Books");
		System.out.println(obj);
		
		Seller s1 = new Seller(21, "Joao", "joaodorea@gmail.com", new Date(), 3000.0, obj);
		
		System.out.println(s1);

	}

}

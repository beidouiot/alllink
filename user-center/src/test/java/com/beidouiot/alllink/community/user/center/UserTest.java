package com.beidouiot.alllink.community.user.center;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
*
* @Description TODO
* @author longww
* @date 2021年6月14日
*/
@SpringBootTest
public class UserTest {

	@Test
	public void study() {
		Date date = new Date();
		System.out.println(date);
		Object obj = date;
		System.out.println(obj);
		System.out.println(obj.toString());
		Date date1 = new Date(1642926952570l);
		System.out.println(date1);
		Object obj1 = date;
		System.out.println(obj1);
		System.out.println(obj1.toString());
		
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.toString());
		Object obj = date;
		System.out.println(obj);
		System.out.println(obj.toString());
		Date date1 = new Date(1642929866092l);
		System.out.println(date1);
		Object obj1 = date1;
		System.out.println(obj1);
		System.out.println(obj1.toString());
	}
 }

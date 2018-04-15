package controller;

import org.junit.Test;

import com.jingxi.common.pojo.ItemInfo;

public class TestCast {
	
	@Test
	public void test() {
		People p =new People();
		p.id=0;
		
		Object obj = p;
		
		Student s = (Student) obj;
		s.id=22;
		System.out.println(s.id);

	}
	
//	@Test
//	public void test () {
//		People p =new Student();
//		p.id=000;
//		Student s = new Student();
//		s.id=22;
//		s = (Student) p;
//		System.out.println(s.id);
//	}
	
	
//	@Test
//	public void test() {
//		People p =new People();
//		p.id=000;
//		Student s = new Student();
//		s.id=22;
//		s = (Student) p;
//		System.out.println(s.id);
//	}
	
//	@Test
//	public void test () {
//		TbItem item = new TbItem();
//		//item.setCid(11111111);long?????
//		item.setBarcode("yishion");
//		//ItemInfo itemInfo = (ItemInfo) item;
//		ItemInfo itemInfo = new ItemInfo();
//		itemInfo = (ItemInfo) item;
//		System.out.println(itemInfo.getBarcode());
//	}
	
//	@Test
//	public void test1() {
//		ItemInfo itemInfo = new ItemInfo();
//		
//		TbItem item = new ItemInfo();
//		
//		item.setBarcode("00");
//		itemInfo=(ItemInfo) item;
//		System.out.println(itemInfo.getBarcode());
//	}
	
	
	
	class People
	{
		int id;
		String name;
	
		People(){}
	
		People(int id,String name)
		{
		   this.id=id;
		   this.name=name;
		}
	}
	
	class Student extends People
	{
		float score;
		Student()
		{
		}
		Student(int id,String name,float score)
		{
		   this.id=id;
		   this.name=name;
		   this.score=score;
		}
	}
}

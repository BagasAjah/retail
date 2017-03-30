package com.example.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Employee;
import com.example.model.Store;
import com.example.repository.EmployeeRepository;
import com.example.repository.StoreRepository;

@RunWith(SpringRunner.class)
public class DiscountServiceTest {
	
	@Mock
	private EmployeeRepository mockEmployeeRepository;
	
	@Mock
	private StoreRepository mockStoreRepository;
	
	@InjectMocks
	private DiscountService discService;
	
	@Test
	public void test01GetDiscountWhenResultIsEmployeeDiscount() {
		/** 
		 * test using employee type = employee and and work on current store
		 * and get discount 30%
		 */
		Employee emp = new Employee();
		emp.setCreatedDate(new Date());
		emp.setFullName("Employee 1");
		emp.setStoreId(1);
		emp.setEmployeeType(Employee.EMPLOYEE);
		
		Store store = new Store();
		store.setStoreName("Store 1");
		store.setStoreType(Store.STORE);
		
		when(mockEmployeeRepository.findOne(any(Long.class))).thenReturn(emp);
		when(mockStoreRepository.findOne(any(Long.class))).thenReturn(store);
		double result = discService.getDiscount(1, 1, 50);
		verify(mockEmployeeRepository).findOne(any(Long.class));
		verify(mockStoreRepository).findOne(any(Long.class));
		Assert.assertEquals(35, result, 0);
	}
	
	@Test
	public void test02GetDiscountWhenResultIsAffiliateDiscount() {
		/** 
		 * test using employee type = affiliate for current store
		 * and get discount 15%
		 */
		Employee emp = new Employee();
		emp.setCreatedDate(new Date());
		emp.setFullName("Employee 1");
		emp.setStoreId(1);
		emp.setEmployeeType(Employee.AFFILIATE);
		
		Store store = new Store();
		store.setStoreName("Store 1");
		store.setStoreType(Store.STORE);
		
		when(mockEmployeeRepository.findOne(any(Long.class))).thenReturn(emp);
		when(mockStoreRepository.findOne(any(Long.class))).thenReturn(store);
		double result = discService.getDiscount(1, 1, 50);
		verify(mockEmployeeRepository).findOne(any(Long.class));
		verify(mockStoreRepository).findOne(any(Long.class));
		Assert.assertEquals(42.5, result, 0);
	}
	
	@Test
	public void test03GetDiscountWhenResultIsCustomerDiscount() {
		/** 
		 * test using customer for over 2 years
		 * and get discount 5%
		 */
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -3);
		Date past3Years = cal.getTime();
		
		Employee emp = new Employee();
		emp.setCreatedDate(past3Years);
		emp.setFullName("Employee 1");
		emp.setStoreId(1);
		emp.setEmployeeType(Employee.OTHER);
		
		Store store = new Store();
		store.setStoreName("Store 1");
		store.setStoreType(Store.STORE);
		
		when(mockEmployeeRepository.findOne(any(Long.class))).thenReturn(emp);
		when(mockStoreRepository.findOne(any(Long.class))).thenReturn(store);
		double result = discService.getDiscount(1, 1, 50);
		verify(mockEmployeeRepository).findOne(any(Long.class));
		verify(mockStoreRepository).findOne(any(Long.class));
		Assert.assertEquals(47.5, result, 0);
	}
	
	@Test
	public void test04GetDiscountWhenResultIsAmountDiscount() {
		/** 
		 * test using groceries store and total amount = $990
		 * and get discount $45
		 */
		
		Store store = new Store();
		store.setStoreName("Store 1");
		store.setStoreType(Store.GROCERIES);
		
		when(mockStoreRepository.findOne(any(Long.class))).thenReturn(store);
		double result = discService.getDiscount(1, 1, 990);
		verify(mockStoreRepository).findOne(any(Long.class));
		Assert.assertEquals(945, result, 0);
	}
	
	@Test
	public void test05GetDiscountWhenResultIsWithoutDiscount() {
		/** 
		 * test using groceries store and total amount = $50
		 * and not get discount
		 */
		
		Store store = new Store();
		store.setStoreName("Store 1");
		store.setStoreType(Store.GROCERIES);
		
		when(mockStoreRepository.findOne(any(Long.class))).thenReturn(store);
		double result = discService.getDiscount(1, 1, 50);
		verify(mockStoreRepository).findOne(any(Long.class));
		Assert.assertEquals(50, result, 0);
	}
	
	@Test
	public void test06GetDiscountWhenResultIsWithoutDiscount() {
		/** 
		 * test using customer for not over 2 years
		 * and not get discount
		 */
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		Date past1Years = cal.getTime();
		
		Employee emp = new Employee();
		emp.setCreatedDate(past1Years);
		emp.setFullName("Employee 1");
		emp.setStoreId(1);
		emp.setEmployeeType(Employee.OTHER);
		
		Store store = new Store();
		store.setStoreName("Store 1");
		store.setStoreType(Store.STORE);
		
		when(mockEmployeeRepository.findOne(any(Long.class))).thenReturn(emp);
		when(mockStoreRepository.findOne(any(Long.class))).thenReturn(store);
		double result = discService.getDiscount(1, 1, 50);
		verify(mockEmployeeRepository).findOne(any(Long.class));
		verify(mockStoreRepository).findOne(any(Long.class));
		Assert.assertEquals(50, result, 0);
	}
}

package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ItemDao;
import com.app.model.Items;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	public void add(Items item) throws Exception{
		try {
			itemDao.add(item);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<?> getAllItem() throws Exception{
		return itemDao.getAllItem();
	}
	
}

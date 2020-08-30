package com.app.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.ItemDao;
import com.app.model.Items;
import com.app.pojo.PojoItems;
import com.app.pojo.PojoSearchItem;

@Service
@Transactional
public class ItemService extends BaseService {

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ItemDetailService itemDetailService;
	
	@Value("${upload.path}")
	private String path;
	
	@Value("${image.path}")
	private String imagePath;
	
	public Items getById(String id) throws Exception{
		Items item = itemDao.getById(id);
		if(item == null) {
			throw new Exception("Item not found !");
		}else {
			return item;
		}
	}
	
	public void add(String items,MultipartFile file) throws Exception{
		try {
			Items item = readValue(items, Items.class);
			item.setCreatedBy(item.getMerchant().getName());
			item.setUpdatedBy(item.getMerchant().getName());
			itemDao.add(item);
			itemDetailService.add(item, file);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void edit(Items tempItem) throws Exception{
		try {
			Items item = getById(tempItem.getId());
			item.setQuantity(tempItem.getQuantity());
			item.setPrice(tempItem.getPrice());
			item.setName(tempItem.getName());
			item.setUpdatedBy(tempItem.getMerchant().getName());
			item.setSale(tempItem.getSale());
			item.setDescription(tempItem.getDescription());
			item.setIsActive(tempItem.getIsActive());
			item.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			itemDao.add(item);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void addPhotoItem(String items,MultipartFile file) throws Exception{
		try {
			Items item = new Items();
			item = readValue(items, Items.class);
			itemDetailService.add(item, file);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Object> getAllItem() throws Exception{
		return itemDao.getAllItem();
	}
	
	public List<Object> getItemSale() throws Exception{
		return itemDao.getItemSale();
	}
	
	public PojoItems getItemById(String id) throws Exception{
		PojoItems pojo = new PojoItems();
		Items item = getById(id);
		if (item != null) {
			pojo.setItem(itemDao.getPojoItemById(id));
			pojo.setListItemDetail(itemDetailService.getPojoDetailItemByItemId(id));
		}
		return pojo;
	}

	public List<Object> getItemBySearch(PojoSearchItem pojoSearch) throws Exception{
		return itemDao.getItemBySearch(pojoSearch);
	}

}

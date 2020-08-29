package com.app.service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.ItemDao;
import com.app.dao.ItemDetailDao;
import com.app.model.Items;
import com.app.model.ItemsDetail;

@Service
public class ItemService extends BaseService {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemDetailDao itemDetailDao;
	
	@Value("${upload.path}")
	private String path;
	
	@Value("${image.path}")
	private String imagePath;
	
	public void add(String items,MultipartFile file) throws Exception{
		try {
			File files = new File(path);
			InputStream is=null;
			Items item = readValue(items, Items.class);
			item.setCreatedBy(item.getMerchant().getName());
			item.setUpdatedBy(item.getMerchant().getName());
			itemDao.add(item);
	
			ItemsDetail itemDetail = setCreatedAtAndUpdateAtBySystem(ItemsDetail.class);
			itemDetail.setItem(item);
			itemDetailDao.add(itemDetail);
			if (!Files.exists(Paths.get(path))) {
				files.mkdirs();         
			}
			
			if(file != null) {
	            Files.copy(is, Paths.get(imagePath + itemDetail.getId()+"_"+itemDetail.getFileName()),
	                    StandardCopyOption.REPLACE_EXISTING);
	        }
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<?> getAllItem() throws Exception{
		return itemDao.getAllItem();
	}
	
	public void createUUID() throws Exception{
		itemDao.createUUID();
	}
}

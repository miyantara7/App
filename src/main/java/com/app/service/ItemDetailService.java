package com.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.ItemDetailDao;
import com.app.model.Items;
import com.app.model.ItemsDetail;
import com.app.pojo.PojoItemDetail;

@Service
@Transactional
public class ItemDetailService  {

	
	@Autowired
	private ItemDetailDao itemDetailDao;
	
	@Value("${image.path}")
	private String imagePath;
	
	public void add(Items item,MultipartFile file) throws Exception{
		try {
			InputStream is=null;
			ItemsDetail itemDetail = new ItemsDetail();
			itemDetail.setItem(item);
			itemDetail.setCreatedBy(item.getMerchant().getName());
			itemDetail.setUpdatedBy(item.getMerchant().getName());
			if (file != null) {
				is = file.getInputStream();
				itemDetail.setFileName(file.getOriginalFilename());
				itemDetail.setFileType(file.getContentType());
			}
			
			itemDetailDao.add(itemDetail);
			
			if(file != null) {
				Files.copy(is, Paths.get(imagePath + itemDetail.getId() + "_" +file.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
	        }
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<PojoItemDetail> getPojoDetailItemByItemId(String id) throws Exception{
		List<PojoItemDetail> listItemDetail = new ArrayList<>();
		try {			
			List<PojoItemDetail> result = itemDetailDao.getPojoDetailItemByItemId(id);
			if (!result.isEmpty()) {
				for (PojoItemDetail pojoItemDetail : result) {
					String filePath = pojoItemDetail.getId() + "_" + pojoItemDetail.getFileName();
					File file = new File(imagePath + filePath);
					if (file.exists()) {
						try {
							pojoItemDetail.setPhoto(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					listItemDetail.add(pojoItemDetail);
				}
			}
			return listItemDetail;
		}catch (Exception e) {
			throw e;
		}
	}
}

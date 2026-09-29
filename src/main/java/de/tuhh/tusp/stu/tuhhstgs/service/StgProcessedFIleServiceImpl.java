package de.tuhh.tusp.stu.tuhhstgs.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.tuhhstgs.dao.StgProcessedFileDao;
import de.tuhh.tusp.stu.tuhhstgs.pojo.StgProcessedFile;
import de.tuhh.tusp.utils.MD5Impl;
import de.tuhh.tusp.utils.MessageCollectorImpl;

@Component
public class StgProcessedFIleServiceImpl implements StgProcessedFileService {

	private static Logger logger = LogManager.getLogger(StgProcessedFIleServiceImpl.class);

	@Autowired
	private StgProcessedFileDao dao;

	@Override
	public void markAsProcessed(List<Resource> inResources, Resource outResource) {

		if (outResource == null) {
			return;
		}

		for (Resource res : inResources) {
			try {
				StgProcessedFile processedFile = new StgProcessedFile();
				processedFile.setInName(res.getFilename());
				processedFile.setOutName(outResource.getFilename());
				processedFile.setHash(MD5Impl.getMd5(res.getInputStream()));
				processedFile.setVerarbeitet(new Date());
				dao.insert(processedFile);
			} catch (Exception e) {
				String msg = "Konnte File nicht als verarbeitet markieren." + res;
				logger.error(msg, e);
				MessageCollectorImpl.getInstance(Thread.currentThread()).addErrorMessage(msg);
			}
		}

	}

}

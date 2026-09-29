package de.tuhh.tusp.stu.tuhhstgs.service;

import java.util.List;

import org.springframework.core.io.Resource;

public interface StgProcessedFileService {

	void markAsProcessed(List<Resource> inResources, Resource outResource);

}

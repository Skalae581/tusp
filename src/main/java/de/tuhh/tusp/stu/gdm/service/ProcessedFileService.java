package de.tuhh.tusp.stu.gdm.service;

import java.util.List;

import org.springframework.core.io.Resource;

public interface ProcessedFileService {

	/**
	 * Speichert für alle inRessources, dass sie verarbeitet wurden.
	 * 
	 * @param inResources
	 * @param outResource
	 */
	void markAsProcessed(List<Resource> inResources, Resource outResource);
}

package de.tuhh.tusp.stu.tuhhstgs.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.w3c.dom.Document;

import de.tuhh.tusp.global.service.JobLogger;

public interface StgFileOutputService {

	Resource getFileByName(String name) throws IOException, SecurityException, FileNotFoundException, RuntimeException;

	List<Resource> listLokaleDateien() throws IOException, RuntimeException;

	WritableResource writeFile(Document document, JobLogger jobLogger);

}

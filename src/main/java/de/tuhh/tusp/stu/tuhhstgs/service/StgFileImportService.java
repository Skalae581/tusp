package de.tuhh.tusp.stu.tuhhstgs.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;

import de.tuhh.tusp.stu.gdm.exception.KeineAktuellenFilesException;

public interface StgFileImportService {

	List<Resource> findStgFiles() throws IOException, KeineAktuellenFilesException;

}

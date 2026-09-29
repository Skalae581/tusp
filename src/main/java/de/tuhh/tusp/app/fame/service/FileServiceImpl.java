package de.tuhh.tusp.app.fame.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import de.tuhh.tusp.app.fame.dao.FileDao;
import de.tuhh.tusp.app.fame.dao.ValueDao;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.exception.SecurityException;
import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFile;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFileMitRecht;

@Component
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao dao;

	@Autowired
	private ValueDao valueDao;

	@Override
	public void delete(ExtendedFileMitRecht file) {

		if (file.getRecht() == null || file.getRecht() != InputRechteEnum.WRITE) {
			throw new SecurityException();
		}
		dao.delete(file.getId());
		valueDao.delete(file.getValueId());
	}

	@Override
	public int delete(Long id) {

		return dao.delete(id);
	}

	@Override
	public File fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public ExtendedFile fetchByValueId(Long id) {

		return dao.fetchByValueId(id);
	}

	@Override
	public ExtendedFileMitRecht fetchByValueId(Long valueId, List<String> rollen) {

		return dao.fetchByValueId(valueId, rollen);
	}

	@Override
	public List<File> findAll() {

		return dao.findAll();
	}

	@Override
	public Long insert(File object) {

		return dao.insert(object);
	}

	@Override
	public void saveFile(Long vorgangId, Long inputId, MultipartFile data) throws IOException {

		Value value = valueDao.fetchByVorgangAndInput(vorgangId, inputId);
		if (value == null) {
			value = new Value();
			value.setInputId(inputId);
			value.setVorgangId(vorgangId);
			value.setEingabe(data.getOriginalFilename());
			value.setId(valueDao.insert(value));
		}
		else {
			value.setEingabe(data.getOriginalFilename());
			valueDao.update(value);
		}

		File fameFile = dao.fetchByValueId(value.getId());

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IOUtils.copy(data.getInputStream(), os);
		if (fameFile == null) {
			fameFile = new File();
			fameFile.setValueId(value.getId());
			fameFile.setMimeType(data.getContentType());
			fameFile.setContent(os.toByteArray());
			dao.insert(fameFile);
		}
		else {
			fameFile.setMimeType(data.getContentType());
			fameFile.setContent(os.toByteArray());
			dao.update(fameFile);
		}
	}

	@Override
	public void update(File object) {

		dao.update(object);
	}
}

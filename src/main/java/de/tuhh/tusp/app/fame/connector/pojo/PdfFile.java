package de.tuhh.tusp.app.fame.connector.pojo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.app.fame.service.FileService;
import de.tuhh.tusp.app.fame.service.ValueService;
import lombok.Data;
import lombok.NonNull;

@Data
public class PdfFile implements ConnectorPojo {

	private static Logger logger = LogManager.getLogger(PdfFile.class);

	private String id;
	private String fileName;
	private byte[] content = new byte[0];
	private String type;
	private boolean delete = false;

	@NonNull
	private ValueService valueService;
	@NonNull
	private FileService fileService;

	@Override
	public void save(ExtendedInput input) throws ImportException, PflichtfeldException, RegexException {

		if (!InputTypeEnum.CONNECTOR_FILE_NAME.equals(input.getInputTypeUniqName())) {
			throw new ImportException("Input " + input.getName() + " muss vom Typ "
					+ InputTypeEnum.CONNECTOR_FILE_NAME.name() + " sein.");
		}

		Long valueId = valueService.save(input, fileName, false);

		File fameFile = fileService.fetchByValueId(valueId);

		if (delete) {
			if (fameFile != null) {
				logger.debug("Lösche file " + fameFile);
				fileService.delete(fameFile.getId());
			}
			valueService.delete(valueId);
			return;
		}

		if (fameFile == null) {
			fameFile = new File();
			fameFile.setValueId(valueId);
			fameFile.setMimeType(getType());
			fameFile.setContent(getContent());
			fileService.insert(fameFile);
		} else {
			fameFile.setMimeType(getType());
			fameFile.setContent(getContent());
			fileService.update(fameFile);
		}
	}

	/**
	 * Setzt den Dateiinhalt
	 *
	 * @param content
	 */
	public void setContent(byte[] content) {

		if (content == null) {
			this.content = new byte[0];
		} else {
			this.content = content;
		}
	}

	@Override
	public String toString() {

		return "PdfFile(" + fileName + ", " + content.length + " bytes" + (delete ? ", delete" : "") + ")";
	}
}

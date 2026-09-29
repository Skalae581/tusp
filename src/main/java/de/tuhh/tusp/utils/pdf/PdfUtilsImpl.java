package de.tuhh.tusp.utils.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.CryptoException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import de.tuhh.tusp.utils.InMemoryResource;

@Component
public class PdfUtilsImpl implements PdfUtils {

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";

	@Override
	public void addAttachment(PdfWriter writer, Resource attachment) throws IOException {

		PdfFileSpecification fs =
				PdfFileSpecification.fileEmbedded(	writer, null, attachment.toString(),
													ByteStreams.toByteArray(attachment.getInputStream()));
		writer.addFileAttachment(attachment.getFilename().substring(0, attachment.getFilename().indexOf('.')), fs);
		writer.setViewerPreferences(PdfWriter.PageModeUseNone);
	}

	@Override
	public void crypt(	int cipherMode, String key, Resource inputFile,
						WritableResource outputFile) throws CryptoException {

		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);

			InputStream inputStream = inputFile.getInputStream();
			byte[] inputBytes = ByteStreams.toByteArray(inputStream);
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			OutputStream outputStream = outputFile.getOutputStream();
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		}
		catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new CryptoException("Error encrypting/decrypting file: " + ex);
		}
	}

	@Override
	public Resource decrypt(String key, Resource inputFile) throws CryptoException {

		WritableResource outputFile = new InMemoryResource(inputFile.getFilename());
		crypt(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
		return outputFile;
	}

	@Override
	public Resource encrypt(String key, Resource inputFile) throws CryptoException {

		WritableResource outputFile = new InMemoryResource(inputFile.getFilename());
		crypt(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
		return outputFile;
	}

	@Override
	public List<Resource> readAttachments(PdfReader reader) throws IOException {

		List<Resource> resources = new ArrayList<>();
		Set<String> filesnames = new HashSet<>(); // Soll sicherstellen, dass Files nicht doppelt sind

		PdfDictionary catalog = reader.getCatalog();
		PdfDictionary names = catalog.getAsDict(PdfName.NAMES);

		if (names == null) {
			return new ArrayList<>();
		}

		PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
		PdfArray filespecs = embeddedFiles.getAsArray(PdfName.NAMES);

		PdfDictionary files = filespecs.getAsDict(1);
		PdfDictionary refs = files.getAsDict(PdfName.EF);
		Set<PdfName> keys = refs.getKeys();

		for (PdfName key : keys) {
			PRStream prStream = (PRStream) PdfReader.getPdfObject(refs.getAsIndirectObject(key));
			String filename = files.getAsString(key).toString();
			if (filesnames.contains(filename)) {
				// Irgendwie können die Dateien doppelt sein.
				// Also bei zweiten auftreten des Namens:
				continue;
			}
			WritableResource resource = new InMemoryResource(filename);
			OutputStream outputStream = resource.getOutputStream();
			outputStream.write(PdfReader.getStreamBytes(prStream));
			resources.add(resource);
			filesnames.add(filename);

			outputStream.close();
		}

		return resources;
	}

}
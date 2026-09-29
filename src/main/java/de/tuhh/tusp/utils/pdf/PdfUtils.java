package de.tuhh.tusp.utils.pdf;

import java.io.IOException;
import java.util.List;

import org.bouncycastle.crypto.CryptoException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public interface PdfUtils {

	void addAttachment(PdfWriter writer, Resource attachment) throws IOException;

	void crypt(int cipherMode, String key, Resource inputFile, WritableResource outputFile) throws CryptoException;

	Resource decrypt(String key, Resource inputFile) throws CryptoException;

	Resource encrypt(String key, Resource inputFile) throws CryptoException;

	List<Resource> readAttachments(PdfReader reader) throws IOException;

}
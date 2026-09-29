package de.tuhh.tusp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

/**
 * Implementierung einer WritableResource, die nicht im Dateisystem existiert.
 *
 * @author rztjm
 *
 */
public class InMemoryResource implements WritableResource {

	private ByteArrayOutputStream writableStream = new ByteArrayOutputStream();
	private String fileName;
	private String mimeType = null;

	/**
	 * Erstellt eine Instanz
	 *
	 * @param fileName
	 */
	public InMemoryResource(String fileName) {

		this.fileName = fileName;
	}

	public InMemoryResource(String fileName, byte[] data) {

		this.fileName = fileName;
		if (data != null) {
			this.writableStream.write(data, 0, data.length);
		}
	}

	@Override
	public long contentLength() throws IOException {

		return writableStream.toByteArray().length;
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {

		return this;
	}

	@Override
	public boolean exists() {

		return writableStream.size() != 0;
	}

	@Override
	public String getDescription() {

		throw new NotImplementedException("getDescription() nicht supportet");
	}

	@Override
	public File getFile() throws IOException {

		throw new NotImplementedException("getFile() nicht supportet");
	}

	@Override
	public String getFilename() {

		return fileName;
	}

	@Override
	public InputStream getInputStream() throws IOException {

		return new ByteArrayInputStream(writableStream.toByteArray());
	}

	public String getMimeType() {

		return mimeType;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {

		return writableStream;
	}

	@Override
	public URI getURI() throws IOException {

		throw new NotImplementedException("getURI() nicht supportet");
	}

	@Override
	public URL getURL() throws IOException {

		throw new NotImplementedException("getURL() nicht supportet");
	}

	@Override
	public boolean isOpen() {

		return true;
	}

	@Override
	public boolean isReadable() {

		return true;
	}

	@Override
	public boolean isWritable() {

		return true;
	}

	@Override
	public long lastModified() throws IOException {

		return System.currentTimeMillis();
	}

	public void setMimeType(String mimeType) {

		this.mimeType = mimeType;
	}

	@Override
	public String toString() {

		return fileName;
	}

}

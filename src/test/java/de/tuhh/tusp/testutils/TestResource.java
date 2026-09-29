package de.tuhh.tusp.testutils;

import static org.mockito.Mockito.doAnswer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

public class TestResource implements Resource, WritableResource {

	private File file;
	private byte[] content;

	private ByteArrayOutputStream byteOutputStream;

	public TestResource() {

		file = Mockito.mock(File.class);

		byteOutputStream = new ByteArrayOutputStream();

		this.content = new byte[0];
	}

	public TestResource(byte[] content, String filename, long lastModifiedSecondsAgo) {

		file = Mockito.mock(File.class);

		doAnswer(i -> System.currentTimeMillis() - lastModifiedSecondsAgo * 1000L).when(file).lastModified();
		doAnswer(i -> filename).when(file).getName();
		doAnswer(i -> filename).when(file).getAbsolutePath();
		doAnswer(i -> file).when(file).getAbsoluteFile();

		this.content = content;
	}

	public TestResource(byte[] content, String filename, long lastModifiedSecondsAgo, File[] children) {

		file = Mockito.mock(File.class);

		doAnswer(i -> System.currentTimeMillis() - lastModifiedSecondsAgo * 1000L).when(file).lastModified();
		doAnswer(i -> filename).when(file).getName();
		doAnswer(i -> filename).when(file).getAbsolutePath();
		doAnswer(i -> file).when(file).getAbsoluteFile();
		doAnswer(i -> true).when(file).isDirectory();
		doAnswer(i -> true).when(file).canRead();
		doAnswer(i -> true).when(file).exists();
		doAnswer(i -> children).when(file).listFiles();

		this.content = content;
	}

	@Override
	public long contentLength() throws IOException {

		return content.length;
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {

		return this;
	}

	@Override
	public boolean exists() {

		return file.exists();
	}

	public byte[] getBytes() {

		return byteOutputStream.toByteArray();
	}

	@Override
	public String getDescription() {

		return "getDescription";
	}

	@Override
	public File getFile() throws IOException {

		return file;
	}

	@Override
	public String getFilename() {

		return file.getName();
	}

	@Override
	public InputStream getInputStream() throws IOException {

		return new ByteArrayInputStream(content);
	}

	@Override
	public OutputStream getOutputStream() throws IOException {

		return byteOutputStream;
	}

	@Override
	public URI getURI() throws IOException {

		try {
			return new URI("file://" + file.getAbsolutePath());
		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public URL getURL() throws IOException {

		return new URL("file://" + file.getAbsolutePath());
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

		return file.lastModified();
	}

	@Override
	public String toString() {

		return getClass().getName() + "(" + new String(content) + ")";
	}

}
package org.sooo.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

/**
 * File copy examples are from
 * <code><a href="http://stackoverflow.com/questions/5253324/how-to-use-the-inputsupplier-or-outputsupplier-api-of-guava">
 * stackoverflow</a></code>
 * 
 * @author sooo
 * 
 */
public class FileIOTest {

	String inFilePath = "src/test/resources/sample.txt";
	String outFilePath = "src/test/resources/sample_copy.txt";

	@Test
	public void inputSupplierForInputStreamReader() throws IOException {
		InputSupplier<InputStreamReader> input = Files.newReaderSupplier(
				new File(inFilePath), Charsets.UTF_8);
		System.out.println(CharStreams.toString(input));
	}

	@Test
	public void copyFileUsingGuava() throws IOException {
		File in = new File(inFilePath);
		File out = new File(outFilePath);
		assertThat(out.exists(), is(false));

		ByteStreams.copy(Files.newInputStreamSupplier(in),
				Files.newOutputStreamSupplier(out));

		assertThat(out.exists(), is(true));
		out.delete();
		assertThat(out.exists(), is(false));
	}

	@Test
	public void copyFileWithNaiveWay() throws IOException {
		File in = new File(inFilePath);
		File out = new File(outFilePath);
		assertThat(out.exists(), is(false));
		FileInputStream inStream = new FileInputStream(in);

		boolean threw = true;
		try {
			FileOutputStream outStream = new FileOutputStream(out);
			try {
				ByteStreams.copy(inStream, outStream);
				threw = false;
			} finally {
				Closeables.close(outStream, threw);
			}
		} finally {
			Closeables.close(inStream, threw);
		}

		assertThat(out.exists(), is(true));
		out.delete();
		assertThat(out.exists(), is(false));
	}

	@Test
	public void getFileExtension() {
		assertThat(Files.getFileExtension(inFilePath), is("txt"));
	}

	@Test
	public void simplifyPath() {
		assertThat(Files.simplifyPath(inFilePath + "/"), is(inFilePath));
	}
}

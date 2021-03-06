package other;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TestIO {
	private static int DISK_BLOCK_SIZE = 4096;
	private static int NUMBER_OF_INTS = 5000;

	public static void main(final String[] args) throws IOException {
		final File binaryFile = new File("input.dat");
		final File textFile = new File("input.txt");

		if (binaryFile.exists()) {
			binaryFile.delete();
		}
		binaryFile.createNewFile();
		if (textFile.exists()) {
			textFile.delete();
		}
		textFile.createNewFile();

		// here is the binary format file creation
		final FileOutputStream fos = new FileOutputStream(binaryFile);
		final BufferedOutputStream bos = new BufferedOutputStream(fos, DISK_BLOCK_SIZE);
		final DataOutputStream dos = new DataOutputStream(bos);

		// here is the text format file creation
		final FileWriter fw = new FileWriter(textFile);
		final BufferedWriter bfw = new BufferedWriter(fw, DISK_BLOCK_SIZE);

		final Random rnd = new Random(System.currentTimeMillis());
		int r;
		for (int i = 0; i < NUMBER_OF_INTS; i++) {
			r = Math.abs(rnd.nextInt());
			dos.write(r);
			bfw.write(Integer.toString(r));
			bfw.write(", ");
		}
		dos.close();
		bos.close();
		fos.close();
		bfw.close();
		fw.close();

		// read them back in
		final FileInputStream fis = new FileInputStream(binaryFile);
		final BufferedInputStream bis = new BufferedInputStream(fis, DISK_BLOCK_SIZE);
		final DataInputStream dis = new DataInputStream(bis);

		final long brStart = System.currentTimeMillis();

		while (dis.available() != 0) {
			dis.readInt();
		}

		System.out.printf("Read binary file in %04d ms\n", System.currentTimeMillis() - brStart);

		dis.close();
		bis.close();
		fis.close();

		final FileReader fr = new FileReader(textFile);
		final BufferedReader bfr = new BufferedReader(fr, DISK_BLOCK_SIZE);
		final Scanner scanner = new Scanner(bfr);
		scanner.useDelimiter(", ");

		final long trStart = System.currentTimeMillis();

		while (scanner.hasNext()) {
			scanner.nextInt();
		}

		System.out.printf("Read text file in   %04d ms\n", System.currentTimeMillis() - trStart);

		scanner.close();
		bfr.close();
		fr.close();
	}
}
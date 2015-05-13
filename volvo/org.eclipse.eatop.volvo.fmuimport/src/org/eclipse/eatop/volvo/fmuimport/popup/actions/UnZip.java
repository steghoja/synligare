package org.eclipse.eatop.volvo.fmuimport.popup.actions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
public class UnZip
{
/**
 * 	
 * @param zipArchiveFilename NAme of the zip archive.
 * @param targetFilename     Name of the file in the zip archive that shall be extracted.
 * @param extractedFilename  Name of the extracted file on disk. 
 * @throws FileNotFoundException
 * @throws IOException
 */
	public void UnZipFile (String zipArchiveFilename, String targetFilename, String extractedFilename) throws FileNotFoundException, IOException {
	
		OutputStream out = new FileOutputStream(extractedFilename);
		FileInputStream fin = new FileInputStream(zipArchiveFilename);
		BufferedInputStream bin = new BufferedInputStream(fin);
		ZipInputStream zin = new ZipInputStream(bin);
		ZipEntry ze = null;
		while ((ze = zin.getNextEntry()) != null) {
		    if (ze.getName().equals(targetFilename)) {
		        byte[] buffer = new byte[8192];
		        int len;
		        while ((len = zin.read(buffer)) != -1) {
		            out.write(buffer, 0, len);
		        }
		        out.close();
		        zin.close();
		        break;
		    }
		}
	}
}
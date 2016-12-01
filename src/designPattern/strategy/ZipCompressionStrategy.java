/**
 * 
 */
package designPattern.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author yan96in
 *
 */
public class ZipCompressionStrategy implements CompressionStrategy{
	
	@Override
	public OutputStream compress(OutputStream data) throws IOException {
		return new ZipOutputStream(data);
	}

}

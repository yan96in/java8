/**
 * 
 */
package designPattern.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author yan96in
 *
 */
public class GzipCompressionStrategy implements CompressionStrategy{
	
	@Override
	public OutputStream compress(OutputStream data) throws IOException {
		return new GZIPOutputStream(data);
	}

}

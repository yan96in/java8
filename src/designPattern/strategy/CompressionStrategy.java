/**
 * 
 */
package designPattern.strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yan96in
 *
 */
public interface CompressionStrategy {
	public OutputStream compress(OutputStream data) throws IOException;
}

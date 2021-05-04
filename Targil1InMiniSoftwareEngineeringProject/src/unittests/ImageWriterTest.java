/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;
import renderer.ImageWriter;

/**
 * writeToImage tests 
 * @author yosefHaim
 *
 */
public class ImageWriterTest {

	/**
	 * base picture test 
	 * Test method for 
	 * {@link renderer.ImageWriter#ImageWriter(java.lang.String, int, int)}.
	 */
	@Test
	public void writeToImage() {
		ImageWriter imageWriter = new ImageWriter("My picture", 500, 800);
		int Nx = imageWriter.getNx();
		int Ny = imageWriter.getNy();
		for (int i = 0; i < Ny; i++) {
			for (int j = 0; j < Nx; j++) {
				if (i % 50 == 0 || j % 50 == 0) {
					imageWriter.writePixel(j, i, new Color(100,100,100));
				} else {
					imageWriter.writePixel(j, i, Color.BLACK);
				}
			}
		}
		imageWriter.writeToImage();
	}

}

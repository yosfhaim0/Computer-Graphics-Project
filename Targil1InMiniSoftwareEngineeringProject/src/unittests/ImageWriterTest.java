/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author yosefHaim
 *
 */
public class ImageWriterTest {

	/**
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
					imageWriter.writePixel(j, i, Color.BLACK);
				} else {
					imageWriter.writePixel(j, i, Color.BLACK);
				}
			}
		}
		imageWriter.writeToImage();
	}

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	public void testWriteToImage() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link renderer.ImageWriter#writePixel(int, int, primitives.Color)}.
	 */
	@Test
	public void testWritePixel() {
		fail("Not yet implemented");
	}

}

package certification;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class CertificationManager {
	private String base64EncodedCertification_ = "";

	private String getFileExtendsion(String filePath) {
		String extendsion = "";
		for (int i = filePath.length() - 1; i >= 0; i--) {
			if (filePath.substring(i, i + 1).equals(".")) {
				extendsion = filePath.substring(i + 1, filePath.length());
				break;
			}
		}
		return extendsion;
	}

	private void encodeImageToString(Image image, String extendsion) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write((RenderedImage) image, extendsion, outputStream);
			Base64.Encoder base64Encoder = Base64.getEncoder();
			base64EncodedCertification_ = base64Encoder.encodeToString(outputStream.toByteArray());
		} catch (IOException e) {
			// throw IOException becuase of outputStream went wrong during
			// writing image inside
		}
	}

	private void setCertificationText(Image img, TemplateCertification template, Certification certification) {
		Graphics2D graphics = (Graphics2D) img.getGraphics();
		graphics.setPaint(Color.black);
		graphics.setFont(new Font("標楷體", Font.PLAIN, template.getIdTextSize()));
		graphics.drawString(certification.getId(), template.getIdLocation().x, template.getIdLocation().y);
		graphics.setFont(new Font("標楷體", Font.PLAIN, template.getOwnerTextSize()));
		graphics.drawString(certification.getOwner(), template.getOwnerLocation().x, template.getOwnerLocation().y);
		graphics.dispose();
	}

	public CertificationManager() {
	}

	public String getCertificationJsonString() {
		return base64EncodedCertification_;
	}

	public void makeCertification(TemplateCertification template, Certification certification, String filePath) {
		try {
			Image image = ImageIO.read(new File(filePath));
			setCertificationText(image, template, certification);
			encodeImageToString(image, getFileExtendsion(filePath));
		} catch (IOException e1) {
			// throw IOException becuase of Image went wrong during reading File
		}
	}
}

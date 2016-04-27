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
	
	private void setCourceName(Graphics2D graphics, TemplateCertification template,Certification certification)
	{
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getCourceNameTextSize()));
		int breakIndex=certification.getCourceName().indexOf("：");
		if(breakIndex==-1)
		{
			graphics.drawString(certification.getCourceName(), template.getCourceNameLocation1().x, template.getCourceNameLocation1().y);
		}
		else
		{
			String forward=certification.getCourceName().substring(0, breakIndex+1);
			String backward=certification.getCourceName().substring(breakIndex+1, certification.getCourceName().length());
			graphics.drawString(forward, template.getCourceNameLocation1().x, template.getCourceNameLocation1().y);
			graphics.drawString(backward, template.getCourceNameLocation2().x, template.getCourceNameLocation2().y);
		}
	}

	private void setCertificationText(Image img, Certification certification) {
		Graphics2D graphics = (Graphics2D) img.getGraphics();
		TemplateCertificationMaker templateCertificationMaker = new TemplateCertificationMaker();
		TemplateCertification template = templateCertificationMaker.MakeTemplateCertification(img, certification);

		graphics.setPaint(Color.black);
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getIdTextSize()));
		graphics.drawString(certification.getId(), template.getIdLocation().x, template.getIdLocation().y);
		
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getOwnerTextSize()));
		graphics.drawString(certification.getOwner(), template.getOwnerLocation().x, template.getOwnerLocation().y);
		
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getCourceDateTextSize()));
		graphics.drawString(certification.getCourceDate(), template.getCourceDateLocation().x, template.getCourceDateLocation().y);
		
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getParticipateSize()));
		graphics.drawString("參加", template.getParticipateLocation().x, template.getParticipateLocation().y);

		setCourceName(graphics,template,certification);
		
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getCourceDurationSize()));
		graphics.drawString(certification.getCourceDuration(), template.getCourceDurationLocation().x, template.getCourceDurationLocation().y);
		
		graphics.setPaint(Color.red);
		graphics.setFont(new Font("DFKai-SB", Font.PLAIN, template.getDateTextSize()));
		graphics.drawString(certification.getDate(), template.getDateLocation().x, template.getDateLocation().y);
		
		graphics.dispose();
	}

	public CertificationManager() {
	}

	public String getCertificationJsonString() {
		return base64EncodedCertification_;
	}

	public void makeCertification(Certification certification, String filePath) {
		try {
			Image image = ImageIO.read(new File(filePath));
			setCertificationText(image, certification);
			encodeImageToString(image, getFileExtendsion(filePath));
		} catch (IOException e1) {
			// throw IOException becuase of Image went wrong during reading File
		}
	}
}

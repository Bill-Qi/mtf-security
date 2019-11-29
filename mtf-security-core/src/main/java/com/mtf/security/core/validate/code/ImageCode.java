package com.mtf.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author Bill
 * @date 2019年11月28日
 *
 */
public class ImageCode extends ValidateCode {
	
	private BufferedImage image;

//	private String code;
//
//	private LocalDateTime expireTime;

	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}

//	public boolean isExpried() {
//		return LocalDateTime.now().isAfter(expireTime);
//	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}

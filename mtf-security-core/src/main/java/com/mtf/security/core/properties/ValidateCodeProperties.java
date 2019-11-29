package com.mtf.security.core.properties;
/** 
* @author Bill
* @date 2019年11月28日
*
*/
public class ValidateCodeProperties {

	//默认配置
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
	
}

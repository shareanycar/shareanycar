package com.shareanycar.dao;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Image;

@Service
public class ImageDao extends BasicDao<Image>{

	public ImageDao() {
		super("Image");
	}

}

package com.shareanycar.dao;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.TransmissionType;

@Service
public class TransmissionTypeDao extends BasicDao<TransmissionType> {

	@Inject
	public ExtDao<?> extDao;

	public TransmissionTypeDao() {
		super("TransmissionType");
	}

	public TransmissionType findByName(String name) {
		return (TransmissionType) extDao.findOneByParam("from TransmissionType where name = :name", "name", name);
	}
}

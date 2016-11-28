package doordu.com.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doordu.com.dao.IDoorduLogDao;
import doordu.com.entity.DoorduLogEntity;
import doordu.com.svc.IDoorduLogSvc;

@Service
public class DoorduLogSvcImpl implements IDoorduLogSvc {
	@Autowired
	private IDoorduLogDao doorduLogDao;

	@Override
	@Transactional
	public int insertList(List<DoorduLogEntity> records) {
		return doorduLogDao.insertList(records);
	}

	@Override
	public int insert(DoorduLogEntity record) {
		return doorduLogDao.insert(record);
	}
	
}

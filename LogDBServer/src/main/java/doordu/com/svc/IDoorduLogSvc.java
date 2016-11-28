package doordu.com.svc;

import java.util.List;

import doordu.com.entity.DoorduLogEntity;

public interface IDoorduLogSvc {
	public int insertList(List<DoorduLogEntity> records);
	
	public int insert(DoorduLogEntity record);
}

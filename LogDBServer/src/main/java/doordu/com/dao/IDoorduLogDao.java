package doordu.com.dao;

import java.util.List;

import doordu.com.entity.DoorduLogEntity;

public interface IDoorduLogDao {
    int deleteByPrimaryKey(Integer logId);

    int insert(DoorduLogEntity record);

    int insertSelective(DoorduLogEntity record);
    
    int insertList(List<DoorduLogEntity> records);

    DoorduLogEntity selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(DoorduLogEntity record);

    int updateByPrimaryKeyWithBLOBs(DoorduLogEntity record);

    int updateByPrimaryKey(DoorduLogEntity record);
}
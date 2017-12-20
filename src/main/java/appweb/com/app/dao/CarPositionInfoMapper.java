package appweb.com.app.dao;

import appweb.com.app.entity.CarPositionInfo;

public interface CarPositionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarPositionInfo record);

    int insertSelective(CarPositionInfo record);

    CarPositionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarPositionInfo record);

    int updateByPrimaryKey(CarPositionInfo record);
}
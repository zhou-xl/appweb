package appweb.com.app.dao;

import appweb.com.app.entity.CarInAndOutInfo;

public interface CarInAndOutInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CarInAndOutInfo record);

    int insertSelective(CarInAndOutInfo record);

    CarInAndOutInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarInAndOutInfo record);

    int updateByPrimaryKey(CarInAndOutInfo record);
    
    public CarInAndOutInfo getCarInAndOutInfoById(Integer id);
}
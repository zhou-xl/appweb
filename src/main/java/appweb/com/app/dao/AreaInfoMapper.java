package appweb.com.app.dao;

import appweb.com.app.entity.AreaInfo;

public interface AreaInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaInfo record);

    int insertSelective(AreaInfo record);

    AreaInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaInfo record);

    int updateByPrimaryKey(AreaInfo record);

	public AreaInfo selectByAreaId(String areaId);
}
package appweb.com.app.quartz;


import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import appweb.com.app.common.AppUtils;
import appweb.com.app.common.Constant;
import appweb.com.app.dao.CarInAndOutInfoMapper;
import appweb.com.app.dao.CarPositionInfoMapper;
import appweb.com.app.entity.CarPositionInfo;
import appweb.com.app.entity.ReturnBean;

public class JobTset {

	
	@Resource
	private CarPositionInfoMapper carPositionInfoMapper;
	@Resource  
    private CarInAndOutInfoMapper carInAndOutInfoMapper;
	
	public void work() {
		vLastLocationV3(new CarPositionInfo());
	}
	
	
	/**
	 * 获取车辆位置
	 * @author zxl
	 * @date 2017年12月29日 上午11:02:52
	 * @param positionInfo
	 */
	public void vLastLocationV3(CarPositionInfo positionInfo) {
		positionInfo.setVcln("陕YH0009");
		String vcln = positionInfo.getVcln();
		ReturnBean bean = AppUtils.vLastLocationV3(positionInfo);
		if (bean != null && bean.getStatus().equals(Constant.status_success)) {
			positionInfo = JSON.parseObject(bean.getResult().toString() ,CarPositionInfo.class);
			positionInfo.setLat(AppUtils.toLatOrLon(positionInfo.getLat()));
			positionInfo.setLon(AppUtils.toLatOrLon(positionInfo.getLon()));
			positionInfo.setUtc(AppUtils.toDate(positionInfo.getUtc()));
			positionInfo.setVcln(vcln);
			carPositionInfoMapper.insertSelective(positionInfo);
		}else if (bean.getStatus().equals("1016")) {
			login();
		}
	}
	
	/**
	 * 登录
	 * @author zxl
	 * @date 2017年12月29日 上午11:02:44
	 */
	public void login() {
		ReturnBean bean = AppUtils.login();
		if (bean.getStatus().equals(Constant.status_success)) {
			Constant.token = (String) bean.getResult();
		}
	}
	
}

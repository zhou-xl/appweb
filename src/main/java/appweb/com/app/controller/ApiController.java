package appweb.com.app.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import appweb.com.app.common.Constant;
import appweb.com.app.common.AppUtils;
import appweb.com.app.dao.AreaInfoMapper;
import appweb.com.app.dao.CarInAndOutInfoMapper;
import appweb.com.app.entity.AreaInfo;
import appweb.com.app.entity.CarInAndOutInfo;
import appweb.com.app.entity.ReturnBean;

@RequestMapping("/apis")
@Controller
public class ApiController {

	private static Logger logger = Logger.getLogger(ApiController.class);  
	
	@Resource  
    private CarInAndOutInfoMapper carInAndOutInfoMapper;
	@Resource
	private AreaInfoMapper areaInfoMapper;
	
	@ResponseBody
	@RequestMapping("/test")
	public String test(){
		CarInAndOutInfo carInAndOutInfo = carInAndOutInfoMapper.getCarInAndOutInfoById(1);
    	// System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(carInAndOutInfo));
        
        return JSON.toJSONString(carInAndOutInfo);
	}
	
	/**
	 * 回调接口
	 * @author zxl
	 * @date 2017年12月20日 下午12:34:27
	 * @param receiveInfo
	 * @param userFlag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/areamsg")
	public String areamsg(CarInAndOutInfo receiveInfo ,String userFlag) {
		
		StringBuffer result = new StringBuffer();
		result.append("{");
		
		Date date = new Date();
		
		Long time = date.getTime();
		
		result.append("result:" + time.toString());
		
		int status = 1006;
		try {
			if (userFlag != null && userFlag.equals(Constant.apiUser)) {
				
				status = 1001;
				receiveInfo.setCreateTime(date);
				AreaInfo areaInfo = areaInfoMapper.selectByAreaId(receiveInfo.getAreaId());
				receiveInfo.setAreaName(areaInfo.getAreaName());
				carInAndOutInfoMapper.insert(receiveInfo);
			
			}
		} catch (Exception e) {
			status = 9001;
		}
		result.append(",status:" + status + "}");
		
		return result.toString();
	}
	
	
	/**
	 * 车辆驶入驶出 区域订阅
	 * @author zxl
	 * @date 2017年12月20日 下午1:58:47
	 * @param area
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/areaReg")
	@ResponseBody
	public String areaReg(AreaInfo area) {
		
		ReturnBean bean =  AppUtils.areaReg(area);
		logger.info(JSON.toJSONString(bean));
		if (bean.getStatus().equals(Constant.status_success)) {
			Map<String, String> map = (Map<String, String>) bean.getResult();
			if (map.get("state").equals("1")) {
				area.setAreaId(map.get("areaid"));
				areaInfoMapper.insert(area);
			}
		}
		return AppUtils.analysisStatus(bean);
	}
	
	/**
	 * 车辆驶入驶出 车辆订阅
	 * @author zxl
	 * @date 2017年12月20日 下午2:27:19
	 * @param area
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/vnoReg")
	public String vnoReg(AreaInfo area) {
		
		ReturnBean bean = AppUtils.vnoReg(area);
		if (bean.getStatus().equals(Constant.status_success)) {
			if (bean.getResult().equals("1")) {
				String vnos = area.getVnos();
				area = areaInfoMapper.selectByAreaId(area.getAreaId());
				area.setVnos(area.getVnos() + "," + vnos);
				areaInfoMapper.updateByPrimaryKeySelective(area);
			}
		}
		return AppUtils.analysisStatus(bean);
	}
	
	
	/**
	 * 登陆
	 * @author zxl
	 * @date 2017年12月20日 下午2:33:37
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public String login() {
		
		ReturnBean bean = AppUtils.login();
		if (bean.getStatus().equals(Constant.status_success)) {
			Constant.token = (String) bean.getResult();
		}
		return AppUtils.analysisStatus(bean);
	}
}

package com.company.crm.workbench.service;

import com.company.crm.settings.domain.User;
import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.vo.ActivityVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author conrad
 * @date 2021/06/28
 */
public interface ActivityService {
    //保存创建的市场活动
    int saveActivity(Activity activity);

    //查询市场活动列表按多条件和分页
    List<Activity> selectActivityForPageByCondition(Map<String, Object> condition);

    long selectActivityCountByCondition(Map<String, Object> condition);

    //根据id去查询市场活动(用于编辑）
    Activity selectActivityById(String id);

    int updateActivity(Activity activity);

    int deleteActivityByIds(String[] ids);

    //导出时要抓取市场活动表中所有数据
    List<Activity> selectActivityForDetailByIds(String[] ids);

    //导入将excel中的多个市场活动导入到数据库市场活动表
    int saveActivityByList(List<Activity> activityList);

    Activity selectActivityForDetailById(String id);

    //在其它模块中需要市场模块的支持
    List<Activity> selectAllActivityForDetail();

    //根据市场活动名查询所有的市场活动
    List<Activity> selectActivityForDetailByName(String name);

    List<ActivityVo> selectAllActivityForExcel(String[] ids);

    List<Activity> parseExcel(User user, MultipartFile activityFile);

    HSSFWorkbook exportExcel(List<ActivityVo> activityList);

    <T> HSSFWorkbook exportExcels(List<T> activityList);

    List<Activity> selectActivityForDetailByClueId(String clueId);

    List<Activity> selectActivityNoBoundById(Map<String, Object> map);

    List<Activity> selectActivityHasBoundById(Map<String, Object> map);
}

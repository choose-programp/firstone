package com.company.crm.workbench.mapper;

import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.vo.ActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author conrad
 * @date 2021/06/28
 */
public interface ActivityMapper {

    //保存创建的市场活动
    int insertctivity(Activity activity);

    //查询市场活动列表按多条件和分页
    List<Activity> selectActivityForPageByCondition(Map<String, Object> condition);

    long selectActivityCountByCondition(Map<String, Object> condition);

    //根据id去查询市场活动(用于编辑）
    Activity selectActivityById(String id);

    int updateActivity(Activity activity);

    int deleteActivityByIds(String[] ids);

    //导出时要抓取市场活动表中所有数据
    List<Activity> selectActivityForDetailByIds(@Param("ids") String[] ids);

    //导入将excel中的多个市场活动导入到数据库市场活动表
    int insertActivityByList(List<Activity> activityList);

    Activity selectActivityForDetailById(String id);

    List<ActivityVo> selectAllActivityForExcel(@Param("ids") String[] ids);

    List<Activity> selectAllActivityForDetail();

    //根据市场活动名查询所有的市场活动
    List<Activity> selectActivityForDetailByName(String name);

    List<Activity> selectActivityForDetailByClueId(String clueId);

    List<Activity> selectActivityHasBoundById(Map<String, Object> map);

    List<Activity> selectActivityNoBoundById(Map<String, Object> map);

}

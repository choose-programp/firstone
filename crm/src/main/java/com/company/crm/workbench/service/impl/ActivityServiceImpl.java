package com.company.crm.workbench.service.impl;

import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.settings.domain.User;
import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.dto.DownActivity;
import com.company.crm.workbench.mapper.ActivityMapper;
import com.company.crm.workbench.service.ActivityService;
import com.company.crm.workbench.vo.ActivityVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author conrad
 * @date 2021/06/28
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveActivity(Activity activity) {
        return activityMapper.insertctivity(activity);
    }

    @Override
    public List<Activity> selectActivityForPageByCondition(Map<String, Object> condition) {
        return activityMapper.selectActivityForPageByCondition(condition);
    }

    @Override
    public long selectActivityCountByCondition(Map<String, Object> condition) {
        return activityMapper.selectActivityCountByCondition(condition);
    }

    @Override
    public Activity selectActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public List<Activity> selectActivityForDetailByIds(String[] ids) {
        return activityMapper.selectActivityForDetailByIds(ids);
    }

    @Override
    public int saveActivityByList(List<Activity> activityList) {
        return activityMapper.insertActivityByList(activityList);
    }

    @Override
    public Activity selectActivityForDetailById(String id) {
        return activityMapper.selectActivityForDetailById(id);
    }

    @Override
    public List<Activity> selectAllActivityForDetail() {
        return activityMapper.selectAllActivityForDetail();
    }

    @Override
    public List<Activity> selectActivityForDetailByName(String name) {
        return activityMapper.selectActivityForDetailByName(name);
    }

    @Override
    public List<ActivityVo> selectAllActivityForExcel(String[] ids) {
        return activityMapper.selectAllActivityForExcel(ids);
    }

    @Override
    public List<Activity> parseExcel(User user, MultipartFile activityFile) {
        List<Activity> list = new ArrayList<>();
        HSSFRow row;
        try {
            InputStream inputStream = activityFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                row = sheet.getRow(i);
                Activity activity = new Activity();
                DownActivity downActivity = new DownActivity();
                setActivityValue(downActivity, row);
                initDownActivity(downActivity, user);
                BeanUtils.copyProperties(downActivity, activity);
                activity.setId(UUIDUtils.getUUID());
                list.add(activity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void initDownActivity(DownActivity activity, User user) {
        activity.setOwner(user.getId());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());
        activity.setEditTime("");
        activity.setEditBy("");
    }

    @Override
    public HSSFWorkbook exportExcel(List<ActivityVo> activityList) {
        String[] headNames = new String[]{"所有者", "名称", "开始日期", "结束日期", "预算", "描述", "创建时间", "创建者", "编辑时间", "编辑者"};
        List<List<Object>> data = new ArrayList<>();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFRow row;
        HSSFCell cell;
        HSSFSheet sheet = wb.createSheet("市场活动");
        row = sheet.createRow(0);
        // 设置表头字体样式
        HSSFFont columnHeadFont = wb.createFont();
        // 列头的样式
        HSSFCellStyle columnHeadStyle = wb.createCellStyle();
        for (int i = 0; i < headNames.length; i++) {
            // 设置表头字体样式
            setHeadStyle(columnHeadFont, columnHeadStyle);
            cell = row.createCell(i);
            cell.setCellValue(headNames[i]);
        }
        //每行列数
        int cellNum = row.getLastCellNum();
        for (ActivityVo activityVo : activityList) {
            data.add(convertBean(activityVo));
        }
        for (int i = 0; i < data.size(); i++) {
            List<Object> list = data.get(i);
            row = sheet.createRow(i + 1);
            for (int j = 0; j < cellNum; j++) {
                cell = row.createCell(j);
                cell.setCellValue(list.get(j) + "");
            }
        }
        return wb;
    }

    private void setHeadStyle(HSSFFont columnHeadFont, HSSFCellStyle columnHeadStyle) {
        //设置标题的格式
        columnHeadFont.setFontName("宋体");
        // 字体大小
        columnHeadFont.setFontHeightInPoints((short) 10);
        // 加粗
        columnHeadFont.setBold(true);
        // 列头的样式
        //单元格标题、居中、字体加粗，黑丝边框样式
        columnHeadStyle.setFont(columnHeadFont);
        // 左右居中
        columnHeadStyle.setAlignment(HorizontalAlignment.CENTER);
        // 上下居中
        columnHeadStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
    }


    @Override
    public <T> HSSFWorkbook exportExcels(List<T> activityList) {
        String[] headNames = new String[]{"所有者", "名称", "开始日期", "结束日期", "预算", "描述", "创建时间", "创建者", "编辑时间", "编辑者"};
        List<List<Object>> data = new ArrayList<>();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFRow row;
        HSSFCell cell;
        HSSFSheet sheet = wb.createSheet("市场活动");
        row = sheet.createRow(0);
        for (int i = 0; i < headNames.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headNames[i]);
        }
        //每行列数
        int cellNum = row.getLastCellNum();
        for (T t : activityList) {
            data.add(convertBean(t));
        }
        for (int i = 0; i < data.size(); i++) {
            List<Object> list = data.get(i);
            row = sheet.createRow(i + 1);
            for (int j = 0; j < cellNum; j++) {
                cell = row.createCell(j);
                cell.setCellValue(list.get(j) + "");
            }
        }
        return wb;
    }

    @Override
    public List<Activity> selectActivityForDetailByClueId(String clueId) {
        return activityMapper.selectActivityForDetailByClueId(clueId);
    }

    @Override
    public List<Activity> selectActivityNoBoundById(Map<String, Object> map) {
        return activityMapper.selectActivityNoBoundById(map);
    }

    @Override
    public List<Activity> selectActivityHasBoundById(Map<String, Object> map) {
        return activityMapper.selectActivityHasBoundById(map);
    }

    public <T> List<Object> convertBean(T bean) {
        Class<?> type = bean.getClass();
        List<Object> list = new ArrayList<>();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            PropertyDescriptor propertyDescriptor;
            try {
                propertyDescriptor = new PropertyDescriptor(name, type);
                Method readMethod = propertyDescriptor.getReadMethod();
                Object obj = readMethod.invoke(bean);
                list.add(Objects.requireNonNullElse(obj, ""));
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void setActivityValue(Object bean, HSSFRow row) {
        Class<?> type = bean.getClass();
        Field[] fields = type.getDeclaredFields();
        short lastCellNum = row.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            HSSFCell cell = row.getCell(i);
            String fieldName = fields[i].getName();
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(bean, cell.getStringCellValue());
            } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object value;
        switch (cellType) {
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                value = cell.getStringCellValue();
        }
        return value;
    }

}

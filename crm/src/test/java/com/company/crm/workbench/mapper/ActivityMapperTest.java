package com.company.crm.workbench.mapper;

import basetest.MyBaseTest;
import com.company.crm.commons.utils.ConvertBean;
import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.dto.SearchActivity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityMapperTest extends MyBaseTest {

    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void selectActivityForPageByCondition() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", 0);
        map.put("pageSize", 10);
        map.put("name", "xx");
        map.put("owner", "");
        map.put("startDate", "2021-09-01");
        map.put("endDate", "2021-10-01");
        List<Activity> activityList = activityMapper.selectActivityForPageByCondition(map);
        System.out.println(activityList.size());
    }

    @Test
    public void testSelectActivityCountByCondition() throws IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Map<String, Object> map = new HashMap<>();
//        map.put("pageNo", 0);
//        map.put("pageSize", 10);
////        map.put("name", "xx");
////        map.put("owner", "李");
//        map.put("startDate", "2021-09-01");
//        map.put("endDate", "2021-10-01");
        long c = activityMapper.selectActivityCountByCondition(map);
        System.out.println(c);

        SearchActivity searchActivity = new SearchActivity();
        searchActivity.setPageNo(10);
        searchActivity.setName("李四");
        searchActivity.setOwner("xxcfh256");
        Map<String, Object> map1 = ConvertBean.convertBeanToMap(searchActivity);
        map1.forEach((k, v) -> System.out.println(k + "::" + v));

        SearchActivity searchActivity1 = new SearchActivity();
        ConvertBean.convertMapToBean(map1, searchActivity1);
        System.out.println(searchActivity1.getEndDate());
        System.out.println(new SearchActivity());
    }


    @Test
    public void selectActivityForDetailByName() {
        List<Activity> list = activityMapper.selectActivityForDetailByName("xx");
        System.out.println(list.size());
    }

    @Test
    public void selectActivityForDetailByIds() {
        List<Activity> list = activityMapper.selectActivityForDetailByIds(new String[]{"2"});
        System.out.println(list.size());
    }
}
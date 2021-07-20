package com.company.crm.settings.service;

import com.company.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author conrad
 * @date 2021/06/22
 */
public interface UserService {

    User selectUserByLoginActAndPwd(Map<String, Object> map);

    List<User> selectAllUsers();

}

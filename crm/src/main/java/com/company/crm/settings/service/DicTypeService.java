package com.company.crm.settings.service;

import com.company.crm.settings.domain.DicType;

import java.util.List;

/**
 * @author conrad
 * @date 2021/06/27
 */
public interface DicTypeService {


    List<DicType> selectAllDicTypes();

    DicType selectDicTypeByCode(String code);

    int saveDicType(DicType dicType);

    int deleteDicTypeByCodes(String[] code);

    int updateDicType(DicType dicType);
}

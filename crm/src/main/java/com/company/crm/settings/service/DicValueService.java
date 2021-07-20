package com.company.crm.settings.service;

import com.company.crm.settings.domain.DicValue;

import java.util.List;

/**
 * @author conrad
 * @date 2021/06/27
 */
public interface DicValueService {
    List<DicValue> selectAllDicValues();

    int saveDicValue(DicValue dicValue);

    int deleteDicValueByIds(String[] ids);

    int updateDicValue(DicValue dicValue);

    int deleteDicValueByTypeCodes(String[] typeCodes);

    List<DicValue> selectDicValueByTypeCode(String typeCode);

    List<DicValue> selectDicValueByTypeCodes(String[] typeCodes);

    DicValue selectDicValueById(String id);
}

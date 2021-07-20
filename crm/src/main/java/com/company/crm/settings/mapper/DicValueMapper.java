package com.company.crm.settings.mapper;

import com.company.crm.settings.domain.DicValue;

import java.util.List;

/**
 * @author conrad
 * @date 2021/06/27
 */
public interface DicValueMapper {

   List<DicValue> selectAllDicValues();

   int saveDicValue(DicValue dicValue);

   int deleteDicValueByIds(String[] ids);

   int updateDicValue(DicValue dicValue);

   int deleteDicValueByTypeCodes(String[] typeCodes);

   List<DicValue> selectDicValueByTypeCodes(String[] typeCodes);

   List<DicValue> selectDicValueByTypeCode(String typeCode);

   DicValue selectDicValueById(String id);
}

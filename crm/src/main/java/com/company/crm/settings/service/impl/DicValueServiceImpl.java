package com.company.crm.settings.service.impl;

import com.company.crm.settings.domain.DicValue;
import com.company.crm.settings.mapper.DicValueMapper;
import com.company.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> selectAllDicValues() {
        return dicValueMapper.selectAllDicValues();
    }

    @Override
    public int saveDicValue(DicValue dicValue) {
        return dicValueMapper.saveDicValue(dicValue);
    }

    @Override
    public int deleteDicValueByIds(String[] ids) {
        return dicValueMapper.deleteDicValueByIds(ids);
    }

    @Override
    public int updateDicValue(DicValue dicValue) {
        return dicValueMapper.updateDicValue(dicValue);
    }

    @Override
    public int deleteDicValueByTypeCodes(String[] typeCodes) {
        return dicValueMapper.deleteDicValueByTypeCodes(typeCodes);
    }

    @Override
    public List<DicValue> selectDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }

    @Override
    public List<DicValue> selectDicValueByTypeCodes(String[] typeCodes) {
        return dicValueMapper.selectDicValueByTypeCodes(typeCodes);
    }

    @Override
    public DicValue selectDicValueById(String id) {
        return dicValueMapper.selectDicValueById(id);
    }
}

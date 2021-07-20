package com.company.crm.settings.service.impl;

import com.company.crm.settings.domain.DicType;
import com.company.crm.settings.domain.DicValue;
import com.company.crm.settings.mapper.DicTypeMapper;
import com.company.crm.settings.service.DicTypeService;
import com.company.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Autowired
    private DicValueService dicValueService;

    @Override
    public List<DicType> selectAllDicTypes() {
        return dicTypeMapper.selectAllDicTypes();
    }

    @Override
    public DicType selectDicTypeByCode(String code) {
        return dicTypeMapper.selectDicTypeByCode(code);
    }

    @Override
    public int saveDicType(DicType dicType) {
        return dicTypeMapper.saveDicType(dicType);
    }

    @Override
    public int deleteDicTypeByCodes(String[] code) {
        List<DicType> dicTypeList = dicTypeMapper.selectDicTypesByCodes(code);
        List<String> typeCodes = new ArrayList<>();
        dicTypeList.forEach(dicType -> typeCodes.add(dicType.getCode()));
        String[] codes = new String[typeCodes.size()];
        codes = typeCodes.toArray(codes);
        List<DicValue> dicValueList = dicValueService.selectDicValueByTypeCodes(codes);
        if (!CollectionUtils.isEmpty(dicValueList)) {
            int i = dicValueService.deleteDicValueByTypeCodes(codes);
            int j = dicTypeMapper.deleteDicTypeByCodes(code);
            return i & j;
        } else {
            return dicTypeMapper.deleteDicTypeByCodes(code);
        }
    }

    @Override
    public int updateDicType(DicType dicType) {
        return dicTypeMapper.updateDicType(dicType);
    }
}

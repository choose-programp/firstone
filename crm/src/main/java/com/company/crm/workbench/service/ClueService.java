package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.Clue;
import com.company.crm.workbench.dto.ConvertClue;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/03
 */
public interface ClueService {
    /**
     * 保存创建的线索
     */
    int saveClue(Clue clue);

    /**
     * 根据id查询线索明细信息
     */
    Clue selectClueForDetailById(String id);

    List<Clue> selectAllClue();

    /**
     * 根据id查询线索信息
     */
    Clue selectClueById(String id);

    /**
     * 根据id删除线索
     */
    int deleteClueById(String id);


    int saveConvertClue(ConvertClue convertClue);
}

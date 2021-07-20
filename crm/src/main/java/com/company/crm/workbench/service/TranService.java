package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.Tran;
import com.company.crm.workbench.dto.CreateTranDto;
import com.company.crm.workbench.vo.FunnelVO;

import java.io.IOException;
import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
public interface TranService {
    /**
     *保存创建交易
     */
    int saveTran(Tran tran);

    /**
     * 根据id查询交易明细信息
     */
    Tran selectTranForDetailById(String id);

    /**
     * 查询交易表中各个阶段的数据量
     */
    List<FunnelVO> selectCountOfTranGroupByStage();


    List<Tran> selectAllTranForDetail();

    String readProperties(String key) throws IOException;

    int saveTran(CreateTranDto createTranDto, String userId);
}

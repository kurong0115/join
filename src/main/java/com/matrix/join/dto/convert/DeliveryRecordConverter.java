package com.matrix.join.dto.convert;

import com.matrix.join.common.DeliveryStateEnum;
import com.matrix.join.dto.DeliveryRecordDTO;
import com.matrix.join.entity.DeliveryRecordEntity;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @ClassName DeliveryRecordDTOConverter
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 14:53
 * @Version 1.0
 */
@Component
public class DeliveryRecordConverter extends CommonConverter{

    /**
     * po -> dto
     * @param deliveryRecord
     * @return
     */
    public DeliveryRecordDTO convertPOToDTO(DeliveryRecordEntity deliveryRecord) {
        DeliveryRecordDTO dto = super.convert(DeliveryRecordDTO.class, deliveryRecord);
        dto.setState(DeliveryStateEnum.state(deliveryRecord.getState()));
        return dto;
    }

    /**
     * dto -> po
     * @param deliveryRecordDTO
     * @return
     */
    public DeliveryRecordEntity convertDTOToPO(DeliveryRecordDTO deliveryRecordDTO) {
        DeliveryRecordEntity po = super.convert(DeliveryRecordEntity.class, deliveryRecordDTO);
        po.setState(DeliveryStateEnum.code(deliveryRecordDTO.getState()));
        return po;
    }
}

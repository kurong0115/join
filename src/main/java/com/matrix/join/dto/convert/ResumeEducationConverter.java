package com.matrix.join.dto.convert;

import com.matrix.join.common.EducationEnum;
import com.matrix.join.dto.ResumeEducationDTO;
import com.matrix.join.entity.ResumeEducationEntity;
import com.matrix.join.util.DataTypeUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @ClassName ResumeEducationConverter
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/23 15:51
 * @Version 1.0
 */
@Component
public class ResumeEducationConverter extends CommonConverter{

    /**
     * po -> dto
     * @param po
     * @return
     */
    public ResumeEducationDTO convertPOToDTO(ResumeEducationEntity po) {
        ResumeEducationDTO dto = super.convert(ResumeEducationDTO.class, po);
        dto.setDegree(EducationEnum.education(DataTypeUtils.convertByteToInteger(po.getDegree())));
        return dto;
    }

    /**
     * dto -> po
     * @param dto
     * @return
     */
    public ResumeEducationEntity convertDTOToPO(ResumeEducationDTO dto) {
        ResumeEducationEntity po = super.convert(ResumeEducationEntity.class, dto);
        if (Objects.isNull(po)) {
            return null;
        }
        po.setDegree(DataTypeUtils.convertIntegerToByte(EducationEnum.code(dto.getDegree())));
        return po;
    }
}

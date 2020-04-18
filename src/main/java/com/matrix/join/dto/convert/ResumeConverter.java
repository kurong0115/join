package com.matrix.join.dto.convert;

import com.matrix.join.common.EducationEnum;
import com.matrix.join.common.GenderEnum;
import com.matrix.join.common.MaritalStatusEnum;
import com.matrix.join.common.WorkExperienceEnum;
import com.matrix.join.dto.ResumeDTO;
import com.matrix.join.entity.ResumeEntity;
import com.matrix.join.util.DataTypeUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ClassName ResumeDTO
 * @Description ResumeDTO ResumePO转换
 * @Author Administrator
 * @Date 2020/3/23 14:21
 * @Version 1.0
 */
@Component
public class ResumeConverter extends CommonConverter{

    /**
     * po -> dto
     * @param resumeEntity
     * @return
     */
    public ResumeDTO convertPOToDTO(ResumeEntity resumeEntity) {
        ResumeDTO dto = super.convert(ResumeDTO.class, resumeEntity);
        if (Objects.nonNull(dto)) {
            dto.setEducation(EducationEnum.education(DataTypeUtils.convertByteToInteger(resumeEntity.getEducation())))
                    .setGender(GenderEnum.gender(DataTypeUtils.convertByteToInteger(resumeEntity.getGender())))
                    .setWorkExperience(WorkExperienceEnum.workExperience(DataTypeUtils.convertByteToInteger(resumeEntity.getWorkExperience())))
                    .setMaritalStatus(MaritalStatusEnum.maritalStatus(resumeEntity.getMaritalStatus()));
        }
        return dto;
    }

    /**
     * dto -> po
     * @param resumeDTO
     * @return
     */
    public ResumeEntity convertDTOToPO(ResumeDTO resumeDTO) {
        ResumeEntity po = super.convert(ResumeEntity.class, resumeDTO);
        if (Objects.nonNull(po)) {
            po.setEducation(DataTypeUtils.convertIntegerToByte(EducationEnum.code(resumeDTO.getEducation())))
                    .setGender(DataTypeUtils.convertIntegerToByte(GenderEnum.code(resumeDTO.getGender())))
                    .setWorkExperience(DataTypeUtils.convertIntegerToByte(WorkExperienceEnum.code(resumeDTO.getWorkExperience())))
                    .setMaritalStatus(MaritalStatusEnum.code(resumeDTO.getMaritalStatus()));
        }
        return po;
    }
}

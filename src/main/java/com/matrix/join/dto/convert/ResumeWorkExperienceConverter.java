package com.matrix.join.dto.convert;

import com.matrix.join.dto.ResumeWorkExperienceDTO;
import com.matrix.join.entity.ResumeWorkExperienceEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ClassName ResumeWorkExperienceConverter
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/23 16:19
 * @Version 1.0
 */
@Component
public class ResumeWorkExperienceConverter extends CommonConverter{

    /**
     * po -> dto
     * @param po
     * @return
     */
    public ResumeWorkExperienceDTO convertPOToDTO(ResumeWorkExperienceEntity po) {
        ResumeWorkExperienceDTO dto = super.convert(ResumeWorkExperienceDTO.class, po);
        return dto;
    }

    /**
     * dto -> po
     * @param dto
     * @return
     */
    public ResumeWorkExperienceEntity convertDTOToPO(ResumeWorkExperienceDTO dto) {
        ResumeWorkExperienceEntity po = super.convert(ResumeWorkExperienceEntity.class, dto);
        if (Objects.isNull(po)) {
            return null;
        }
        return po;
    }
}

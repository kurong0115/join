package com.matrix.join.dto.convert;

import com.matrix.join.dto.ResumeAttachDTO;
import com.matrix.join.entity.ResumeAttachEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

/**
 * @ClassName ResumeAttachConverter
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/26 13:49
 * @Version 1.0
 */
@Component
public class ResumeAttachConverter extends CommonConverter{

    /**
     * po -> dto
     * @param resumeAttachEntity
     * @return
     */
    public ResumeAttachDTO convertPOToDTO(ResumeAttachEntity resumeAttachEntity) {
        ResumeAttachDTO dto = super.convert(ResumeAttachDTO.class, resumeAttachEntity);
        if (Objects.nonNull(dto) && Objects.nonNull(dto.getUrl())) {
            int index = resumeAttachEntity.getUrl().lastIndexOf(File.separator);
            dto.setFileName(resumeAttachEntity.getUrl().substring(index + 1));
        }
        return dto;
    }

    /**
     * dto -> po
     * @param resumeAttachDTO
     * @return
     */
    public ResumeAttachEntity convertDTOToPO(ResumeAttachDTO resumeAttachDTO) {
        ResumeAttachEntity po = super.convert(ResumeAttachEntity.class, resumeAttachDTO);
        return po;
    }
}

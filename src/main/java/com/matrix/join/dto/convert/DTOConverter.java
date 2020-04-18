package com.matrix.join.dto.convert;

import com.auth0.jwt.JWT;
import com.matrix.join.common.*;
import com.matrix.join.dto.JobDTO;
import com.matrix.join.dto.UserDTO;
import com.matrix.join.entity.JobEntity;
import com.matrix.join.entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;

/**
 * @ClassName DTOConverter
 * @Description dto转换
 * @Author Administrator
 * @Date 2020/2/5 13:11
 * @Version 1.0
 */
public class DTOConverter {

    public static final String GET = "get";

    public static final String SET = "set";

    /**
     * 作用于做对象转换po -> dto
     * @param object 源类型
     * @param clazz 目标对象的类对象
     * @param <T>
     * @return
     */
    public static <T> T convert(Object object, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            String className = object.getClass().getSimpleName();
            Method method = clazz.getMethod(SET + className, object.getClass());
            method.invoke(t, object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * dto -> po
     * @param dto
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse(JobDTO dto, Class<T> clazz) {
        String userId = JWT.decode(dto.getCreator()).getAudience().get(0);
        if (clazz == JobEntity.class) {
            JobEntity jobEntity = JobEntity.builder()
                    .jobNo(dto.getJobNo())
                    .city(dto.getCity())
                    .benefit(dto.getBenefit())
                    .companyNo(dto.getCompanyNo())
                    .creator(BigInteger.valueOf(Long.decode(userId)))
                    .education(EducationEnum.code(dto.getEducation()).byteValue())
                    .gender(GenderEnum.code(dto.getGender()).byteValue())
                    .id(dto.getId())
                    .gmtCreate(dto.getGmtCreate())
                    .gmtModified(dto.getGmtModified())
                    .introduce(dto.getIntroduce())
                    .jobType(JobTypeEnum.code(dto.getJobType()).byteValue())
                    .salary(SalaryEnum.code(dto.getSalary()).byteValue())
                    .name(dto.getName())
                    .jobCategory(dto.getJobCategory())
                    .workExperience(WorkExperienceEnum.code(dto.getWorkExperience()).byteValue())
                    .email(dto.getEmail())
                    .address(dto.getAddress())
                    .build();
            return (T) jobEntity;
        }
        return null;
    }

    /**
     *
     * @param jobEntity
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(JobEntity jobEntity, Class<T> clazz) {
        if (clazz == JobDTO.class) {
            JobDTO dto = JobDTO.builder()
                    .jobNo(jobEntity.getJobNo())
                    .city(jobEntity.getCity())
                    .benefit(jobEntity.getBenefit())
                    .companyNo(jobEntity.getCompanyNo())
                    .creator(jobEntity.getCreator().toString())
                    .education(EducationEnum.education(Integer.valueOf(jobEntity.getEducation())))
                    .gender(GenderEnum.gender(jobEntity.getGender().intValue()))
                    .id(jobEntity.getId())
                    .gmtCreate(jobEntity.getGmtCreate())
                    .gmtModified(jobEntity.getGmtModified())
                    .introduce(jobEntity.getIntroduce())
                    .jobType(JobTypeEnum.type(jobEntity.getJobType().intValue()))
                    .salary(SalaryEnum.salary(jobEntity.getSalary().intValue()))
                    .name(jobEntity.getName())
                    .jobCategory(jobEntity.getJobCategory())
                    .workExperience(WorkExperienceEnum.workExperience(jobEntity.getWorkExperience().intValue()))
                    .email(jobEntity.getEmail())
                    .address(jobEntity.getAddress())
                    .icon(jobEntity.getIcon())
                    .build();
            return (T) dto;
        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println(convert(new UserEntity().setEmail("1181209156@qq.com"), UserDTO.class));
        JobDTO dto = convert(new JobEntity().setName("Java"), JobDTO.class);
        System.out.println(dto);
    }
}

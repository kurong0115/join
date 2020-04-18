package com.matrix.join.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.annotation.UserLogin;
import com.matrix.join.dto.DeliveryRecordDTO;
import com.matrix.join.dto.convert.DeliveryRecordConverter;
import com.matrix.join.entity.DeliveryRecordEntity;
import com.matrix.join.protocol.ApiResponse;
import com.matrix.join.protocol.Pagination;
import com.matrix.join.service.DeliveryRecordService;
import com.matrix.join.vo.DeliveryRecordVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName DeliveryRecordController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/31 15:07
 * @Version 1.0
 */
@Api(value = "投递")
@RestController
@RequestMapping(value = "/delivery")
public class DeliveryRecordController {

    @Autowired
    DeliveryRecordConverter deliveryRecordConverter;

    @Autowired
    DeliveryRecordService deliveryRecordService;

    @UserLogin
    @PostMapping(value = "/saveDeliveryRecord")
    public ApiResponse<DeliveryRecordDTO> saveDeliveryRecord(@RequestBody @Valid DeliveryRecordDTO deliveryRecordDTO) {
        DeliveryRecordEntity deliveryRecordEntity = deliveryRecordService.saveDeliveryRecord(deliveryRecordConverter.convertDTOToPO(deliveryRecordDTO));
        return ApiResponse.responseData(deliveryRecordConverter.convertPOToDTO(deliveryRecordEntity));
    }

    @UserLogin
    @GetMapping(value = "/listDeliveryRecord")
    public ApiResponse<List<DeliveryRecordVO>> listDeliveryRecord(@RequestParam(name = "jobName", required = false) String jobName,
                                                                  @RequestParam(name = "recordId", required = false)BigInteger recordId,
                                                                  @RequestParam(name = "state", required = false) Byte state,
                                                                  @RequestParam(name = "sender", required = false) BigInteger sender,
                                                                  @RequestParam(name = "receiver", required = false) BigInteger receiver,
                                                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                                  @RequestParam(name = "pageSize", required = false, defaultValue = "6") int pageSize) {
        IPage<DeliveryRecordVO> deliveryRecord = deliveryRecordService.listDeliveryRecord(jobName, recordId, state, sender, receiver, new Page<DeliveryRecordVO>(pageNum, pageSize));
        return new ApiResponse<List<DeliveryRecordVO>>().builder().code(200).message("ok")
                .data(deliveryRecord.getRecords()).pagination(Pagination.convertPage(deliveryRecord)).build();
    }

    @UserLogin
    @PostMapping(value = "/updateDeliveryState")
    public ApiResponse<DeliveryRecordVO> updateDeliveryState(@RequestParam(value = "userId", required = true) BigInteger userId,
                                                             @RequestParam(value = "recordId", required = true) BigInteger recordId,
                                                             @RequestParam(value = "state", required = true) Byte state) {
        int result = deliveryRecordService.updateDeliveryState(userId, recordId, state);
        return ApiResponse.responseData(null);
    }
}

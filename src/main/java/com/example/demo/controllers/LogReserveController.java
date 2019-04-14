package com.example.demo.controllers;
import com.example.demo.define.ResultCode;
import com.example.demo.dto.LogReserveDTO;
import com.example.demo.dto.PitchDTO;
import com.example.demo.dto.response.Response;
import com.example.demo.services.LogReserveService;
import io.swagger.annotations.ApiParam;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogReserveController {
    @Autowired
    private LogReserveService logReserveService;

    @GetMapping(value = "/common/get-pitch-free-time")
    public Response getPitchFreeTime(@ApiParam("district") @RequestParam String district,
                                     @ApiParam("date") @RequestParam String date,
                                     @ApiParam("time") @RequestParam Long time,
                                     @ApiParam("type") @RequestParam String type){
        List<JSONObject> list = logReserveService.getPitchFreeTime(district,date,time,type);
        if (list != null) {
            return new Response(ResultCode.SUCCESS, list, ResultCode.STR_SUCCESS);
        }else{
            return new Response(ResultCode.BAD_REQUEST,null,ResultCode.STR_BAD_REQUEST);
        }
    }

    @PostMapping(value = "/personal/reserve-pitch")
    public Response reservePitch(@ApiParam("date") @RequestParam String date,
                                  @ApiParam("idCustomer") @RequestParam Long idCustomer,
                                  @ApiParam("idPitch") @RequestParam Long idPitch,
                                  @ApiParam("idPrice") @RequestParam Long idPrice,
                                  @ApiParam("idTime") @RequestParam Long idTime){
        JSONObject object = logReserveService.reservePitch(idCustomer,idPitch,idPrice,idTime,date);
        if (object != null){
            return new Response(ResultCode.SUCCESS,object,ResultCode.STR_SUCCESS);
        }else{
            return new Response(ResultCode.BAD_REQUEST,null,ResultCode.STR_BAD_REQUEST);
        }
    }

    @GetMapping(value = "/personal/get-reserve")
    public Response getReserve(@ApiParam("idCustomer") @RequestParam Long idCustomer,
                               @ApiParam("page") @RequestParam int page,
                               @ApiParam("pageSize") @RequestParam int pageSize){
        List<LogReserveDTO> logReserveDTOS = logReserveService.getLogByIdCustomer(idCustomer,page,pageSize);
        if (logReserveDTOS != null){
            return new Response(ResultCode.SUCCESS,logReserveDTOS,ResultCode.STR_SUCCESS);
        }else{
            return new Response(ResultCode.BAD_REQUEST,null,ResultCode.STR_BAD_REQUEST);
        }
    }
}

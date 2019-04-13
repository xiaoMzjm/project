package com.zjm.suggest.api;

/**
 * @author:小M
 * @date:2019/2/18 11:41 PM
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.zjm.common.constant.Result;
import com.zjm.suggest.constant.Constants.SuggestBizKeyEnum;
import com.zjm.suggest.constant.SuggestResult;
import com.zjm.suggest.model.SuggestConvertor;
import com.zjm.suggest.model.SuggestDTO;
import com.zjm.suggest.model.SuggestSaveDTO;
import com.zjm.suggest.model.SuggestVO;
import com.zjm.suggest.service.SuggestService;
import com.zjm.user.model.WxUserInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:小M
 * @date:2019/1/13 10:41 PM
 */
@Api(description = "建议和反馈接口")
@Controller
@ResponseBody
@RequestMapping("/suggest")
@RestController
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    /**
     * 获取健身小程序首页广告
     * @return
     */
    @ApiOperation(value = "保存健身小程序建议" ,  notes="保存建议")
    @RequestMapping(value = "/save" , method = RequestMethod.POST , produces = {"application/json;charset=UTF-8"})
    public String saveFitSuggest(@ApiParam(name="类型",value="type",required=true)String type ,
                       @ApiParam(name="问题或建议",value="suggest",required=true)String suggest ,
                       HttpServletRequest request,
                       HttpServletResponse response){
        SuggestSaveDTO suggestSaveDTO = new SuggestSaveDTO();
        suggestSaveDTO.setBizKey(SuggestBizKeyEnum.FIT.getCode());
        suggestSaveDTO.setType(type);
        suggestSaveDTO.setSuggest(suggest);
        WxUserInfoDTO wxUserInfoDTO = (WxUserInfoDTO)request.getAttribute("user");
        suggestSaveDTO.setUserId(wxUserInfoDTO.getId());
        SuggestResult<SuggestDTO> suggestResult = suggestService.save(suggestSaveDTO);
        if(!suggestResult.getSuccess()) {
            return JSON.toJSONString(suggestResult);
        }
        SuggestVO suggestVO = SuggestConvertor.dto2vo(suggestResult.getData());

        return JSON.toJSONString(Result.success(suggestVO));
    }
}

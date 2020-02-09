package com.zjm.suggest.service;

import com.zjm.common.exception.BaseException;
import com.zjm.common.exception.ExceptionEnums;
import com.zjm.common.util.VerifyUtil;
import com.zjm.suggest.constant.SuggestResult;
import com.zjm.suggest.manager.SuggestManager;
import com.zjm.suggest.model.SuggestDTO;
import com.zjm.suggest.model.SuggestSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/21 12:43 AM
 */
@Component
public class SuggestService {

    @Autowired
    private SuggestManager suggestManager;

    public SuggestResult save(SuggestSaveDTO suggestSaveDTO){
        try {
            VerifyUtil.isNotEmpty(suggestSaveDTO.getBizKey() , ExceptionEnums.SAVE_BIZ_KEY_IS_NULL.getCode(),ExceptionEnums.SAVE_BIZ_KEY_IS_NULL.getMsg());
            VerifyUtil.isNotEmpty(suggestSaveDTO.getType() , ExceptionEnums.SAVE_TYPE_IS_NULL.getCode(), ExceptionEnums.SAVE_TYPE_IS_NULL.getMsg());
            VerifyUtil.isNotEmpty(suggestSaveDTO.getSuggest() , ExceptionEnums.SAVE_SUGGEST_IS_NULL.getCode(), ExceptionEnums.SAVE_TYPE_IS_NULL.getMsg());
            VerifyUtil.isNotEmpty(suggestSaveDTO.getUserId() , ExceptionEnums.SAVE_USER_ID_IS_NULL.getCode(), ExceptionEnums.SAVE_TYPE_IS_NULL.getMsg());
            SuggestDTO suggestDTO = suggestManager.save(suggestSaveDTO);
            return SuggestResult.success(suggestDTO);
        }
        catch (BaseException se) {
            return SuggestResult.error(se.getErrorCode() , se.getMessage());
        }
        catch (Exception e) {
            return SuggestResult.error(e.getMessage() , e.getMessage());
        }
    }
}

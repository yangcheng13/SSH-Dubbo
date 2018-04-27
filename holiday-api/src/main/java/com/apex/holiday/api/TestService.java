package com.apex.holiday.api;

import java.util.List;
import com.apex.holiday.dto.HolidayTestDto;


/**
 * @desc: holiday-api
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午2:29:36
 * @version: v1.0
 */
public interface TestService {

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午2:29:48
     * @return List<HolidayTestDto>
     */
    List<HolidayTestDto> findAll();

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午2:29:55
     * @param vo void
     */
    void add(HolidayTestDto vo);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月8日 下午5:07:30
     * @param vo void
     */
    void update(HolidayTestDto vo);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月11日 下午1:52:36
     * @param appId void
     */
    void delete(String appId);

}

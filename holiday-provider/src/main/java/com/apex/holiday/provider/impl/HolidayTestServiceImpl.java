package com.apex.holiday.provider.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.apex.holiday.api.TestService;
import com.apex.holiday.dao.HolidayDao;
import com.apex.holiday.domain.HolidayTest;
import com.apex.holiday.dto.HolidayTestDto;

/**
 * @desc: holiday-provider
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午2:00:21
 * @version: v1.0
 */
@Service("holidayTestService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class HolidayTestServiceImpl implements TestService {

    @Autowired
    private HolidayDao holidayDao;

    @Override
    public List<HolidayTestDto> listCity() {
        List<HolidayTestDto> dtos = new ArrayList<>();
        List<HolidayTest> list = holidayDao.loadAll();
        for (HolidayTest item : list) {
            HolidayTestDto dto = new HolidayTestDto();
            dto.setAddTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    .format(new Date(item.getAddTime())));
            dto.setAppId(item.getAppId());
            dto.setCity(item.getCity());
            dto.setPrice(item.getPrice());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void add(HolidayTestDto vo) {
        HolidayTest holidayTest = new HolidayTest();
        // holidayTest.setAddTime(System.currentTimeMillis());
        // holidayTest.setAppId(vo.getAppId());
        holidayTest.setCity(vo.getCity());
        holidayTest.setPrice(vo.getPrice());
        holidayDao.save(holidayTest);

    }

    @Override
    public void update(HolidayTestDto vo) {
        HolidayTest holidayTest = new HolidayTest();
        holidayTest.setAppId(vo.getAppId());
        holidayTest.setCity(vo.getCity());
        holidayTest.setPrice(vo.getPrice());
        holidayDao.update(holidayTest);

    }

    @Override
    public void delete(String appId) {
        holidayDao.deleteById(appId);
    }

}

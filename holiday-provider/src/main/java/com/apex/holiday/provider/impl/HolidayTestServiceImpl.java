package com.apex.holiday.provider.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apex.holiday.api.TestService;
import com.apex.holiday.domain.HolidayTest;
import com.apex.holiday.dto.HolidayTestDto;
import com.apex.holiday.repository.HolidayTestRepository;

/**
 * @desc: holiday-provider
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午2:00:21
 * @version: v1.0
 */
@Service("holidayTestService")
public class HolidayTestServiceImpl implements TestService {

    @Autowired
    private HolidayTestRepository holidayTestRepository;

    @Override
    public List<HolidayTestDto> listCity() {
        List<HolidayTestDto> dtos = new ArrayList<>();
        List<HolidayTest> list =
                holidayTestRepository.find("from HolidayTest", null);
        for (HolidayTest item : list) {
            HolidayTestDto dto = new HolidayTestDto();
            dto.setAddTime(item.getAddTime());
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
        holidayTestRepository.save(holidayTest);

    }

    @Override
    public void update(HolidayTestDto vo) {
        HolidayTest holidayTest = new HolidayTest();
        holidayTest.setAppId(vo.getAppId());
        holidayTest.setCity(vo.getCity());
        holidayTest.setPrice(vo.getPrice());
        holidayTestRepository.update(holidayTest);

    }

}

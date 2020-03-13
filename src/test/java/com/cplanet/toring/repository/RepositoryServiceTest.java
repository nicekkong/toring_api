package com.cplanet.toring.repository;

import com.cplanet.toring.domain.TestData;
import com.cplanet.toring.dto.TestDataRequestDto;
import com.cplanet.toring.mapper.TestDataMapper;
import com.cplanet.toring.service.TestDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryServiceTest {

    @Autowired
    TestDataService testDataService;


    @Autowired
    TestDataRepository testDataRepository;

    @Test
    public void testSave() {


        TestDataRequestDto dto = TestDataRequestDto.builder()
                .intData(99)
                .stringData("nicekkong@gmail.com")
                .build();
        System.out.println(testDataService.save(dto));
    }

    @Test
    public void testJpql() {
        testDataRepository.findBySearch("nicek").forEach(
                l -> System.out.println(l.getStringData())
        );
    }

    @Test
    public void testFindAll() {

        testDataRepository.findBySearch("nicekkong@gmail.com").forEach(
                l -> System.out.println(l.getCreateDate())
        );
    }

    @Autowired
    TestDataMapper testDataMapper;

    @Test
    public void testyMybatis() {

        TestData testData = testDataMapper.selectTestDataById(3l);
        System.out.println(testData.getStringData());


        List<String> testDatas = testDataMapper.selectByStrSearch("nice");
        testDatas.forEach(System.out::println);

    }


}

package reaper.service.fundServiceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reaper.service.FundService;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Feng on 2017/9/7.
 */

@RunWith(Parameterized.class)
@SpringBootTest()
public class TestFindUnitNetValueTrendByCode {
    @Autowired
    FundService fundService;

    private int length;
    private String startDate;
    private String endDate;
    private double startValue;
    private double endValue;
    private String code;

    @Parameterized.Parameters
    public static Collection words(){
        return Arrays.asList(new Object[][]{
                {1066, "2013-03-22", "1", "2017-09-01", "1.7883", "000008"},
                {},
                {}
        });
    }

    public TestFindUnitNetValueTrendByCode(int length, String startDate, String endDate, double startValue, double endValue, String code){
        this.length=length;
        this.startDate=startDate;
        this.endDate=endDate;
        this.startValue=startValue;
        this.endValue=endValue;
        this.code=code;
    }

    @Test
    public void findUnitNetValueTrendByCodeTest(){

    }
}

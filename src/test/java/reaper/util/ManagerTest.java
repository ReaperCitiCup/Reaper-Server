package reaper.util;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.CompanyMiniBean;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;
import reaper.model.*;
import reaper.repository.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Feng on 2017/8/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class ManagerTest {
//    @Autowired
//    ManagerRepository managerRepository;
//
//    @Autowired
//    ManagerCompanyRespository managerCompanyRespository;
//
//    @Autowired
//    CompanyRepository companyRepository;
//
//    @Autowired
//    FundHistoryRepository fundHistoryRepository;
//
//    @Autowired
//    FundRepository fundRepository;
//
//    //managers
//    String[] managerIds={"m1","m2","m3"};
//    String[] managerNames={"name1","name2","name3"};
//    String[] genders={"w","w","m"};
//    Date[] dates={new Date(), new Date(), new Date()};
//    String[] introductions={"intro1", "intro2", "intro3"};
//    Double[] totalScopes={1.1, 2.2, 3.3};
//    Double[] bestReturnss={11.11, 22.22, 33.33};
//
//    //managerCompany
//    String[] managerCompanyIds={"c1", "c2", "c1"};
//
//    //company
//    String[] companyIds={"c1","c2"};
//    String[] companyName={"cname1", "cname2"};
//
//    //fund
//    String[] fundCodes={"f1","f2","f3","f4"};
//    String[] fundNames={"fname1","fname2","fname3","fname4"};
//    String[] fundTypes1={"t1","t2","t3","t4"};
//    String[] fundTypes2={"t11","t22","t33","t44"};
//    Date[] esDates={new Date(), new Date(), new Date(),new Date()};
//    Double[] scopes={1.0, 2.0, 3.0, 4.0};
//
//    //fundHistory
//    String[] hManagerIds={"h1","h2","h2","h1","h2"};
//    String[] hFundCodes={"f1","f1","f3","f2","f1"};
//    String[] hFundNames={"fname1","fname1","fname3","fname2","fname1"};
//    String[] hFundTypes={"t1","t1","t3","t2","t1"};
//    Double[] hSizes={10.1, 20.2, 30.3, 50.5, 40.4};
//    Date[] hStartDates={new Date(),new Date(),new Date(),new Date(),new Date()};
//    Date[] hEndDates={new Date(),new Date(),new Date(),new Date(),new Date()};
//    String[] hTimes={"11","22","33","44","55"};
//    Double[] hPayBacks={1.11, 2.22, 3.33, 5.55, 4.44};
//
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//    @Before
//    public void prepare() throws Exception{
//        System.out.println(fundHistoryRepository.getClass());
//        for (int i = 0; i < managerIds.length; i++) {
//            Manager manager=new Manager();
//            manager.setManagerId(managerIds[i]);
//            manager.setName(managerNames[i]);
//            manager.setAppointedDate(dates[i]);
//            manager.setIntroduction(introductions[i]);
//            manager.setTotalScope(totalScopes[i]);
//            manager.setBestReturns(bestReturnss[i]);
//            managerRepository.save(manager);
//
//            ManagerCompany managerCompany=new ManagerCompany();
//            managerCompany.setManagerId(managerIds[i]);
//            managerCompany.setCompanyId(managerCompanyIds[i]);
//            managerCompanyRespository.save(managerCompany);
//        }
//
//        for (int i = 0; i < companyIds.length; i++) {
//            Company company=new Company();
//            company.setCompanyId(companyIds[i]);
//            company.setName(companyName[i]);
//            companyRepository.save(company);
//        }
//
//        for (int i = 0; i < fundCodes.length; i++) {
//            Fund fund=new Fund();
//            fund.setCode(fundCodes[i]);
//            fund.setName(fundNames[i]);
//            fund.setType1(fundTypes1[i]);
//            fund.setType2(fundTypes2[i]);
//            fund.setEstablishmentDate(esDates[i]);
//            fund.setScope(scopes[i]);
//            fundRepository.save(fund);
//        }
//
////        for (int i = 0; i < hManagerIds.length; i++) {
////            FundHistory fundHistory=new FundHistory();
////            fundHistory.setManagerId(hManagerIds[i]);
////            fundHistory.setCode(hFundCodes[i]);
////            fundHistory.setFundName(hFundNames[i]);
////            fundHistory.setFundType(hFundTypes[i]);
////            fundHistory.setSize(hSizes[i]);
////            fundHistory.setStartDate(hStartDates[i]);
////            fundHistory.setEndDate(hEndDates[i]);
////            fundHistory.setTime(hTimes[i]);
////            fundHistory.setPayback(hPayBacks[i]);
////            fundHistoryRepository.save(fundHistory);
////        }
//
//    }
//
//    @Test
//    public void findManagerByIdTest(){
//        Manager manager=managerRepository.findByManagerId("m1");
//        if(manager!=null){
//            ManagerCompany managerCompany=managerCompanyRespository.findByManagerId(manager.getManagerId());
//            if(managerCompany!=null){
//                Company company=companyRepository.findByCompanyId(managerCompany.getCompanyId());
//                if(company!=null){
//                    ManagerBean managerBean=  new ManagerBean(manager.getManagerId(), manager.getName(), sdf.format(manager.getAppointedDate()),
//                            new CompanyMiniBean(company.getCompanyId(), company.getName()), manager.getTotalScope(), manager.getBestReturns(), manager.getIntroduction());
//                }
//            }
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//    }
//
//    @Test
//    public void findFundHistoryByIdTest(){
//        List<FundHistoryBean> res=new ArrayList<>();
//        List<FundHistory> fundHistories=fundHistoryRepository.findAllByManagerId("h2");
////        if(fundHistories!=null){
////            for(FundHistory fundHistory:fundHistories){
////                List<String> type=new ArrayList<>();
////                type.add(fundHistory.getFundType1());
////                type.add(fundHistory.getFundType2());
////                res.add(new FundHistoryBean(fundHistory.getCode(),fundHistory.getFundName(),type,
////                        fundRepository.findByFundCode(fundHistory.getCode()).getScope(), sdf.format(fundHistory.getStartDate()), sdf.format(fundHistory.getEndDate()),
////                        (int)((fundHistory.getEndDate().getTime()-fundHistory.getStartDate().getTime())/(1000*3600*24)),
////                        fundHistory.getPayback()));
////            }
////        }
//        System.out.println(res.size());
//        System.out.println();
//    }
//
//    @After
//    public void tearDown(){
//        managerRepository.deleteAll();
//        managerCompanyRespository.deleteAll();
//        companyRepository.deleteAll();
////        fundHistoryRepository.deleteAll();
//        fundRepository.deleteAll();
//    }
}

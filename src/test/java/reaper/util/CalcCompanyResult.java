package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.*;
import reaper.repository.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CalcCompanyResult {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FundCompanyRepository fundCompanyRepository;

    @Autowired
    FactorResultRepository factorResultRepository;

    @Autowired
    TotalPortionRepository totalPortionRepository;

    @Autowired
    BrisonResultRepository brisonResultRepository;

    @Test
    public void calcResult(){
        for(Company company:companyRepository.findAll()){
            double sum = 0;
            FactorResult res = new FactorResult();
            res.initWithZero();

            for(FundCompany fundCompany:fundCompanyRepository.findAllByCompanyId(company.getCompanyId())){
                FactorResult factorResult = null;
                TotalPortion totalPortion = null;
                if(fundCompany!=null&&(factorResult=factorResultRepository.findByCodeAndFactorType(fundCompany.getFundId(),'R'))!=null&&(totalPortion=totalPortionRepository.findOne(fundCompany.getFundId()))!=null){
                    res.addFactorResult(factorResult,totalPortion.getValue());
                    sum+=totalPortion.getValue();
                }
                else {
//                    System.out.println(fundCompany);
                }
            }
            res.toResult(sum);
            res.setFactorType('R');
            res.setCode(company.getCompanyId());
            if(sum==0){
                System.out.println(company.getCompanyId());
                System.out.println(res);
            }else {
                System.out.println(res);
                factorResultRepository.save(res);
            }
        }

    }

//    @Test
//    public void calcBrisonResult(){
//        for(Company company:companyRepository.findAll()){
//            double sum = 0;
//            BrisonResult res = new BrisonResult();
//            res.initWithZero();
//
//            for(FundCompany fundCompany:fundCompanyRepository.findAllByCompanyId(company.getCompanyId())){
//                BrisonResult brisonResult = null;
//                TotalPortion totalPortion = null;
//                if(fundCompany!=null&&(brisonResult=brisonResultRepository.findByCode(fundCompany.getFundId()))!=null&&(totalPortion=totalPortionRepository.findOne(fundCompany.getFundId()))!=null){
//                    res.addResult(brisonResult,totalPortion.getValue());
//                    sum+=totalPortion.getValue();
//                }
//                else {
////                    System.out.println(fundCompany);
//                }
//            }
//            res.toResult(sum);
//            res.setCode(company.getCompanyId());
//            if(sum==0){
//                System.out.println(company.getCompanyId());
//                System.out.println(res);
//            }else {
//                System.out.println(res);
//                brisonResultRepository.save(res);
//            }
//
//        }
//    }
}

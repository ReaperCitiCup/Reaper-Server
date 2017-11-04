package reaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.Fund;
import reaper.model.FundNetEdge;
import reaper.model.ManagerRelation;
import reaper.repository.FundNetEdgeRepository;
import reaper.repository.ManagerRelationRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by max on 2017/9/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class FundNetEdgeTest {
    @Autowired
    FundNetEdgeRepository fundNetEdgeRepository;

    @Test
    public void solve(){
        getAllIn();
    }


    public void getAllIn(){
        File file=new File("src/main/fund_net_edge_10.csv");
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=null;
            while ((s=bufferedReader.readLine())!=null){

                //System.out.println(s);

                String[] temp = s.split(",");
                String newString = String.format("%06d", 5);

                    String code1 = String.format("%06d", Integer.parseInt(temp[1]));
                    String code2 = String.format("%06d", Integer.parseInt(temp[2]));
                    Double weight = Double.parseDouble(temp[3]);
                    FundNetEdge fundNetEdge=new FundNetEdge();
                    fundNetEdge.setCodeIdA(code1);
                    fundNetEdge.setCodeIdB(code2);
                    fundNetEdge.setWeight(weight);
                    fundNetEdgeRepository.save(fundNetEdge);

                    System.out.println(code1 + " " + code2 + " " + weight);



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getAllCodeRelated(){
        List<FundNetEdge> res = new ArrayList<>();
        for(FundNetEdge fundNetEdge:getInterfacingCode("000017",res)){
            System.out.println(fundNetEdge);
        }

    }

    private List<FundNetEdge> getInterfacingCode(String code,List<FundNetEdge> fundNetEdges){
        //作为左端点
        for(FundNetEdge fundNetEdge:fundNetEdgeRepository.findAllByCodeIdA(code)){
            if(!fundNetEdges.contains(fundNetEdge)) {
                fundNetEdges.add(fundNetEdge);
                fundNetEdges = getInterfacingCode(fundNetEdge.getCodeIdB(),fundNetEdges);
            }
        }
        //作为右端点
        for(FundNetEdge fundNetEdge:fundNetEdgeRepository.findAllByCodeIdB(code)){
            if(!fundNetEdges.contains(fundNetEdge)) {
                fundNetEdges.add(fundNetEdge);
                fundNetEdges = getInterfacingCode(fundNetEdge.getCodeIdB(),fundNetEdges);
            }
        }
        return fundNetEdges;
    }


}

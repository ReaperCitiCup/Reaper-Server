# -*- coding: utf-8 -*-
#!/usr/bin/env python
#@author: xiexian

import csv
import pymysql
import xlrd
import time
from datetime import datetime, timedelta
from xlrd import xldate_as_tuple
from xlutils.copy import copy
import numpy
import pandas as pd
import math
from sklearn.cross_validation import train_test_split  #引用交叉验证
from sklearn.linear_model import LinearRegression
import sys


Number_Of_Trading_Days=245#一年的交易日个数



#计算标准差,参数类型：列表
def standardDeviation(rate):
     return numpy.std(rate)

#计算下行标准差，参数类型：列表
def downsideStdDev(rate,rf):   #rf:risk-free rate
     rateLen=len(rate)
     smallerRate=[]
     smallerLen=0
     squareSum=0
     for i in range(rateLen):
          if (rate[i]<rf[i]):
               smallerRate.append(rate[i])
               squareSum +=(rate[i]-rf[i])*(rate[i]-rf[i])
               smallerLen+=1
     if(smallerLen<=1):
          return 0
     else:
          return math.sqrt(squareSum/(float)(smallerLen-1))



#计算协方差，参数类型：列表
def countCovariance(x,y):
     xy=[]
     xyLen=min(len(x),len(y))
     for i in range(xyLen):
          xy.append(x[i]*y[i])
     xAvg=(float)(sum((x)))/len(x)
     yAvg=(float)(sum(y))/len(y)
     xyAvg=(float)(sum(xy))/len(xy)
     #Cov(x,y)=E[xy]-E[x]E[y]
     cov=xyAvg-xAvg*yAvg
     return cov




#计算Beta值，参数类型：列表
def countBeta(resultRate,marketRate):
     x=resultRate
     y=marketRate   
     cov=countCovariance(x,y)
     var=numpy.var(marketRate)
     if(0==var):
          return 0
     beta=(float)(cov)/var
     return beta


#计算Alpha，参数类型：前三个为列表，beta为数值
def countAlpha(resultRate,marketRate,rf,beta):
     alpha=[]
     alphaLen=min(len(resultRate),len(marketRate))
     for i in range(alphaLen):
          alpha.append((resultRate[i]-rf[i])-beta*(marketRate[i]-rf[i]))
     return alpha



#计算夏普比，参数类型：列表
def countSharpeRatio(resultRate,rf):
     Erp=sum(resultRate)/len(resultRate)
     Erf=sum(rf)/len(rf)
     std=standardDeviation(resultRate)
     if(0==std):
          return 0
     else:
          return (Erp-Erf)/std
     


#计算两个序列的相关系数，参数类型：列表
def countCorrelation(r1,r2):
     return countCovariance(r1,r2)/(numpy.std(r1)*numpy.std(r2))




#计算在险价值，参数类型：数值
def countValue_at_risk(yearlySigam):
     return 2.33*yearlySigam/math.sqrt(52)




#计算年化波动率，参数类型：列表
def annualizedVolatility(r):
     return standardDeviation(r)*math.sqrt(Number_Of_Trading_Days)



#计算年化收益率，参数类型：列表
def annualizedRate(dailyRate):
     result=0
     countLen=0
     while(countLen<len(dailyRate) and countLen<Number_Of_Trading_Days):
          result+=dailyRate[countLen]
          countLen+=1
     return result/countLen*Number_Of_Trading_Days



#计算特雷诺比率，参数类型：前两个为列表，beta为数值
def TreynorRatio(resultRate,rf,beta):
     Erp=sum(resultRate)/len(resultRate)
     Erf=sum(rf)/len(rf)
     return (Erp-Erf)/beta





#求两个列表的差
def ListSub(l1,l2):
     rtn=[]
     length=min(len(l1),len(l2))
     for i in range(length):
          rtn.append(l1[i]-l2[i])
     return rtn


def ListSubSqare(l1,l2):
     rtn=[]
     length=min(len(l1),len(l2))
     for i in range(length):
          rtn.append((l1[i]-l2[i])*(l1[i]-l2[i]))
     return rtn



#获取数据库里基金的所有代码
def getCode():
    try:
        conn=pymysql.connect(host='106.15.203.173',user='reaper',passwd='reaper112233',db='reaper',port=3306,charset='utf8')
        cur=conn.cursor()#获取一个游标
        cur.execute('SELECT  distinct id FROM reaper.fund_netValue')
        data=cur.fetchall()
        id=[]
        for d in data :
            id.append(str(d[0]))
    except Exception :print("查询失败")
    return id

#基金类
class Fund:
     def __init__(self,id):
          self.id=id   #基金代码
          self.date=[]
          self.nav=[]   #单位净值
          self.dailyRate=[]   #日收益率       

#根据基金代码从数据库里获取某个基金的信息
def getFund(id):
    fund=Fund(id)
    try:
        conn=pymysql.connect(host='106.15.203.173',user='reaper',passwd='reaper112233',db='reaper',port=3306,charset='utf8')
        cur=conn.cursor()
        cur.execute('SELECT  date,unitNetValue,dailyRate FROM reaper.fund_netValue WHERE id='+id)
        data=cur.fetchall()               
        for d in data :          
            fund.date.append((str(d[0]))[:10]) #去掉时分秒
            nav=filter(lambda ch: ch in '0123456789.',str(d[1]))
            if(''==nav):
                appNav=sum(fund.nav[-11:-1])/10
                fund.nav.append(appNav)
            else:
                fund.nav.append(float(nav))
                 
            dailyRate=filter(lambda ch: ch in '-0123456789.',str(d[2]))
            if(''==dailyRate):
                dIndex=data.index(d)
                appDailyRate=0
                if(dIndex+1<len(data)):
                    yesterdayNav=filter(lambda ch: ch in '-0123456789.',str(data[dIndex+1][1]))
                    if(yesterdayNav!=''):
                        yesterdayNav=float(yesterdayNav)
                        appDailyRate=(fund.nav[-1]-yesterdayNav)/yesterdayNav
                    else:
                         pass                
                else:
                    appDailyRate=sum(fund.dailyRate[-11:-1])/10
                
                fund.dailyRate.append(appDailyRate)
            else:       
                fund.dailyRate.append((float(dailyRate))/100) #数据库里的利率省略了百分号的，除回来
            #print (float(d[2]))/100
    except Exception :print(id+"查询失败")
    return fund
    


#解决日期与收益率以及净值的对应问题，返回的对象的属性包括：
#各个日期序列的并值，以及该时间序列对应的基金收益率，市场收益率和无风险利率(通过相同的下标对应，如fundRate[i],rm[i],rf[i]同为date[i]这一天的数据）
class corrDate:   
    def __init__(self,date1,l1,date2,l2,date3=0,l3=0):    
          self.date=[]  #date1，date2，date3这三个日期序列的并值
          self.fundRate=[]
          self.rm=[]
          self.rf=[]    
          if(0==date3): #只有两个时间序列的情况
               i1=0
               i2=0
               while(i1<len(date1) and i2<len(date2)):
                   if(date1[i1]>date2[i2]):
                        while(i1<len(date1) and  date1[i1]>date2[i2]):
                             i1+=1
                   else:
                        while(i2<len(date2)  and date2[i2]>date1[i1]):
                             i2+=1
                    
                   if(i1<len(date1) and i2<len(date2)):
                        #此处有date1[i1]=date2[i2]
                        self.date.append(date1[i1])
                        self.fundRate.append(l1[i1])
                        self.rm.append(l2[i2])
                        i1+=1
                        i2+=1
          else:#三个时间序列
               i1=0
               i2=0
               i3=0
               while(i1<len(date1) and i2<len(date2) and i3<len(date3)):
                   if((date1[i1]<date2[i2] or date1[i1]==date2[i2]) and (date1[i1]<date3[i3] or date1[i1]==date3[i3])):
                        while(i2<len(date2) and date1[i1]<date2[i2] ):
                             i2+=1
                        while(i3<len(date3) and date1[i1]<date3[i3]):
                             i3+=1
                        
                   elif((date2[i2]<date1[i1] or date2[i2]==date1[i1]) and (date2[i2]<date3[i3] or date2[i2]==date3[i3])):
                        while(i1<len(date1)  and date2[i2]<date1[i1]):
                             i1+=1
                        while(i3<len(date3) and date2[i2]<date3[i3] ):
                             i3+=1
                        
                   else:
                        while(i1<len(date1)  and date3[i3]<date1[i1]):
                             i1+=1
                        while(i2<len(date2) and date3[i3]<date2[i2]):
                             i2+=1
                             
                   if(i1<len(date1) and i2<len(date2) and i3<len(date3)):
                        self.date.append(date1[i1])
                        self.fundRate.append(l1[i1])
                        self.rm.append(l2[i2])
                        self.rf.append(l3[i3])
                        i1+=1
                        i2+=1
                        i3+=1
     #按起始时间计算(如用户要求计算某一段时间的波动率）
    def countByDate(self,startTime,endTime):
        i=0
        tempDate=[]
        tempFundRate=[]
        tempRm=[]
        tempRf=[]
        while(i<len(self.date) and self.date[i]>endTime):
            i+=1
        while(i<len(self.date) and self.date[i]>=startTime):
            tempDate.append(self.date[i])
            tempFundRate.append(self.fundRate[i])
            tempRm.append(self.rm[i])
            tempRf.append(self.rf[i])
            i+=1
            
        self.date=tempDate
        self.fundRate=tempFundRate
        self.rm=tempRm
        self.rf=tempRf
          

          


#市场收益率对象，暂时是从csv文件读取数据，数据库里有该数据可修改成从数据库里读取
class Rm:
     def __init__(self,fileName):
          self.date=[]   
          self.closingPrice=[]
          self.dayRate=[]
          self.monthRate=[]

          csv_reader = csv.reader(open(fileName))
          firstRow=1
          fundData=[]
          for row in csv_reader:
               if(1==firstRow):
                         firstRow=0
                         continue    
               fundData.append(row)#读入每一行数据
               
          dataLen=len(fundData)
          i=0
          while(i+20<dataLen):     
             self.date.append((filter(lambda ch: ch in '-0123456789',fundData[i][0])))
             curPrice=(float)(filter(lambda ch: ch in '0123456789.', fundData[i][3]))
             monthAgoPrice=(float)(filter(lambda ch: ch in '0123456789.', fundData[i+20][3]))#设每月20个交易日
             self.closingPrice.append(curPrice)
             dateRate=(filter(lambda ch: ch in '-0123456789.',fundData[i][4]))
             if(''==dateRate):
                 dateRate='0'
             #print dateRate,self.date[-1]
             self.dayRate.append(float(dateRate)/100)
             self.monthRate.append((curPrice-monthAgoPrice)/monthAgoPrice/100)
             i+=1


#无风险收益率对象，暂时是从csv文件读取数据
class Rf:
     def __init__(self,fileName,Treasury=''):
          self.date=[]   
          self.rfDaily=[]
          self.rfWeekly=[]
          self.rfMonthly=[]
          self.rfYearly=[]
          
          if(Treasury!=''):
              data = xlrd.open_workbook(Treasury)

              table=data.sheets()[0]          
              rm=[]
              r=1
              date=datetime(*xldate_as_tuple(table.row_values(r,0)[0],0)).strftime('%Y-%m-%d')
              while (date>'2016-12-30'):  #用国债数据计算2016/12/30以后的无风险收益率（因为Fund_RiskFree.csv里没有）
                  self.date.append(date)
                  self.rfDaily.append(table.row_values(r,4)[0])
                  self.rfWeekly.append((table.row_values(r,3)[0]-table.row_values(r+5,3)[0])/table.row_values(r+5,3)[0])
                  self.rfMonthly.append((table.row_values(r,3)[0]-table.row_values(r+20,3)[0])/table.row_values(r+20,3)[0])
                  self.rfYearly.append((table.row_values(r,3)[0]-table.row_values(r+246,3)[0])/table.row_values(r+246,3)[0])
                  r+=1
                  date=datetime(*xldate_as_tuple(table.row_values(r,0)[0],0)).strftime('%Y-%m-%d')
                  #print date
                  

          with open(fileName, 'r') as f:
               lines=f.readlines()
               isFirstLine=1
               lastLine=lines[-1]

               lineLen=len(lines)
               i=lineLen-1-1 #最后一行为空行，舍去
               
               while i>0:
                    line=lines[i]
                    i-=1
                    if(1==isFirstLine):
                         isFirstLine=0
                         continue
                    if(line==lastLine):
                         break
                    date=line[39:58]
                    rfYearly = line[61:76]
                    rfDaily = line[79:92]              
                    rfWeekly = line[95:108]
                    rfMonthly = line[111:125]
                    
                    self.date.append(filter(lambda ch: ch in '0123456789-', date))
                    #print rfYearly
                    self.rfYearly.append(((float)(filter(lambda ch: ch in '-0123456789.', rfYearly)))/100)
                    self.rfDaily.append(((float)(filter(lambda ch: ch in '-0123456789.', rfDaily)))/100)
                    self.rfWeekly.append( ((float)(filter(lambda ch: ch in '-0123456789.', rfWeekly)))/100)
                    self.rfMonthly.append(((float)(filter(lambda ch: ch in '-0123456789.', rfMonthly)))/100)
                 












#构造基金组合，待续
def fundGroup():
     fundDict={}
     codeList=[]    #前端应该传来的用于构造基金组合的数据
     pencentage=[]  #codeList为用户所选的组合中所用基金的代码（列表类型），pencentage为组合中所用基金占组合的百分比（也为列表类型，与codeList通过下标对应，如pencentage[i]表示基金代码为codeList[i]的基金占组合的百分比）
     for id in codeList:
        fundDict[id]=getFund(id) #获取组合中各个基金的信息
        
     myFundGroup=Fund("myFundGroup")#创建一个空的基金组合对象，加权平均后的数据可放到这个对象中
     #。。。
     




#测试函数
def test():
     rm=Rm('000001.csv')#读取市场数据
     #print rm.monthRate
     rf=Rf('Fund_RiskFree.csv','Treasury.xlsx')
     #print rf.date
     fundDict={} #基金字典，用于查询或管理基金，key为基金代码，value为Fund对象    
     #codeList=getCode()   
     #for id in codeList:
     #    fundDict[id]=getFund(id)
         
     id='000003' #前端点击查看某只基金的信息后，传来该基金的代码，赋值到这里，便可获取该基金的信息并计算各种风险因子
     fundDict[id]=getFund(id)
     
     temp=corrDate(fundDict[id].date,fundDict[id].dailyRate,rm.date,rm.dayRate,rf.date,rf.rfMonthly)
        #统一日期，并返回统一后的日期序列和该日期序列对应的基金收益率序列/市场收益率序列/无风险利率序列,市场数据用的是日收益率，无风险利率用的是月收益率

     
     beta=countBeta(temp.fundRate,temp.rm)
     alpha=countAlpha(temp.fundRate,temp.rm,temp.rf,beta)
     
      
     print "目标基金年化收益率=",annualizedRate(fundDict[id].dailyRate)
     print "目标基金年化波动率=",annualizedVolatility(temp.fundRate)
     print "目标基金在险价值=",countValue_at_risk(annualizedVolatility(temp.fundRate))
     print "目标基金收益率序列的下行标准差=",downsideStdDev(temp.fundRate,temp.rf)
     print "目标基金夏普比=",countSharpeRatio(temp.fundRate,temp.rf)
     print "beta=",beta
     #for i in range(len(alpha)):
     #    print temp.date[i]+"对应的alpha值为",alpha[i]   
     print "目标基金特雷诺指数=",TreynorRatio(temp.fundRate,temp.rf,beta)

     
     
     #T-M回归模型（详情见指标数据归纳.pdf)
     y=ListSub(temp.fundRate,temp.rf)
     x1=ListSub(temp.rm,temp.rf)
     x2=ListSubSqare(temp.rm,temp.rf)
     obj_dict={'y':y,'x1':x1,'x2':x2}
     data=pd.DataFrame(obj_dict)#通过字典创建dataframe
     #data.to_csv("testfoo.csv")
     x=data[['x1','x2']]
     y=data['y']
     X_train,X_test, y_train, y_test = train_test_split(x, y, random_state=1)
     linreg = LinearRegression()  
     model=linreg.fit(X_train, y_train)  
     #print model    
     print '择股系数：',linreg.intercept_
     print '择时系数：',linreg.coef_[1]



     #计算某段时间内的各种基金指标
     id='000003' #前端点击查看某只基金的信息后，传来该基金的代码，赋值到这里，便可获取该基金的信息并计算各种风险因子
     fundDict[id]=getFund(id)
     temp=corrDate(fundDict[id].date,fundDict[id].dailyRate,rm.date,rm.dayRate,rf.date,rf.rfMonthly)
     temp.countByDate('2016-05-06','2017-08-01') #若要计算 某段时间内 的波动率，则要先调用这个函数，参数为开始和截至日期，类型为字符串，格式为'yyyy-mm-dd'(五月六号的5和6前的0不能省略）
     #然后用temp的属性计算出来的波动率就是这段时间内的了（前提是目标基金/市场收益率/无风险利率有这段时间内的数据）
     #计算方法同上
     beta=countBeta(temp.fundRate,temp.rm)
     alpha=countAlpha(temp.fundRate,temp.rm,temp.rf,beta)
     print "目标基金收益率序列2016-05-06至2017-08-01的年化收益率=",annualizedRate(fundDict[id].dailyRate)
     print "目标基金收益率序列2016-05-06至2017-08-01的年化波动率=",annualizedVolatility(temp.fundRate)
     print "目标基金收益率序列2016-05-06至2017-08-01的在险价值=",countValue_at_risk(annualizedVolatility(temp.fundRate))
     print "目标基金收益率序列2016-05-06至2017-08-01的收益率序列的下行标准差=",downsideStdDev(temp.fundRate,temp.rf)
     print "目标基金收益率序列2016-05-06至2017-08-01的夏普比=",countSharpeRatio(temp.fundRate,temp.rf)
     print "目标基金收益率序列2016-05-06至2017-08-01的beta=",beta
     #for i in range(len(alpha)):
     #    print temp.date[i]+"对应的alpha值为",alpha[i]
     print "目标基金收益率序列2016-05-06至2017-08-01的特雷诺指数=",TreynorRatio(temp.fundRate,temp.rf,beta)

     
     #T-M回归模型的计算也同上
     y=ListSub(temp.fundRate,temp.rf)
     x1=ListSub(temp.rm,temp.rf)
     x2=ListSubSqare(temp.rm,temp.rf)
     obj_dict={'y':y,'x1':x1,'x2':x2}
     data=pd.DataFrame(obj_dict)#通过字典创建dataframe
     #data.to_csv("testfoo.csv")
     x=data[['x1','x2']]
     y=data['y']
     X_train,X_test, y_train, y_test = train_test_split(x, y, random_state=1)
     linreg = LinearRegression()  
     model=linreg.fit(X_train, y_train)  
     #print model    
     print '择股系数：',linreg.intercept_
     print '择时系数：',linreg.coef_[1]






def test2(id,startTime,endTime,option):   #参数：基金的代码，查询的起始和结束日期（'yyyy-mm-dd'的字符串类型,option为字符串，选择查看哪一个因子
     rm=Rm('000001.csv')#读取市场数据
     #print rm.monthRate
     rf=Rf('Fund_RiskFree.csv','Treasury.xlsx')
     #print rf.date
     fundDict={}

     #计算某段时间内的各种基金指标

     fundDict[id]=getFund(id)
     temp=corrDate(fundDict[id].date,fundDict[id].dailyRate,rm.date,rm.dayRate,rf.date,rf.rfMonthly)
     
     
     startTime = datetime.strptime(startTime,'%Y-%m-%d')
     endTime = datetime.strptime(endTime,'%Y-%m-%d')
     days=(endTime-startTime).days

     alpha=[]
     beta=[]
     AnnualizedRate=[]
     AnnualizedVolatility=[]
     CountValue_at_risk=[]
     DownsideStdDev=[]
     sharpeRatio=[]
     treynorRatio=[]
     stockSelectionCoefficient=[]
     timeSelectionCoefficient=[]

     if('alpha'==option):
          temp.countByDate(startTime.strftime('%Y-%m-%d'),endTime.strftime('%Y-%m-%d'))
          alpha=countAlpha(temp.fundRate,temp.rm,temp.rf,countBeta(temp.fundRate,temp.rm))
          for i in range(len(alpha)):
               print temp.date[i],alpha[i]
     
     i=0
     while(i<=days):      
          temp=corrDate(fundDict[id].date,fundDict[id].dailyRate,rm.date,rm.dayRate,rf.date,rf.rfMonthly)
          curEndTime = startTime + timedelta(days=i)
          curStartTime=curEndTime - timedelta(days=365) #拿每一天和一年前的数据来算
          #print curStartTime
          temp.countByDate(curStartTime.strftime('%Y-%m-%d'),curEndTime.strftime('%Y-%m-%d'))
          
          if('beta'==option):              
               beta.append(countBeta(temp.fundRate,temp.rm))
               print curEndTime.strftime('%Y-%m-%d'),beta[-1]
               
          elif('annualizedRate'==option):
               AnnualizedRate.append(annualizedRate(temp.fundRate))
               print curEndTime.strftime('%Y-%m-%d'),AnnualizedRate[-1]
               
          elif('annualizedVolatility'==option):
               AnnualizedVolatility.append(annualizedVolatility(temp.fundRate))
               print curEndTime.strftime('%Y-%m-%d'),AnnualizedVolatility[-1]
               
          elif('countValue_at_risk'==option):
               CountValue_at_risk.append(countValue_at_risk(annualizedVolatility(temp.fundRate)))
               print curEndTime.strftime('%Y-%m-%d'),CountValue_at_risk[-1]
               
          elif('downsideStdDev'==option):
               DownsideStdDev.append(downsideStdDev(temp.fundRate,temp.rf))
               print curEndTime.strftime('%Y-%m-%d'),DownsideStdDev[-1]
               
          elif('sharpeRatio'==option):
               sharpeRatio.append(countSharpeRatio(temp.fundRate,temp.rf))
               print curEndTime.strftime('%Y-%m-%d'),sharpeRatio[-1]
               
          elif('treynorRatio'==option):
               tempBeta=countBeta(temp.fundRate,temp.rm)
               treynorRatio.append(TreynorRatio(temp.fundRate,temp.rf,tempBeta))
               print curEndTime.strftime('%Y-%m-%d'),treynorRatio[-1]
               
          elif('stockSelectionCoefficient'==option or 'timeSelectionCoefficient'==option):
               y=ListSub(temp.fundRate,temp.rf)
               x1=ListSub(temp.rm,temp.rf)
               x2=ListSubSqare(temp.rm,temp.rf)
               obj_dict={'y':y,'x1':x1,'x2':x2}
               data=pd.DataFrame(obj_dict)#通过字典创建dataframe
               x=data[['x1','x2']]
               y=data['y']
               X_train,X_test, y_train, y_test = train_test_split(x, y, random_state=1)
               linreg = LinearRegression()  
               model=linreg.fit(X_train, y_train)
               
               stockSelectionCoefficient.append(linreg.intercept_)
               timeSelectionCoefficient.append(linreg.coef_[1])
               if('stockSelectionCoefficient'==option):
                    print curEndTime.strftime('%Y-%m-%d'),linreg.intercept_
               else:
                    print curEndTime.strftime('%Y-%m-%d'),linreg.coef_[1]
               
          i+=1


         
                    
optionList=['alpha','beta','annualizedRate','annualizedVolatility','countValue_at_risk','downsideStdDev','sharpeRatio','treynorRatio','stockSelectionCoefficient','timeSelectionCoefficient']
test2(str(sys.argv[1]),'2013-03-22','2017-09-08',optionList[int(sys.argv[2])])


   

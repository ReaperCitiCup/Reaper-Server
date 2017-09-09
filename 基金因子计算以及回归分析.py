# -*- coding: utf-8 -*-
#!/usr/bin/env python
#@author: xiexian
import csv
import pymysql
import xlrd
from xlutils.copy import copy
import numpy
import pandas as pd
import math
from sklearn.cross_validation import train_test_split  #引用交叉验证
from sklearn.linear_model import LinearRegression  
import sys

#计算标准差
def standardDeviation(rate):
     return numpy.std(rate)

#计算下行标准差
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

#计算Beta值
def countBeta(resultRate,marketRate):
     x=resultRate
     y=marketRate
     xy=[]
     xyLen=min(len(x),len(y))
     for i in range(xyLen):
          xy.append(x[i]*y[i])
     xAvg=(float)(sum((x)))/len(x)
     yAvg=(float)(sum(y))/len(y)
     xyAvg=(float)(sum(xy))/len(xy)
     #Cov(x,y)=E[xy]-E[x]E[y]
     cov=xyAvg-xAvg*yAvg
     beta=(float)(cov)/numpy.var(marketRate)
     return beta


#计算Alpha
def countAlpha(resultRate,marketRate,rf,beta):
     alpha=[]
     alphaLen=min(len(resultRate),len(marketRate))
     for i in range(alphaLen):
          alpha.append((resultRate[i]-rf[i])-beta*(marketRate[i]-rf[i]))
     return alpha

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
        cur.execute('SELECT  distinct code FROM reaper.fund_netValue')
        data=cur.fetchall()
        code=[]    
        for d in data :
            code.append(str(d[0]))        
    except Exception :print("查询失败")
    return code

#基金类
class Fund:
     def __init__(self,code):
          self.code=code   #基金代码
          self.date=[]
          self.nav=[]   #单位净值
          self.dailyRate=[]   #日收益率       

#根据基金代码从数据库里获取某个基金的信息
def getFund(code):
    fund=Fund(code)
    try:
        conn=pymysql.connect(host='106.15.203.173',user='reaper',passwd='reaper112233',db='reaper',port=3306,charset='utf8')
        cur=conn.cursor()
        cur.execute('SELECT  date,unitNetValue,dailyRate FROM reaper.fund_netValue WHERE code='+code)
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
    except Exception :print(code+"查询失败")
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
             monthAgoPrice=(float)(filter(lambda ch: ch in '0123456789.', fundData[i+20][3]))
             self.closingPrice.append(curPrice)
             dateRate=(filter(lambda ch: ch in '-0123456789.',fundData[i][4]))
             if(''==dateRate):
                 dateRate='0'
             #print dateRate,self.date[-1]
             self.dayRate.append(float(dateRate)/100)
             self.monthRate.append((curPrice-monthAgoPrice)/monthAgoPrice/100)#设每月20个交易日
             i+=1


#无风险收益率对象，暂时是从csv文件读取数据
class Rf:
     def __init__(self,fileName):
          self.date=[]   
          self.rfDaily=[]
          self.rfWeekly=[]
          self.rfMonthly=[]
          self.rfYearly=[]

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
                    self.rfYearly.append(((float)(filter(lambda ch: ch in '0123456789.', rfYearly)))/100)
                    self.rfDaily.append(((float)(filter(lambda ch: ch in '0123456789.', rfDaily)))/100)
                    self.rfWeekly.append( ((float)(filter(lambda ch: ch in '0123456789.', rfWeekly)))/100)
                    self.rfMonthly.append(((float)(filter(lambda ch: ch in '0123456789.', rfMonthly)))/100)
                        

#测试函数
def test():
     rm=Rm('000001.csv')#读取市场数据
     #print rm.monthRate
     rf=Rf('Fund_RiskFree.csv')
     #print rf.rfDaily
     
     fundDict={} #基金字典，用于查询或管理基金，key为基金代码，value为Fund对象    
     #codeList=getCode()   
     #for code in codeList:
     #    fundDict[code]=getFund(code)

     print(sys.argv[1])
     code=str(sys.argv[1]) #前端点击查看某只基金的信息后，传来该基金的代码，赋值到这里，便可获取该基金的信息并计算各种风险因子
     fundDict[code]=getFund(code)
     
     temp=corrDate(fundDict[code].date,fundDict[code].dailyRate,rm.date,rm.dayRate,rf.date,rf.rfMonthly) 
        #统一日期，并返回统一后的日期序列和该日期序列对应的基金收益率序列/市场收益率序列/无风险利率序列,市场数据用的是日收益率，无风险利率用的是月收益率
     
     beta=countBeta(temp.fundRate,temp.rm)
     alpha=countAlpha(temp.fundRate,temp.rm,temp.rf,beta)
     print "beta=",beta
     for i in range(len(alpha)):
         print temp.date[i],alpha[i]
     print "目标基金净值序列的标准差=",standardDeviation(fundDict[code].nav)
     print "目标基金收益率序列的下行标准差=",downsideStdDev(temp.fundRate,temp.rf)
     
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
     print '截距：',linreg.intercept_  
     print '回归系数：',linreg.coef_


test()


   

# -*- coding: utf-8 -*-
# !/usr/bin/env python
# @author: xiexian
# 获得基金的全部数据计算结果，传递的第一个参数为0
# 获得基金的一段时间数据计算结果，传递的第一个参数为1
import math
import numpy
import pandas as pd
import pymysql
import sys
from sklearn.cross_validation import train_test_split  # 引用交叉验证
from sklearn.linear_model import LinearRegression

Number_Of_Trading_Days = 245  # 一年的交易日个数


# 计算标准差,参数类型：列表
def standardDeviation(rate):
    return numpy.std(rate)


# 计算下行标准差，参数类型：列表
def downsideStdDev(rate, rf):  # rf:risk-free rate
    rateLen = len(rate)
    smallerRate = []
    smallerLen = 0
    squareSum = 0
    for i in range(rateLen):
        if (rate[i] < rf[i]):
            smallerRate.append(rate[i])
            squareSum += (rate[i] - rf[i]) * (rate[i] - rf[i])
            smallerLen += 1
    if (smallerLen <= 1):
        return 0
    else:
        return math.sqrt(squareSum / (float)(smallerLen - 1))


# 计算协方差，参数类型：列表
def countCovariance(x, y):
    xy = []
    xyLen = min(len(x), len(y))
    for i in range(xyLen):
        xy.append(x[i] * y[i])
    xAvg = (float)(sum((x))) / len(x)
    yAvg = (float)(sum(y)) / len(y)
    xyAvg = (float)(sum(xy)) / len(xy)
    # Cov(x,y)=E[xy]-E[x]E[y]
    cov = xyAvg - xAvg * yAvg
    return cov


# 计算Beta值，参数类型：列表
def countBeta(resultRate, marketRate):
    x = resultRate
    y = marketRate
    cov = countCovariance(x, y)
    var = numpy.var(marketRate)
    if (0 == var):
        return 0
    beta = (float)(cov) / var
    return beta


# 计算Alpha，参数类型：前三个为列表，beta为数值
def countAlpha(resultRate, marketRate, rf, beta):
    alpha = []
    alphaLen = min(len(resultRate), len(marketRate))
    for i in range(alphaLen):
        alpha.append((resultRate[i] - rf[i]) - beta * (marketRate[i] - rf[i]))
    return alpha


# 计算夏普比，参数类型：列表
def countSharpeRatio(resultRate, rf):
    Erp = sum(resultRate) / len(resultRate)
    Erf = sum(rf) / len(rf)
    std = standardDeviation(resultRate)
    if (0 == std):
        return 0
    else:
        return (Erp - Erf) / std


# TODO unused
# 计算两个序列的相关系数，参数类型：列表
def countCorrelation(r1, r2):
    return countCovariance(r1, r2) / (numpy.std(r1) * numpy.std(r2))


# 计算在险价值，参数类型：数值
def countValue_at_risk(yearlySigam):
    return 2.33 * yearlySigam / math.sqrt(52)


# 计算年化波动率，参数类型：列表
def annualizedVolatility(r):
    return standardDeviation(r) * math.sqrt(Number_Of_Trading_Days)


# 计算年化收益率，参数类型：列表
def annualizedRate(dailyRate):
    result = 0
    countLen = 0
    while (countLen < len(dailyRate) and countLen < Number_Of_Trading_Days):
        result += dailyRate[countLen]
        countLen += 1
    return result / countLen * Number_Of_Trading_Days


# 计算特雷诺比率，参数类型：前两个为列表，beta为数值
def TreynorRatio(resultRate, rf, beta):
    Erp = sum(resultRate) / len(resultRate)
    Erf = sum(rf) / len(rf)
    return (Erp - Erf) / beta


# 求两个列表的差
def ListSub(l1, l2):
    rtn = []
    length = min(len(l1), len(l2))
    for i in range(length):
        rtn.append(l1[i] - l2[i])
    return rtn


def ListSubSqare(l1, l2):
    rtn = []
    length = min(len(l1), len(l2))
    for i in range(length):
        rtn.append((l1[i] - l2[i]) * (l1[i] - l2[i]))
    return rtn


# TODO unsed
# 获取数据库里基金的所有代码
def getCode():
    try:
        conn = pymysql.connect(host='106.15.203.173', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                               charset='utf8')
        cur = conn.cursor()  # 获取一个游标
        cur.execute('SELECT  distinct code FROM reaper.fund_netValue')
        data = cur.fetchall()
        code = []
        for d in data:
            code.append(str(d[0]))
    except Exception:
        print("查询失败")
    return code


# 基金类
class Fund:
    def __init__(self, code):
        self.code = code  # 基金代码
        self.date = []
        self.nav = []  # 单位净值
        self.dailyRate = []  # 日收益率


# 根据基金代码从数据库里获取某个基金的信息
def getFund(code):
    fund = Fund(code)
    try:
        conn = pymysql.connect(host='106.15.203.173', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                               charset='utf8')
        cur = conn.cursor()
        cur.execute('SELECT  date,unitNetValue,dailyRate FROM reaper.fund_netValue WHERE code=' + code)
        data = cur.fetchall()
        for d in data:
            fund.date.append((str(d[0]))[:10])  # 去掉时分秒
            nav = filter(lambda ch: ch in '0123456789.', str(d[1]))
            if ('' == nav):
                appNav = sum(fund.nav[-11:-1]) / 10
                fund.nav.append(appNav)
            else:
                fund.nav.append(float(nav))

            dailyRate = filter(lambda ch: ch in '-0123456789.', str(d[2]))
            if ('' == dailyRate):
                dIndex = data.index(d)
                appDailyRate = 0
                if (dIndex + 1 < len(data)):
                    yesterdayNav = filter(lambda ch: ch in '-0123456789.', str(data[dIndex + 1][1]))
                    if (yesterdayNav != ''):
                        yesterdayNav = float(yesterdayNav)
                        appDailyRate = (fund.nav[-1] - yesterdayNav) / yesterdayNav
                    else:
                        pass
                else:
                    appDailyRate = sum(fund.dailyRate[-11:-1]) / 10

                fund.dailyRate.append(appDailyRate)
            else:
                fund.dailyRate.append((float(dailyRate)) / 100)  # 数据库里的利率省略了百分号的，除回来
                # print (float(d[2]))/100
    except Exception:
        print(code + "查询失败")
    return fund


# 解决日期与收益率以及净值的对应问题，返回的对象的属性包括：
# 各个日期序列的并值，以及该时间序列对应的基金收益率，市场收益率和无风险利率(通过相同的下标对应，如fundRate[i],rm[i],rf[i]同为date[i]这一天的数据）
class corrDate:
    def __init__(self, date1, l1, date2, l2, date3=0, l3=0):
        self.date = []  # date1，date2，date3这三个日期序列的并值
        self.fundRate = []
        self.rm = []
        self.rf = []
        if (0 == date3):  # 只有两个时间序列的情况
            i1 = 0
            i2 = 0
            while (i1 < len(date1) and i2 < len(date2)):
                if (date1[i1] > date2[i2]):
                    while (i1 < len(date1) and date1[i1] > date2[i2]):
                        i1 += 1
                else:
                    while (i2 < len(date2) and date2[i2] > date1[i1]):
                        i2 += 1

                if (i1 < len(date1) and i2 < len(date2)):
                    # 此处有date1[i1]=date2[i2]
                    self.date.append(date1[i1])
                    self.fundRate.append(l1[i1])
                    self.rm.append(l2[i2])
                    i1 += 1
                    i2 += 1
        else:  # 三个时间序列
            i1 = 0
            i2 = 0
            i3 = 0
            while (i1 < len(date1) and i2 < len(date2) and i3 < len(date3)):
                if ((date1[i1] < date2[i2] or date1[i1] == date2[i2]) and (
                                date1[i1] < date3[i3] or date1[i1] == date3[i3])):
                    while (i2 < len(date2) and date1[i1] < date2[i2]):
                        i2 += 1
                    while (i3 < len(date3) and date1[i1] < date3[i3]):
                        i3 += 1

                elif ((date2[i2] < date1[i1] or date2[i2] == date1[i1]) and (
                                date2[i2] < date3[i3] or date2[i2] == date3[i3])):
                    while (i1 < len(date1) and date2[i2] < date1[i1]):
                        i1 += 1
                    while (i3 < len(date3) and date2[i2] < date3[i3]):
                        i3 += 1

                else:
                    while (i1 < len(date1) and date3[i3] < date1[i1]):
                        i1 += 1
                    while (i2 < len(date2) and date3[i3] < date2[i2]):
                        i2 += 1

                if (i1 < len(date1) and i2 < len(date2) and i3 < len(date3)):
                    self.date.append(date1[i1])
                    self.fundRate.append(l1[i1])
                    self.rm.append(l2[i2])
                    self.rf.append(l3[i3])
                    i1 += 1
                    i2 += 1
                    i3 += 1
                    # 按起始时间计算(如用户要求计算某一段时间的波动率）

    def countByDate(self, startTime, endTime):
        i = 0
        tempDate = []
        tempFundRate = []
        tempRm = []
        tempRf = []
        while (i < len(self.date) and self.date[i] > endTime):
            i += 1
        while (i < len(self.date) and self.date[i] >= startTime):
            tempDate.append(self.date[i])
            tempFundRate.append(self.fundRate[i])
            tempRm.append(self.rm[i])
            tempRf.append(self.rf[i])
            i += 1

        self.date = tempDate
        self.fundRate = tempFundRate
        self.rm = tempRm
        self.rf = tempRf


# 市场收益率对象，从数据库里读取数据（日更新）
class Rm:
    def __init__(self):
        self.date = []
        self.closingPrice = []
        self.dayRate = []
        self.monthRate = []

        conn = pymysql.connect(host='106.15.203.173', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                               charset='utf8')
        cur = conn.cursor()
        cur.execute('SELECT  date,beforeClosePrice,closePrice FROM basic_stock_index where stockId="000001"')
        data = cur.fetchall()
        dataLen = len(data)
        i = 0

        while (i + 20 < dataLen):
            d = data[i]
            self.date.append((filter(lambda ch: ch in '-0123456789', str(d[0].strftime('%Y-%m-%d')))))
            beforePrice = (float)(filter(lambda ch: ch in '0123456789.', str(d[1])))
            curPrice = (float)(filter(lambda ch: ch in '0123456789.', str(d[2])))
            monthAgoPrice = (float)(filter(lambda ch: ch in '0123456789.', str(data[i + 20][2])))  # 设每月20个交易日
            self.closingPrice.append(curPrice)
            dateRate = (curPrice - beforePrice) / beforePrice
            if ('' == dateRate):
                dateRate = '0'
                # print dateRate,self.date[-1]
            self.dayRate.append(float(dateRate))
            self.monthRate.append((curPrice - monthAgoPrice) / monthAgoPrice)
            i += 1


# 无风险收益率对象，暂时是从csv文件读取数据
class Rf:
    def __init__(self):
        self.date = []
        self.rfDaily = []
        self.rfWeekly = []
        self.rfMonthly = []
        self.rfYearly = []

        conn = pymysql.connect(host='114.212.234.27', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                               charset='utf8')
        cur = conn.cursor()

        cur.execute('SELECT date,closePrice,priceFluctuation from basic_stock_index where stockId="000012"')
        data = cur.fetchall()
        dataLen = len(data)
        i = 0
        date = data[i][0].strftime('%Y-%m-%d')
        while (date > '2016-12-30'):
            self.date.append(date)
            self.rfDaily.append(data[i][2])
            self.rfWeekly.append((data[i][1] - data[i + 5][1]) / data[i + 5][1])
            self.rfMonthly.append((data[i][1] - data[i + 20][1]) / data[i + 20][1])
            self.rfYearly.append(
                (data[i][1] - data[i + Number_Of_Trading_Days][1]) / data[i + Number_Of_Trading_Days][1])
            i += 1
            date = data[i][0].strftime('%Y-%m-%d')

        cur.execute('SELECT date,rfYearly,rfDaily,rfWeekly,rfMonthly from rf')
        data = cur.fetchall()
        dataLen = len(data)
        for d in data:
            self.date.append(filter(lambda ch: ch in '0123456789-', str(d[0])))
            self.rfYearly.append(((float)(filter(lambda ch: ch in '-0123456789.', str(d[1])))) / 100)
            self.rfDaily.append(((float)(filter(lambda ch: ch in '-0123456789.', str(d[2])))) / 100)
            self.rfWeekly.append(((float)(filter(lambda ch: ch in '-0123456789.', str(d[3])))) / 100)
            self.rfMonthly.append(((float)(filter(lambda ch: ch in '-0123456789.', str(d[4])))) / 100)


# TODO unused
# 构造基金组合，待续
def fundGroup():
    fundDict = {}
    codeList = []  # 前端应该传来的用于构造基金组合的数据
    pencentage = []  # codeList为用户所选的组合中所用基金的代码（列表类型），pencentage为组合中所用基金占组合的百分比（也为列表类型，与codeList通过下标对应，如pencentage[i]表示基金代码为codeList[i]的基金占组合的百分比）
    for code in codeList:
        fundDict[code] = getFund(code)  # 获取组合中各个基金的信息

    myFundGroup = Fund("myFundGroup")  # 创建一个空的基金组合对象，加权平均后的数据可放到这个对象中
    # 。。。


# 测试函数
def test():
    rm = Rm()  # 读取市场数据
    # print rm.monthRate
    rf = Rf()
    # print rf.date
    fundDict = {}  # 基金字典，用于查询或管理基金，key为基金代码，value为Fund对象
    # codeList=getCode()
    # for code in codeList:
    #    fundDict[code]=getFund(code)
    if int(sys.argv[1]) == 0:
        lists = [[]] * (len(sys.argv) - 2)
        for i in range(2, len(sys.argv)):
            code = str(sys.argv[i])  # 前端点击查看某只基金的信息后，传来该基金的代码，赋值到这里，便可获取该基金的信息并计算各种风险因子
            fundDict[code] = getFund(code)

            temp = corrDate(fundDict[code].date, fundDict[code].dailyRate, rm.date, rm.dayRate, rf.date, rf.rfMonthly)
            # 统一日期，并返回统一后的日期序列和该日期序列对应的基金收益率序列/市场收益率序列/无风险利率序列,市场数据用的是日收益率，无风险利率用的是月收益率

            beta = countBeta(temp.fundRate, temp.rm)
            alpha = countAlpha(temp.fundRate, temp.rm, temp.rf, beta)

            print "*", code, "年化收益率=", annualizedRate(fundDict[code].dailyRate)
            print "*", code, "年化波动率=", annualizedVolatility(temp.fundRate)
            print "*", code, "在险价值=", countValue_at_risk(annualizedVolatility(temp.fundRate))
            print "*", code, "收益率序列的下行标准差=", downsideStdDev(temp.fundRate, temp.rf)
            print "*", code, "夏普比=", countSharpeRatio(temp.fundRate, temp.rf)
            print "*", code, "beta=", beta
            # for i in range(len(alpha)):
            # print temp.date[i]+"对应的alpha值为",alpha[i]
            print "*", code, "特雷诺指数=", TreynorRatio(temp.fundRate, temp.rf, beta)

            # T-M回归模型（详情见指标数据归纳.pdf)
            y = ListSub(temp.fundRate, temp.rf)
            x1 = ListSub(temp.rm, temp.rf)
            x2 = ListSubSqare(temp.rm, temp.rf)
            obj_dict = {'y': y, 'x1': x1, 'x2': x2}
            data = pd.DataFrame(obj_dict)  # 通过字典创建dataframe
            # data.to_csv("testfoo.csv")
            x = data[['x1', 'x2']]
            y = data['y']
            X_train, X_test, y_train, y_test = train_test_split(x, y, random_state=1)
            linreg = LinearRegression()
            model = linreg.fit(X_train, y_train)
            # print model
            print "*", code, "择股系数=", linreg.intercept_
            print "*", code, "择时系数=", linreg.coef_[1]
            # for j in range(0, len(lists)):
            #     for k in range(j + 1, len(lists)):
            #         print "##", str(sys.argv[j + 2]), str(sys.argv[k + 2]), countCorrelation(lists[j], lists[k])

    if int(sys.argv[1]) == 1:
        # 计算某段时间内的各种基金指标
        lists = [[]] * (len(sys.argv) - 4)
        before = str(sys.argv[2])
        after = str(sys.argv[3])
        for i in range(4, len(sys.argv)):
            code = str(sys.argv[i])

            # code = '000003'  # 前端点击查看某只基金的信息后，传来该基金的代码，赋值到这里，便可获取该基金的信息并计算各种风险因子
            fundDict[code] = getFund(code)
            # -----------------------------------------------------------------------------------
            lists[i - 4] = fundDict[code].dailyRate
            # -----------------------------------------------------------------------------------
            temp = corrDate(fundDict[code].date, fundDict[code].dailyRate, rm.date, rm.dayRate, rf.date, rf.rfMonthly)
            temp.countByDate(before,
                             after)  # 若要计算 某段时间内 的波动率，则要先调用这个函数，参数为开始和截至日期，类型为字符串，格式为'yyyy-mm-dd'(五月六号的5和6前的0不能省略）
            # 然后用temp的属性计算出来的波动率就是这段时间内的了（前提是目标基金/市场收益率/无风险利率有这段时间内的数据）
            # 计算方法同上
            beta = countBeta(temp.fundRate, temp.rm)
            alpha = countAlpha(temp.fundRate, temp.rm, temp.rf, beta)
            # -----------------------------------------------------------------------------------
            print "#", code, "年化收益率=", annualizedRate(fundDict[code].dailyRate)
            print "#", code, "年化波动率=", annualizedVolatility(temp.fundRate)
            print "#", code, "在险价值=", countValue_at_risk(annualizedVolatility(temp.fundRate))
            print "#", code, "收益率序列的下行标准差=", downsideStdDev(temp.fundRate, temp.rf)
            print "#", code, "夏普比=", countSharpeRatio(temp.fundRate, temp.rf)
            print "#", code, "beta=", beta
            print "#", code, "特雷诺指数=", TreynorRatio(temp.fundRate, temp.rf, beta)
            # -----------------------------------------------------------------------------------

            # T-M回归模型的计算也同上
            y = ListSub(temp.fundRate, temp.rf)
            x1 = ListSub(temp.rm, temp.rf)
            x2 = ListSubSqare(temp.rm, temp.rf)
            obj_dict = {'y': y, 'x1': x1, 'x2': x2}
            data = pd.DataFrame(obj_dict)  # 通过字典创建dataframe
            # data.to_csv("testfoo.csv")
            x = data[['x1', 'x2']]
            y = data['y']
            X_train, X_test, y_train, y_test = train_test_split(x, y, random_state=1)
            linreg = LinearRegression()
            model = linreg.fit(X_train, y_train)
            # print model
            print "#", code, "择股系数=", linreg.intercept_
            print "#", code, "择时系数=", linreg.coef_[1]

        for j in range(0, len(lists)):
            for k in range(j + 1, len(lists)):
                print "#", str(sys.argv[j + 4]), str(sys.argv[k + 4]), countCorrelation(lists[j], lists[k])


test()

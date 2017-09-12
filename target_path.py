# -*- coding: utf-8 -*-
"""
Created on Sun Sep 10 10:48:40 2017

@author: Administrator
"""

import numpy as np
import pymysql
import sys


def sqlByType(type_kind):
    if type_kind == 1:
        fund_type = 'type1=\'债券型\' or type1= \'债券指数\' or type1= \'保本型\''
    elif type_kind == 2:
        fund_type = 'type1= \'股票型\' or type1= \'股票指数\' or type1= \'QDII-指数\' '
    elif type_kind == 3:
        fund_type = 'type1= \'混合型\' '
    else:
        print("invalid type")
    try:
        conn = pymysql.connect(host='106.15.203.173', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                               charset='utf8')
        cur = conn.cursor()  # 获取一个游标

        cur.execute('SELECT  code FROM reaper.fund WHERE ' + fund_type)
        data = cur.fetchall()

        data_code = [];

        for d in data:
            data_code.append(int(d[0]))
            # data_date.append(str(d[1]))
    except Exception:
        print("查询失败")
    return data_code


def sqlByFactor(factor_kind):
    data_code = [];
    try:
        conn = pymysql.connect(host='106.15.203.173', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                               charset='utf8')
        cur = conn.cursor()  # 获取一个游标

        cur.execute('SELECT code FROM reaper.factor_result WHERE max2=' + str(int(factor_kind)))
        data = cur.fetchall()

        for d in data:
            data_code.append(int(d[0]))
            # data_date.append(str(d[1]))
    except Exception:
        print("查询失败")
    return data_code


def sqlOrder(data_code, lamda=5, count=8):
    conn = pymysql.connect(host='106.15.203.173', user='reaper', passwd='reaper112233', db='reaper', port=3306,
                           charset='utf8')
    cur = conn.cursor()  # 获取一个游标
    cur.execute('SELECT code,rank' + str(int(lamda)) + ' FROM reaper.fund_score WHERE 1')
    data = cur.fetchall()
    data_score = []
    for d in data:
        if int(d[0]) in data_code:
            data_score.append([int(d[0]), float(d[1])])

    data_score = np.array(data_score)
    data_score = data_score[np.lexsort(-data_score.T)]
    code = data_score[0:count, 0]
    code = code.tolist()
    return code


# lamda是彩虹条的那个选择
# count资产间分散为10，因子间分散为3
# sqlkind 1为资产减分散，2为因子间分散
# typekind只在资产减分散的时候要用，1债券型，2股票型，3混合型 要分三次调用
# factorkind只在因子间分散时要用，对应关系暂时不确定 要分10次调用
def sqlCode(lamda=5, count=8, sqlkind=1, type_kind=1, factor_kind=1):
    # count 输出基金的数量
    # lamda 风险偏好 范围 整数1-10
    # sqlkind 筛选方式 1：按基金类型筛选（资产间分散） 2：因子间分散
    # type_kind 资产种类 范围 整数1-3
    # factor_kind 因子种类 范围 整数1-10
    if sqlkind == 1:
        code = sqlByType(type_kind)
        code = sqlOrder(code, lamda, count)
    elif sqlkind == 2:
        code = sqlByFactor(factor_kind)
        code = sqlOrder(code, lamda, count)

    return code


# print(sqlByType(1))
# print(sqlByFactor(6))
# print(sqlOrder(sqlByType(1),1))
# print(sqlCode())
# a=sqlOrder(sqlByType(1),1)

lamda = int(sys.argv[1])
count = int(sys.argv[2])
sqlkind = int(sys.argv[3])
type_kind = int(sys.argv[4])
factor_kind = int(sys.argv[5])
print sqlCode(lamda, count, sqlkind, type_kind, factor_kind)

# coding=utf-8
import sklearn.cluster
import numpy as np
import sys


def hierarchicalClustering(fund_data):
     fundsData=[]

     for i in range(len(fund_data)):
          fundsData.append(fund_data[i][1:]) 

     fundsData=np.array(fundsData)
     
     clusters=sklearn.cluster.AgglomerativeClustering(n_clusters=1, affinity='euclidean', memory=None, connectivity=None, linkage='ward')

     orderIndex=[]
     for i in range(len(clusters.fit(fundsData).children_)):
          for j in range(len(clusters.fit(fundsData).children_[i])):
               if(clusters.fit(fundsData).children_[i][j]<len(fundsData)):
                    orderIndex.append(clusters.fit(fundsData).children_[i][j])
     fundOrder=[]
     factorOrder=[]
     for i in range(len(fund_data)):
          fundOrder.append(fund_data[orderIndex[i]][0])

     j=1
     while(j<len(fund_data[0])):
          curFactorOrder=[]
          for i in range(len(fund_data)):
               curFactorOrder.append(fund_data[orderIndex[i]][j])
          factorOrder.append(curFactorOrder)
          j+=1

     print ' '.join(fundOrder)

     for i in range(1,len(factorOrder)):
          print ' '.join(str(f) for f in factorOrder[i])

fund_data=[]
#读取个数
n=int(sys.argv[1])
#读取每个代码以及后面的10个参数
for i in range(n):
     data=[]
     data.append(str(sys.argv[2+i*11]))
     for j in range(11):
          data.append(float(sys.argv[2+i*11+j]))
     fund_data.append(data)

# fund_data.append(['000003',-0.0719,0.069663935,0.022509314])#基金数据，第一个为基金代码，后面为要聚类的数据（传来的10个风格因子，现在设的只是样例数据）
# fund_data.append(['000004',0.0177,0.019897408,0.006429108])
# fund_data.append(['000007',0.0102,0.10581899,0.034191477])
# fund_data.append(['000017',-0.0063,0.012374808,0.00399846])
# fund_data.append(['000024',0.0538,0.070886282,0.02290427])
# fund_data.append(['000025',0.163,0.084955376,0.027450175])

hierarchicalClustering(fund_data) #层次聚类

# HotelManage
由Java写成，自行编写类以实现基本数据结构。并应用爬山算法解决了路径选择问题。<br>
Written in Java. Implement on my own to achieve the functionality of basic data structures. Hill-climbing algorithm was used to solved path selection problem.<br>

# 包括有以下数据结构的实现（均体现在.java文件中）：
Vector, Linked List, Stack, Queue, Graph<br><br>

此处解决的是一个酒店管理问题。操作者需要能对房间和客户进行增删改查，并安排客户入住和退房。<br>
酒店有多个Building, 而路径选择问题需要基于不同楼之间的距离抽象为Graph（例如采用邻接矩阵的形式），并利用算法得出最短的路径，以获取所有Building之间巡游一周的最短代价，并输出路径。

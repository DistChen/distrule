-----------------------------------
![github](http://3.su.bdimg.com/icon/9898.png "")[�ҵ�����΢��](http://weibo.com/ooqingkuangoo/)
![github](http://4.su.bdimg.com/icon/7083.png "")[�ҵ�QQ�ռ�](http://user.qzone.qq.com/365061362/main/)
![github](http://3.su.bdimg.com/icon/2602.png?1 "")[�ҵ���Ѷ΢��](http://t.qq.com/cyp365061362/)

    ��Instroduce��  
    ���幦��˵��:���ں�̨���ô洢���̵�����ʽʵ�֣���ҵ����������������
    1�����ڵ�Ϊprocedures
    2��ÿһ���洢���̶�Ӧһ�� procedure �ڵ㣬procedure�ڵ�Ϊprocedure�ӽڵ�
    3��procedure �ڵ㹲��name��desc��proName��executeClass��excuteMethod��parameters�����ӽڵ㣬����desc��executeClass��excuteMethod����Ϊ��ѡ�����Ϊ��ѡ�� 
    	name����ѡ���������ǰ�˵�����Ҫ����ֵ
    	desc����ѡ��ù��ܵ�������Ϣ
    	proName����ѡ��洢������
    	executeClass����ѡ��洢���̵�ִ���࣬Ĭ�ϵ�ִ����Ϊdist.common.procedure.define.ProcedureExecutor��
    		      ��ִ�����޷��Դ����ݿ��з��ص�����������ҵ����������Ҫ�Դ����ݿⷵ�ص�������
    		      ���δ�����̳и��࣬Ȼ����д handerResult �������÷�������μ�Ϊ�����ݿⷵ��
    		      �����ݡ�
    	executeMethod����ѡ��ִ�����Ĭ��ִ�з�����Ĭ�Ϸ���Ϊ execute��Ҳ��Ϊ������ο�������
    	parmeters����ѡ��洢���̲���������˳��Ҫ���ֶ�Ӧ��������0�������ӽڵ� parameter 
    4��paramters �ڵ���� parameter �ڵ㣬parameter �ڵ�������У�
       name������������洢���̶�Ӧ����ѡ
       type��ȡֵ in ���� out�������ò���Ϊ���뻹�Ǵ�������ѡ
       dataType���������ͣ�����varchar�����ַ�����date �����������Ͳ���,cursor �����α꣬Ҳ���Դ�����ֵ������varchar=4  date=91
       vo���� type="out" dataType="cursor" ʱ����Ҫ���ø�ֵ
       format����dataType="date" ʱ���������ø�ֵ��Ĭ��Ϊ yyyy/MM/dd ,����ǰ�˴���ֵΪ 2010-08-22�����������format="yyyy-MM-dd"
    5��datasource �ڵ�������������Դ

### һ��ʾ����������:

    <?xml version="1.0" encoding="UTF-8"?>
    <procedures xmlns="http://www.dist.com.cn"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.dist.com.cn distprocedure.xsd">
        <datasource src="config.properties">
            <driver>${driverClassName}</driver>
            <url>${url}</url>
            <username>${username}</username>
            <password>${password}</password>
        </datasource>
        <procedure id="testPro">
            <proName>pro_test</proName>
            <parameters>
                <parameter name="p_str" type="in" dataType="varchar"/>
                <parameter name="p_str2" type="in" dataType="varchar"/>
                <parameter name="p_num" type="in" dataType="number" />
                <parameter name="p_strDate" type="in" dataType="date" format="yyyy-MM-dd" />
                <parameter name="p_date" type="in" dataType="date" />
                <parameter name="p_info" type="out" dataType="varchar" />
                <parameter name="p_cursor" type="out" dataType="cursor" vo="dist.dgp.controller.Person"/>
            </parameters>
        </procedure>
    </procedures>
    
### �汾����˵��:

        -------------------1.0.1-replease--------------------
        1��֧��ע�ͽڵ�Ľ���
        2��֧�����ö�������ļ��������Ӧ�Ŀ��������е�features������������:
             p:features="features1.xml features2.xml features3.xml"
        3��name��desc��proName��executeClass��excuteMethod��parameters ���Դ�СдӰ��
        
        -------------------1.0.2-replease--------------------
        1���������������ļ��еĿո�Ӱ�죬�������µ�����:
           <proName>importCatalog</proName> �� <proName>  importCatalog    </proName>
           �������������ǵ�Ч�ġ���1.0.2-replease�汾֮ǰ�������÷�ʽ��Ч
        2��֧������ format ���ԣ��Զ�����洢�������������͵Ĳ������ַ�����ʽ�����ݵ��������ǰ�˴�һ���ַ�������������ʱ������ format ����ֵΪǰ��ֵ��ʽ��Ĭ��ֵΪ yyyy/MM/dd
           ����ֵΪ��2010-08-22��ʱ������format=��yyyy-MM-dd��,��ֵΪ��2010/08/22��,���Բ������� format ����
           ע��:ֻ�� dataType="date" ���͵Ĳ�������Ҫ format ����
        3��֧������ desc �ڵ㣬desc �ڵ������Դ洢���̽�������
        4�������ļ�·�����⣬��ǰֻ�ܼ�����·���µ������ļ������ڿ���ͬʱ������·�����ļ�ϵͳ�µ������ļ������ҿ����Կո�Ͷ��ŷָ�������:
           p:features="features1.xml,F:\MyEclipseWorkSpace\SZMDServer\src\features.xml features3.xml"
           
        -------------------1.1.0-replease--------------------
        1������� spring ������
        2������ datasource �ڵ�
        3������ProcedureRepository�洢���ֿ̲�
        4������ProcedureListener�����ļ�������

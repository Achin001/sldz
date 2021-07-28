FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

ADD pom.xml /tmp/build/

ADD src /tmp/build/src
        #构建应用
RUN cd /tmp/build && mvn clean package \
        #拷贝编译结果到指定目录
        && mv target/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build


#使用亚洲上海时间
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone


VOLUME /tmp
EXPOSE 83
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","/app.jar"]

ENTRYPOINT ["java","-Djasypt.encryptor.password=opwsaL.afcfa18avC1","-jar","app.jar"]
ENTRYPOINT ["java","-jar","app.jar","--jasypt.encryptor.password=opwsaL.afcfa18avC1"]



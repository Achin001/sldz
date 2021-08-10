FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8
VOLUME /tmp
ADD sldz-0.0.1.jar app.jar

#使用亚洲上海时间
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone



ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","/app.jar"]

ENTRYPOINT ["java","-Djasypt.encryptor.password=opwsaL.afcfa18avC1","-jar","app.jar"]
ENTRYPOINT ["java","-jar","app.jar","--jasypt.encryptor.password=opwsaL.afcfa18avC1"]

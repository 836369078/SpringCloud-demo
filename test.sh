#!/bin/sh
#进入文件根目录
cd "$CI_PROJECT_DIR"

#项目打包
mvn -Dmaven.test.skip=true package

#进入target文件夹
cd ./target

#创建Dockerfile文件
cat << EOF > Dockerfile
FROM registry.cn-hangzhou.aliyuncs.com/java-jdk/openjdk:jdk8
MAINTAINER LINJINP
VOLUME /tmp
LABEL app="test1" version="v1" by="linjinp"
COPY boot.jar boot.jar
EXPOSE 8088
CMD  java -jar boot.jar
EOF

#创建镜像
sudo docker build -t 10.10.111.117:31409/springboot .

#上传镜像
sudo docker push 10.10.111.117:31409/springboot

#创建K8S配置文件
filename=`test-1.yaml`
cat << EOF > $filename
kind: Deployment
apiVersion: apps/v1
metadata:
  labels:
   app: test-1-depolyment
  name: test-1-depoyment
  namespace: default
spec:
  replicas: 1
  selector:
    matchLabels:
      app: test-1-depolyment
  template:
    metadata:
      labels:
        app: test-1-depolyment
    spec:
      containers:
      - name: test-1-depolyment
        image: 10.10.111.117:31409/springboot
        ports:
        - containerPort: 8080 #运行端口
          protocol: TCP
---
kind: Service
apiVersion: v1
metadata:
  labels:
    app: test-1-depoyment
  name: test-1-depoyment
  namespace: default
spec:
  type: NodePort
  ports:
    - port: 8089 #内部开放端口
      targetPort: 8888 #项目端口
      nodePort: 8000 #外部开放端口
  selector:
    app: test-1-depoyment
EOF

#如果服务不存在，创建服务
exist=`kubectl get svc test-1-depolyment`
if [! -n $exist ];then
  kubectl create -f $filename
fi
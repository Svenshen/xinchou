FROM adoptopenjdk/openjdk11-openj9:alpine-jre

LABEL AUTHOR="szh" \
      VERSION=1.0.1

# FROM # 基础镜像 比如node
# MAINTAINER # 镜像是谁写的 姓名+邮箱(MAINTAINER已经被废弃)
# LABEL # 给镜像添加一些元数据
# ARG # 构建参数,与 ENV 作用一至.不过作用域不一样。ARG设置的环境变量仅对Dockerfile内有效,也就是说只有docker build的过程中有效，构建好的镜像内不存在此环境变量
# RUN # 镜像构建时需要运行的命令
# ADD # 添加，比如添加一个tomcat压缩包
# WORKDIR # 镜像的工作目录
# VOLUME # 挂载的目录
# EXPOSE # 指定暴露端口，跟-p一个道理
# RUN # 最终要运行的
# CMD # 指定这个容器启动的时候要运行的命令，只有最后一个会生效，而且可被替代
# ENTRYPOINT # 指定这个容器启动的时候要运行的命令，可以追加命令
# ONBUILD # 当构建一个被继承Dockerfile 这个时候运行ONBUILD指定，触发指令
# COPY # 将文件拷贝到镜像中
# ENV # 构建的时候设置环境变量

# ====================================================================
# Add proxy, using --build-arg "HTTP_PROXY=http://192.168.1.100:7890"

# set -e ： -e这个参数的含义是,当命令发生错误的时候,停止脚本的执行
# set -x ： -x参数的作用是把将要运行的命令用一个+标记之后显示出来
# echo -e ：激活转义字符
# echo -n ：不换行输出
# apk : 软件包管理工具
# mkdir -p ：递归创建目录，即使上级目录不存在，会按目录层级自动创建目录
# git clone -b : 克隆指定的分支 git clone -b 分支名 仓库地址

# 设置环境变量，容器内部使用

RUN set -ex \
    && echo -e "https://mirrors.ustc.edu.cn/alpine/latest-stable/main\nhttps://mirrors.ustc.edu.cn/alpine/latest-stable/community" > /etc/apk/repositories \
    && apk update \
    && apk upgrade \
    && apk add --no-cache bash tzdata git moreutils curl jq openssh-client \
    && apk add --no-cache make gcc g++ python3-dev pkgconfig pixman-dev cairo-dev pango-dev libjpeg-turbo-dev giflib-dev \
    && apk add nodejs npm \
    && rm -rf /var/cache/apk/* \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && mkdir -p /root/.ssh \
    && echo -e $KEY > /root/.ssh/id_rsa \
    && chmod 600 /root/.ssh/id_rsa \
    && npm config set registry https://registry.npm.taobao.org 
#Add pip install mirror:

RUN echo "[global] \
    index-url = https://mirrors.aliyun.com/pypi/simple \
    trusted-host = mirrors.aliyun.com \
    timeout = 120 \
    " > /etc/pip.conf
# ====================================================================
# RUN pip install --upgrade pip 
# ====================================================================

# ====================================================================
VOLUME /tmp
#ARG JAR_FILE
#RUN apk add -U tzdata
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone && mkdir /app
#COPY ${JAR_FILE} app.jar

COPY app.jar /app/app.jar
EXPOSE 8080
ENV LANG C.UTF-8
workdir /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar","-Xmx5000m"]

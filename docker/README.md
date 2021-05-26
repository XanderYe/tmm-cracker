# tinymediamanager-docker 4.1.5.1

## 描述

基于 [romancin/tinymediamanager](https://github.com/romancin/tinymediamanager-docker) Dockerfile，打包的破解的tmm4.1.5.1 docker镜像

## 获取镜像

### 1.自己编译
执行 `docker build -t xanderye/tinymediamanager:4.1.5.1 .`

### 2.从dockerhub pull
2.执行 `docker pull xanderye/tinymediamanager:4.1.5.1`

## 使用方法

### 映射卷路径自己修改

```
docker run \
    --name=tinyMediaManager \
    -v /share/Container/tinymediamanager/config:/config \
    -v /share/Container/tinymediamanager/media:/media \
    -e GROUP_ID=0 -e USER_ID=0 -e TZ=Asia/Shanghai \
    -p 5800:5800 \
    -p 5900:5900 \
    --add-host=api.themoviedb.org:13.224.161.90 \
    --add-host=image.tmdb.org:104.16.61.155 \
    --add-host=api.themoviedb.org:13.35.67.86 \
    --add-host=www.themoviedb.org:54.192.151.79 \
    xanderye/tinymediamanager:4.1.5.1
```
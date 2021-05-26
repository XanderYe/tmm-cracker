# tinymediamanager 破解

## 最新

已更新tmm_4.1.5.1_win，详情查看博客。

## 描述

tinymediamanager 4.0.6 破解程序，去除50部电影、10部电视剧的限制，仅供学习交流，严禁用于商业用途，请于24小时内删除。

## 注意事项

1. 由于类名存在Windows系统保留关键字，需要在linux下运行。
2. macOS默认大小写不敏感，也会导致生成的tmm.jar无法运行。
3. 需要安装jdk环境。

## 使用说明

1. `git clone https://github.com/XanderYe/tmm-cracker.git`
2. 执行 `mvn -Dfile.encoding=UTF-8 clean install assembly:single`
3. 复制 target/tmm-cracker-jar-with-dependencies.jar 到 linux 系统中
4. 提取官网下载的tmm_版本_win.zip中的tmm.jar，目录结构为<br/>
|--tmm.jar<br/>
|--tmm-cracker-jar-with-dependencies.jar<br/>
5. 确认安装了jdk并配置了环境变量，保证jar命令可用
6. 执行 `java -jar tmm-cracker-jar-with-dependencies.jar`，生成 tmm-cracked.jar，提取出来替换tmm.jar即可

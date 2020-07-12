#!/bin/bash
# 执用本脚本时，必须使用绝对路径,必须位于当前目录执行 ！
# 使用方法：
# 1. curl cip.cc 查看本机IP地址
# 2. 将IP地址发送给服务器运维人员，让其协助添加防火墙
# 3. 找运维人员获取服务器对应的用户名
# 4. 执行 bash cd.sh -u <用户名>  完成后台的自动发布。

# 帮助函数
helpFunction() {
  echo ""
  echo "Usage: $0 -u uesrname"
  echo -e "\t-u the username of the serivce"
  exit 1 # Exit script after printing help
}

# 获取某个目录下的*.jar文件
# 调用方法 xxx=$(getExecJarFileNameByDir <dir>)
function getExecJarFileNameByDir() {
  # 遍历文件夹
  # shellcheck disable=SC2045
  for file in $(ls "$1"); do
    # 当前如果为文件则判断是否以.jar结尾
    if [[ -f $1"/"${file} ]]; then
      # 获取整个文件名的长度
      length=${#file}

      # 长度大于9，则判断是否以.jar结尾。
      if [[ ${length} -gt 4 ]]; then
        endWith=${file:${length}-4:4}
        if [[ ${endWith} == ".jar" ]]; then
          echo "$file"
          return
        fi
      fi
    fi
  done
}

# 后台发布程序
# return 0: 成功 1: 失败
function api() {
  username=$1
  baseDir=$2
  server=$3
  port=$4
  appdir=$5

  echo 'rm target'
  rm -rf 'target'
  mvn install -Dmaven.test.skip=true

  cd 'target'
  returnVar=$(getExecJarFileNameByDir "$PWD")
  echo "$returnVar"
  cd '..'

  # 获取到的jar包名为空, 说明打包出现问题
  if [[ -z "$returnVar" ]]; then
    echo "jar is empty"
    return 1
  fi

  echo 'upload file'
  scp -r -P "${port}" "$baseDir"/target/"${returnVar}" "${username}"@"${server}":"${appdir}/"

  echo 'copy file'
  ssh -p "${port}" "${username}"@"${server}" "cp ${appdir}/${returnVar} ${appdir}/app.jar"

  echo 'stoping'
  ssh -p "${port}" "${username}"@"${server}" "cd ${appdir} && ./stop.sh"

  echo 'starting'
  ssh -p "${port}" "${username}"@"${server}" "cd ${appdir} && ./start.sh &" &

  return 0
}

# 启动入口
function main() {
  username=""
  server="schedule.mengyunzhi.cn"
  port="2122"
  appdir="/mengyunzhi/app/schedule/api"

  while getopts "u:" opt; do
    case "$opt" in
    u) username="$OPTARG" ;;
    ?) helpFunction "$@" ;; # Print helpFunction in case parameter is non-existent
    esac
  done

  if [ "${username}" == "" ]; then
      echo "plz input username with -u"
      exit 0
  fi

  echo "username is ${username} you should use 'ssh-copy-id ${username}@${server}' upload your public key to avoid input password"

  # 调用function api构建后台
  api "${username}" $(dirname "$0") $server $port $appdir;

  exit 0
}

# 启动程序
main "$@"


# 服务器对应的脚本内容如下：

# start.sh
# #/sbin/bin
# java -Dfile.encoding=utf-8 -jar app.jar \
# --server.port=8083 --active=production --username=schedule --password=schedule &


# stop.sh
# #!/bin/sh
#
# printf 'kill old jar process if exist\n'
# ps -aux | grep server.port=8083 | grep -v grep | awk "{print \$2}" | xargs kill
#
# printf 'done'
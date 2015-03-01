# !/bin/bash
# kill UAT pid and restart UAT
pidlist=`ps -ef| grep uat | grep -v "grep" | awk '{print $2}'`
#ps -u $USER|grep "java"|grep -v "grep"
echo will kill $pidlist
if [ $pidlist ]; then
for pid in ${pidlist}
do
kill -9 $pid
done;
#start UAT
cd /root/.jenkins/jobs/eedaDailyBuild/workspace;

nohup ant uat >/dev/null 2>&1 &

pid=`ps -ef| grep uat | grep -v "grep" | awk '{print $2}'`
echo new UAT pid: $pid
fi

#!/bin/bash
#
# usage: studdata.sh FILE [TIMESTAMP]

# -2       Forces ssh to try protocol version 2 only.
# -a       Disables forwarding of the authentication agent connection.
# -x       Disables X11 forwarding.
# -n     ! Redirects stdin from /dev/null (actually, prevents reading from
#          stdin).
# -T     ! Disable pseudo-tty allocation.
# -q       Quiet mode.
# -e none  Disables any escapes and makes the session fully transparent.

identity="studdata@blackhole.rz.tu-harburg.de"

myssh() {
  ssh -2 -a -x -q -T -e none -o "BatchMode yes" -o "StrictHostKeyChecking yes" \
  -i ~/.ssh/id_rsa_tim $identity write
}

if [ "$3" == "wrap" ]; then
  echo '<date>'
  echo $2
  echo '</date>'
  echo '<data>'
  cat $1
  echo '</data>'
  exit 0;
fi
if [ "$2" == "" ]; then
  timestamp=${1:6:17}
# timestamp=`date +%F`
else
  timestamp=$2
fi
res=`$0 $1 $timestamp wrap | myssh`
rc=$?

if [ "$res" != "done" ]; then
  echo $res
  rc=1
fi

exit $rc
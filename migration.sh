#!/bin/bash
# CSV形式のデータをRedisにセットとして追加
HOST=
PASSWORD=

# CSVファイルを処理し、キーと値をRedisにセットとして追加
cat output.csv | while IFS=, read -r key value
do
  # キーと値を整形
  key=$(echo $key | sed 's/"//g')
  value=$(echo $value | sed 's/^"//' | sed 's/"$//')  # カンマを含む文字列を取り除く
  echo "key: $key, value: $value"

  redis-cli -h $HOST -a $PASSWORD　SET $key $value
done
